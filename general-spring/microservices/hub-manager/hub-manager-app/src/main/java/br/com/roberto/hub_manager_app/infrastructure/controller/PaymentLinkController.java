package br.com.roberto.hub_manager_app.infrastructure.controller;

import br.com.roberto.api.PaymentLinksApi;
import br.com.roberto.hub_manager_app.domain.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.domain.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.infrastructure.adapter.mapper.PaymentLinkMapper;
import br.com.roberto.model.PagedPaymentLinkResponse;
import br.com.roberto.model.PaymentLinkRequest;
import br.com.roberto.model.PaymentLinkResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
//@RequestMapping("/api/payment-links")
public class PaymentLinkController implements PaymentLinksApi {

    private final PaymentLinkInPort paymentLinkInPort;
    private final PaymentLinkMapper paymentLinkMapper;

    public PaymentLinkController(PaymentLinkInPort paymentLinkInPort, PaymentLinkMapper paymentLinkMapper) {
        this.paymentLinkInPort = paymentLinkInPort;
        this.paymentLinkMapper = paymentLinkMapper;
    }

    @Override
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(PaymentLinkRequest paymentLinkRequest, String xCorrelationId) {
                var entity = paymentLinkInPort.create(
                paymentLinkMapper.toDomain(paymentLinkRequest)
        );

        var response = paymentLinkMapper.toResponse(entity);

        URI location = URI.create("/api/payment-links/" + entity.getId());

        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<Void> deletePaymentLink(UUID id, String xCorrelationId) {
        paymentLinkInPort.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PaymentLinkResponse> disablePaymentLink(UUID id, String xCorrelationId) {
        var entity = paymentLinkInPort.disable(id);
        return ResponseEntity.ok(paymentLinkMapper.toResponse(entity));
    }

    @Override
    public ResponseEntity<PagedPaymentLinkResponse> findAllPaymentLinks(String xCorrelationId, Integer page, Integer size, String sort, OffsetDateTime createdAtFrom, OffsetDateTime createdAtTo, Boolean isActive, String description) {
        var filter = new PaymentLinkFilter(
                createdAtTo,
                createdAtFrom,
                isActive,
                description
        );

        var page = paymentLinkInPort.findAll(pageable, filter)
                .map(paymentLinkMapper::toResponse);

        return ResponseEntity.ok(page);
    }

    @Override
    public ResponseEntity<PaymentLinkResponse> findPaymentLinkById(UUID id, String xCorrelationId) {
        return paymentLinkInPort.findById(id)
                .map(paymentLinkMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<PaymentLinkResponse> updatePaymentLink(UUID id, PaymentLinkRequest paymentLinkRequest, String xCorrelationId) {
        var entity = paymentLinkInPort.update(id, paymentLinkMapper.toDomain(paymentLinkRequest));
        return ResponseEntity.ok(paymentLinkMapper.toResponse(entity));
    }


//
//    public PaymentLinkController(PaymentLinkInPort paymentLinkInPort, PaymentLinkMapper paymentLinkMapper) {
//        this.paymentLinkInPort = paymentLinkInPort;
//        this.paymentLinkMapper = paymentLinkMapper;
//    }
//
//    @GetMapping
//    public ResponseEntity<Page<PaymentLinkResponse>> findAll(
//            @RequestParam(required = false) LocalDateTime createdAtTo,
//            @RequestParam(required = false) LocalDateTime createdAtFrom,
//            @RequestParam(required = false) Boolean isActive,
//            @RequestParam(required = false) String description,
//            Pageable pageable) {
//
//        var filter = new PaymentLinkFilter(
//                createdAtTo,
//                createdAtFrom,
//                isActive,
//                description
//        );
//
//        var page = paymentLinkInPort.findAll(pageable, filter)
//                .map(paymentLinkMapper::toResponse);
//
//        return ResponseEntity.ok(page);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<PaymentLinkResponse> findById(@PathVariable UUID id){
//        return paymentLinkInPort.findById(id)
//                .map(paymentLinkMapper::toResponse)
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    @PostMapping
//    public ResponseEntity<PaymentLinkResponse> create(@RequestBody PaymentLinkRequest paymentLinkRequest){
//        var entity = paymentLinkInPort.create(
//                paymentLinkMapper.toDomain(paymentLinkRequest)
//        );
//
//        var response = paymentLinkMapper.toResponse(entity);
//
//        URI location = URI.create("/api/payment-links/" + entity.getId());
//
//        return ResponseEntity.created(location).body(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<PaymentLinkResponse> update(@PathVariable UUID id, @RequestBody PaymentLinkRequest paymentLinkRequest){
//        var entity = paymentLinkInPort.update(id, paymentLinkMapper.toDomain(paymentLinkRequest));
//        return ResponseEntity.ok(paymentLinkMapper.toResponse(entity));
//    }
//
//    @PatchMapping("/{id}/disable")
//    public ResponseEntity<PaymentLinkResponse> disabled(@PathVariable UUID id){
//        var entity = paymentLinkInPort.disable(id);
//        return ResponseEntity.ok(paymentLinkMapper.toResponse(entity));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable UUID id){
//        paymentLinkInPort.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}