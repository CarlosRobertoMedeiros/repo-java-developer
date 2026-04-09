package br.com.roberto.hub_manager_app.infrastructure.adapter.in.web;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper.PaymentLinkMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request.PaymentLinkRequest;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response.PaymentLinkResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Validated
@RestController
@RequestMapping("/api/payment-links")
public class PaymentLinkController {

    private final PaymentLinkInPort paymentLinkInPort;

    public PaymentLinkController(PaymentLinkInPort paymentLinkInPort) {
        this.paymentLinkInPort = paymentLinkInPort;
    }


    @GetMapping
    public ResponseEntity<Page<PaymentLinkResponse>> findAll(
            @RequestParam(required = false) LocalDateTime createdAtFrom,
            @RequestParam(required = false) LocalDateTime createdAtTo,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String description,
            Pageable pageable) {

        log.debug("Fetching payment links with filters - createdAtFrom: {}, createdAtTo: {}, isActive: {}, description: {}, page: {}",
                createdAtFrom, createdAtTo, isActive, description, pageable.getPageNumber());

        var filter = new PaymentLinkFilter(
                createdAtFrom,
                createdAtTo,
                isActive,
                description
        );

        var page = paymentLinkInPort.findAll(pageable, filter)
                .map(PaymentLinkMapper::fromModel);

        log.info("Found {} payment links", page.getTotalElements());
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentLinkResponse> findById(
            @PathVariable UUID id){
        log.debug("Fetching payment link with id: {}", id);
        return paymentLinkInPort.findById(id)
                .map(PaymentLinkMapper::fromModel)
                .map(response -> {
                    log.info("Payment link found with id: {}", id);
                    return ResponseEntity.ok(response);
                })
                .orElseGet(() -> {
                    log.warn("Payment link not found with id: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> create(
            @Valid @RequestBody PaymentLinkRequest paymentLinkRequest){
        log.info("Creating payment link with description: {}", paymentLinkRequest.description());
        var entity = paymentLinkInPort.create(
                PaymentLinkMapper.toModel(paymentLinkRequest)
        );

        var response = PaymentLinkMapper.fromModel(entity);

        URI location = URI.create("/api/payment-links/" + entity.getId());
        log.info("Payment link created successfully with id: {}", entity.getId());

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentLinkResponse> update(
            @PathVariable UUID id,
            @Valid @RequestBody PaymentLinkRequest paymentLinkRequest){
        log.info("Updating payment link with id: {}", id);
        var entity = paymentLinkInPort.update(id, PaymentLinkMapper.toModel(paymentLinkRequest));
        log.info("Payment link updated successfully with id: {}", id);
        return ResponseEntity.ok(PaymentLinkMapper.fromModel(entity));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<PaymentLinkResponse> disabled(
            @PathVariable UUID id){
        log.info("Disabling payment link with id: {}", id);
        var entity = paymentLinkInPort.disable(id);
        log.info("Payment link disabled successfully with id: {}", id);
        return ResponseEntity.ok(PaymentLinkMapper.fromModel(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable UUID id){
        log.info("Deleting payment link with id: {}", id);
        paymentLinkInPort.delete(id);
        log.info("Payment link deleted successfully with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}