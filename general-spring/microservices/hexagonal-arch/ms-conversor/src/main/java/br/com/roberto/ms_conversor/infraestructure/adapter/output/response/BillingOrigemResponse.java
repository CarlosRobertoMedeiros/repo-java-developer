package br.com.roberto.ms_conversor.infraestructure.adapter.output;

import java.util.List;

public record BillingOrigemResponse(List<ItemOrigemResponse> items) {
}
