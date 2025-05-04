package com.microservices.order.service;

import com.microservices.order.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(OrderDto orderDto);

    OrderDto getOrder(Long id);

    List<OrderDto> getOrders();

    void deleteOrder(Long id);
}
