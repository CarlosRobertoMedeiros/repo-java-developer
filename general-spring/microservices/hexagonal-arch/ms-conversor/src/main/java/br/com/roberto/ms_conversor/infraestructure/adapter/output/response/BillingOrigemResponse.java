package br.com.roberto.ms_conversor.infraestructure.adapter.output.response;

import java.util.List;

public record BillingOrigemResponse(List<ItemOrigemResponse> items) {
}
