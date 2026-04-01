package br.com.roberto.ms_conversor.infraestructure.adapter.input;

import java.util.List;

public record BillingRequest(List<ItemRequest> items) {
}
