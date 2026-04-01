package br.com.roberto.hub_manager_app.infrastructure.dto.response;

public record HubManagerResponse(
        String id,
        String name,
        String email,
        String status
) {}
