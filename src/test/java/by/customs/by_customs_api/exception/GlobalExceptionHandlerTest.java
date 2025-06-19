package by.customs.by_customs_api.exception;

import by.customs.by_customs_api.exception.exceptions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    @Test
    void badRequestException_ShouldStoreMessage() {
        BadRequestException ex = new BadRequestException("Bad request error");
        assertEquals("Bad request error", ex.getMessage());
    }

    @Test
    void duplicateDeclarationException_ShouldStoreMessage() {
        DuplicateDeclarationException ex = new DuplicateDeclarationException("Duplicate");
        assertEquals("Duplicate", ex.getMessage());
    }

    @Test
    void invalidHsCodeException_ShouldStoreMessage() {
        InvalidHsCodeException ex = new InvalidHsCodeException("Invalid HS Code");
        assertEquals("Invalid HS Code", ex.getMessage());
    }

    @Test
    void resourceNotFoundException_ShouldStoreMessage() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Not found");
        assertEquals("Not found", ex.getMessage());
    }

    @Test
    void xmlGenerationException_ShouldStoreMessage() {
        XmlGenerationException ex = new XmlGenerationException("XML Error");
        assertEquals("XML Error", ex.getMessage());
    }

    @Test
    void errorResponse_ShouldSetAndGetFields() {
        ErrorResponse response = ErrorResponse.builder()
                .status(400)
                .error("Bad Request")
                .message("Validation failed")
                .path("/api/items")
                .build();

        assertEquals(400, response.getStatus());
        assertEquals("Bad Request", response.getError());
        assertEquals("Validation failed", response.getMessage());
        assertEquals("/api/items", response.getPath());
        assertNotNull(response.getTimestamp());
    }
}