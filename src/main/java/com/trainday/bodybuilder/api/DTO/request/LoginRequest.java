package com.trainday.bodybuilder.api.DTO.request;

public record LoginRequest(
    String id,
    String email,
    String password
) {
}
