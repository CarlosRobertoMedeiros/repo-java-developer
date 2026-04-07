package br.com.roberto.hub_manager_app.infrastructure.adapter.in.web;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkFilter;
import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper.PaymentLinkMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request.PaymentLinkRequest;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response.PaymentLinkResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment-links")
public class PaymentLinkController {

    private final PaymentLinkInPort paymentLinkInPort;

    public PaymentLinkController(PaymentLinkInPort paymentLinkInPort) {
        this.paymentLinkInPort = paymentLinkInPort;
    }


//    @GetMapping
//    public ResponseEntity<List<PaymentLinkResponse>> findAll(){
//        var response = paymentLinkInPort.findAll()
//                .stream()
//                .map(PaymentLinkMapper::fromModel)
//                .toList();
//
//        return ResponseEntity.ok(response);
//    }

    @GetMapping
    public ResponseEntity<Page<PaymentLinkResponse>> findAll(
            @RequestParam(required = false) LocalDateTime createdAtFrom,
            @RequestParam(required = false) LocalDateTime createdAtTo,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) String description,
            Pageable pageable) {

        var filter = new PaymentLinkFilter(
                createdAtFrom,
                createdAtTo,
                isActive,
                description
        );

        var page = paymentLinkInPort.findAll(pageable, filter)
                .map(PaymentLinkMapper::fromModel);

        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PaymentLinkResponse> findById(@PathVariable UUID id){
        return paymentLinkInPort.findById(id)
                .map(PaymentLinkMapper::fromModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> create(@RequestBody PaymentLinkRequest paymentLinkRequest){
        var entity = paymentLinkInPort.create(
                PaymentLinkMapper.toModel(paymentLinkRequest)
        );

        var response = PaymentLinkMapper.fromModel(entity);

        URI location = URI.create("/api/payment-links/" + entity.getId());

        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentLinkResponse> update(@PathVariable UUID id, @RequestBody PaymentLinkRequest paymentLinkRequest){
        var entity = paymentLinkInPort.update(id, PaymentLinkMapper.toModel(paymentLinkRequest));
        return ResponseEntity.ok(PaymentLinkMapper.fromModel(entity));
    }

    @PatchMapping("/{id}/disable")
    public ResponseEntity<PaymentLinkResponse> disabled(@PathVariable UUID id){
        var entity = paymentLinkInPort.disable(id);
        return ResponseEntity.ok(PaymentLinkMapper.fromModel(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        paymentLinkInPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}