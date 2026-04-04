package br.com.roberto.hub_manager_app.infrastructure.adapter.in.web;

import br.com.roberto.hub_manager_app.application.ports.in.PaymentLinkInPort;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.mapper.PaymentLinkMapper;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.request.PaymentLinkRequest;
import br.com.roberto.hub_manager_app.infrastructure.adapter.in.dto.response.PaymentLinkResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment-link")
public class PaymentLinkController {

    private final PaymentLinkInPort paymentLinkInPort;

    public PaymentLinkController(PaymentLinkInPort paymentLinkInPort) {
        this.paymentLinkInPort = paymentLinkInPort;
    }


    @GetMapping
    public ResponseEntity<List<PaymentLinkResponse>> findAll(){
        var response = paymentLinkInPort.findAll()
                .stream()
                .map(PaymentLinkMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentLinkResponse> findById(@PathVariable UUID id){
        return paymentLinkInPort.findById(id)
                .map(PaymentLinkMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PaymentLinkResponse> create( /*@Valid*/ @RequestBody PaymentLinkRequest paymentLinkRequest){
        var entity = paymentLinkInPort.create(
                PaymentLinkMapper.toEntity(paymentLinkRequest)
        );

        var response = PaymentLinkMapper.toResponse(entity);

        URI location = URI.create("/api/payment-links/" + entity.getId());

        return ResponseEntity.created(location).body(response);
    }

    @PatchMapping("/disabled/{id}")
    public ResponseEntity<PaymentLinkResponse> disabled(@PathVariable UUID id){
        var entity = paymentLinkInPort.disable(id);
        return ResponseEntity.ok(PaymentLinkMapper.toResponse(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentLinkResponse> update(@PathVariable UUID id,
                                                      /*@Valid*/ @RequestBody PaymentLinkRequest paymentLinkRequest){
        var entity = paymentLinkInPort.update(id, PaymentLinkMapper.toEntity(paymentLinkRequest));
        return ResponseEntity.ok(PaymentLinkMapper.toResponse(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        paymentLinkInPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
