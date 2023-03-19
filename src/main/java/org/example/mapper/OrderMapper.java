package org.example.mapper;

import org.example.dto.OrderDTO.OrderCreateReq;
import org.example.dto.OrderDTO.OrderResp;
import org.example.model.Car;
import org.example.model.Order;
import org.example.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "car", source = "car")
    @Mapping(target = "user", source = "user")
    Order toOrder(OrderCreateReq dto, User user, Car car);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "idUser", source = "user.id")
    @Mapping(target = "idCar", source = "car.id")
    OrderResp toOrderResp(Order order);

    List<OrderResp> toListOrderCreationResponses(List<Order> orders);
}
