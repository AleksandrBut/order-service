package com.microservices.order.model.dto;

import java.math.BigDecimal;

public record OrderDto(
        Long id,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
