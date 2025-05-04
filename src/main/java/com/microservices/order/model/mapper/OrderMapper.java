package com.microservices.order.model.mapper;

import com.microservices.order.model.Order;
import com.microservices.order.model.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface OrderMapper {

    OrderDto toDto(Order order);

    @Mapping(target = "orderNumber", expression = "java(java.util.UUID.randomUUID().toString())")
    Order toModel(OrderDto orderDto);

    List<OrderDto> toDtoList(List<Order> orders);
}
