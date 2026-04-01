package br.com.roberto.hub_manager_app.infrastructure.dto.request;

public record HubManagerRequest(
        String name,
        String email,
        String status
) {}
