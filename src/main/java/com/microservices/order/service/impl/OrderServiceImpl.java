package com.microservices.order.service.impl;

import com.microservices.order.model.Order;
import com.microservices.order.model.dto.OrderDto;
import com.microservices.order.model.mapper.OrderMapper;
import com.microservices.order.repository.OrderRepository;
import com.microservices.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return orderMapper.toDto(
                orderRepository.save(orderMapper.toModel(orderDto))
        );
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        return orderMapper.toDto(
                orderRepository.save(orderMapper.toModel(orderDto))
        );
    }

    @Override
    public OrderDto getOrder(Long id) {
        return orderMapper.toDto(
                orderRepository.findById(id)
                        .orElse(null)
        );
    }

    @Override
    public List<OrderDto> getOrders() {
        return orderMapper.toDtoList((List<Order>) orderRepository.findAll());
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
