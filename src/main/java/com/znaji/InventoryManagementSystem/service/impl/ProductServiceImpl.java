package com.znaji.InventoryManagementSystem.service.impl;

import com.znaji.InventoryManagementSystem.dto.request.ProductRequest;
import com.znaji.InventoryManagementSystem.dto.response.ProductResponse;
import com.znaji.InventoryManagementSystem.dto.response.Response;
import com.znaji.InventoryManagementSystem.entity.Category;
import com.znaji.InventoryManagementSystem.entity.Product;
import com.znaji.InventoryManagementSystem.exception.NotFoundException;
import com.znaji.InventoryManagementSystem.repository.CategoryRepository;
import com.znaji.InventoryManagementSystem.repository.ProductRepository;
import com.znaji.InventoryManagementSystem.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String IMAGE_URL_PREFIX = "/uploads/products/";

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final Path imageStoragePath = Paths.get("uploads", "products");

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Response createProduct(ProductRequest request) {
        productRepository.findBySkuIgnoreCase(request.sku())
                .ifPresent(existing -> {
                    throw new IllegalArgumentException("Product with SKU %s already exists.".formatted(request.sku()));
                });

        Product product = new Product();
        applyRequest(product, request, false);
        productRepository.save(product);

        return Response.builder()
                .status(HttpStatus.CREATED.value())
                .message("Product created.")
                .build();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAllBy();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findProductById(id)
                .orElseThrow(() -> new NotFoundException("Product not found for id: " + id));
    }

    @Override
    @Transactional
    public Response updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found for id: " + id));

        if (!product.getSku().equalsIgnoreCase(request.sku())) {
            productRepository.findBySkuIgnoreCase(request.sku())
                    .filter(existing -> !existing.getId().equals(id))
                    .ifPresent(existing -> {
                        throw new IllegalArgumentException("Product with SKU %s already exists.".formatted(request.sku()));
                    });
        }

        applyRequest(product, request, true);
        productRepository.save(product);

        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("Product updated.")
                .build();
    }

    @Override
    @Transactional
    public Response deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found for id: " + id));

        productRepository.delete(product);

        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("Product deleted.")
                .build();
    }

    @Override
    public List<ProductResponse> searchProducts(String input) {
        return productRepository
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(input, input);
    }

    @Override
    @Transactional
    public Response uploadProductImage(Long id, MultipartFile image) {
        if (image == null || image.isEmpty()) {
            throw new IllegalArgumentException("Image file must not be empty.");
        }

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found for id: " + id));

        deleteExistingImageIfPresent(product.getImageUrl());
        String storedFileName = storeImageFile(id, image);
        product.setImageUrl(IMAGE_URL_PREFIX + storedFileName);
        productRepository.save(product);

        return Response.builder()
                .status(HttpStatus.OK.value())
                .message("Product image uploaded.")
                .build();
    }

    private void applyRequest(Product product, ProductRequest request, boolean isUpdate) {
        product.setName(request.name());
        product.setSku(request.sku());
        product.setPrice(request.price());
        product.setStockQuantity(request.stockQuantity());
        product.setDescription(request.description());
        product.setImageUrl(request.imageUrl());
        product.setExpiryDate(request.expiryDate());

        if (request.categoryId() != null) {
            Category category = categoryRepository.findById(request.categoryId())
                    .orElseThrow(() -> new NotFoundException("Category not found for id: " + request.categoryId()));
            product.setCategory(category);
        } else if (!isUpdate) {
            product.setCategory(null);
        }
    }

    private void deleteExistingImageIfPresent(String currentImageUrl) {
        if (!StringUtils.hasText(currentImageUrl) || !currentImageUrl.startsWith(IMAGE_URL_PREFIX)) {
            return;
        }
        Path imagePath = imageStoragePath.resolve(currentImageUrl.substring(IMAGE_URL_PREFIX.length()));
        try {
            Files.deleteIfExists(imagePath);
        } catch (IOException ignored) {
            // Ignore delete failures – old files can remain if removal fails
        }
    }

    private String storeImageFile(Long productId, MultipartFile image) {
        String originalFilename = image.getOriginalFilename();
        String cleanedFilename = StringUtils.hasText(originalFilename)
                ? StringUtils.cleanPath(originalFilename)
                : "product-image";
        String extension = StringUtils.getFilenameExtension(cleanedFilename);
        String uniqueName = "product-" + productId + "-" + UUID.randomUUID();
        if (StringUtils.hasText(extension)) {
            uniqueName += "." + extension;
        }

        try {
            Files.createDirectories(imageStoragePath);
            Path destination = imageStoragePath.resolve(uniqueName);
            try (InputStream inputStream = image.getInputStream()) {
                Files.copy(inputStream, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            throw new IllegalStateException("Failed to store product image.", ex);
        }

        return uniqueName;
    }
}
