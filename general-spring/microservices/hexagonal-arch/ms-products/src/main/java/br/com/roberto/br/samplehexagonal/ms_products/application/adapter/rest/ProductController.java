package br.com.roberto.br.samplehexagonal.ms_products.application.adapter.rest;

import br.com.roberto.br.samplehexagonal.ms_products.application.adapter.exceptions.NotFoundInventoryException;
import br.com.roberto.br.samplehexagonal.ms_products.application.adapter.request.InventoryRequest;
import br.com.roberto.br.samplehexagonal.ms_products.application.adapter.request.ProductRequest;
import br.com.roberto.br.samplehexagonal.ms_products.application.adapter.response.ProductResponse;
import br.com.roberto.br.samplehexagonal.ms_products.domain.entities.Product;
import br.com.roberto.br.samplehexagonal.ms_products.domain.ports.interfaces.ProductServicePort;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductServicePort productServicePort;


    public ProductController(ProductServicePort productServicePort) {
        this.productServicePort = productServicePort;
    }

    @PostMapping
    ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest){
        Product savedProduct = productServicePort.createProduct(productRequest.toDomain());
        ProductResponse response = ProductResponse.fromDomain(savedProduct);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    List<ProductResponse> getProducts(){
        return productServicePort.findProducts().stream()
                .map(ProductResponse::fromDomain)
                .toList();
    }

    @PutMapping(value = "/sku/{sku}")
    ResponseEntity<Void> refreshInvenctory(@PathVariable String sku,
                                           @RequestBody @Valid InventoryRequest inventoryRequest)
            throws NotFoundInventoryException {
        productServicePort.updateInventory(sku, inventoryRequest.toDomain() );
        return ResponseEntity.noContent().build();
    }

}