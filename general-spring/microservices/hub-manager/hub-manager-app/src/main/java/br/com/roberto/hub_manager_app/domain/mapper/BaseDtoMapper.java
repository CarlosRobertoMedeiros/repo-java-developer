package br.com.roberto.hub_manager_app.domain.mapper;

public interface BaseDtoMapper<DOMAIN, REQUEST, RESPONSE> {

    DOMAIN toDomain(REQUEST request);
    RESPONSE toResponse(DOMAIN domain);
    void updateDomain(DOMAIN domain, REQUEST request);
}
