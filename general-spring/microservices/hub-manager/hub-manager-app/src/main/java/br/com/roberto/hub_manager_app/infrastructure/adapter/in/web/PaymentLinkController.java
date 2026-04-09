package br.com.roberto.hub_manager_app.infrastructure.adapter.in.web;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper.PaymentLinkMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request.PaymentLinkRequest;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response.PaymentLinkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Payment Links", description = "APIs for managing payment links")
public class PaymentLinkController {

    private final PaymentLinkInPort paymentLinkInPort;

    public PaymentLinkController(PaymentLinkInPort paymentLinkInPort) {
        this.paymentLinkInPort = paymentLinkInPort;
    }


    @GetMapping
    @Operation(summary = "List all payment links", description = "Retrieve a paginated list of payment links with optional filtering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of payment links"),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
    @Operation(summary = "Get payment link by ID", description = "Retrieve a specific payment link by its UUID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment link found"),
            @ApiResponse(responseCode = "404", description = "Payment link not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PaymentLinkResponse> findById(
            @Parameter(description = "Payment link UUID") @PathVariable UUID id){
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
    @Operation(summary = "Create a new payment link", description = "Create a new payment link with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment link created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PaymentLinkResponse> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Payment link details",
                    required = true
            )
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
    @Operation(summary = "Update a payment link", description = "Update an existing payment link with new details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment link updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters"),
            @ApiResponse(responseCode = "404", description = "Payment link not found"),
            @ApiResponse(responseCode = "422", description = "Payment link cannot be updated (inactive or expired)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PaymentLinkResponse> update(
            @Parameter(description = "Payment link UUID") @PathVariable UUID id,
            @Valid @RequestBody PaymentLinkRequest paymentLinkRequest){
        log.info("Updating payment link with id: {}", id);
        var entity = paymentLinkInPort.update(id, PaymentLinkMapper.toModel(paymentLinkRequest));
        log.info("Payment link updated successfully with id: {}", id);
        return ResponseEntity.ok(PaymentLinkMapper.fromModel(entity));
    }

    @PatchMapping("/{id}/disable")
    @Operation(summary = "Disable a payment link", description = "Disable an active payment link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment link disabled successfully"),
            @ApiResponse(responseCode = "404", description = "Payment link not found"),
            @ApiResponse(responseCode = "422", description = "Payment link cannot be disabled (not active)"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<PaymentLinkResponse> disabled(
            @Parameter(description = "Payment link UUID") @PathVariable UUID id){
        log.info("Disabling payment link with id: {}", id);
        var entity = paymentLinkInPort.disable(id);
        log.info("Payment link disabled successfully with id: {}", id);
        return ResponseEntity.ok(PaymentLinkMapper.fromModel(entity));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a payment link", description = "Delete an existing payment link")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Payment link deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment link not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Payment link UUID") @PathVariable UUID id){
        log.info("Deleting payment link with id: {}", id);
        paymentLinkInPort.delete(id);
        log.info("Payment link deleted successfully with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}