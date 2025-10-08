package com.znaji.InventoryManagementSystem.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.znaji.InventoryManagementSystem.dto.response.ErrorResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedhandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.of(
                HttpServletResponse.SC_FORBIDDEN,
                "FORBIDDEN",
                accessDeniedException.getMessage(),
                request.getRequestURI()
        );

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
    }
}
