package org.example.service;

import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.OrderNotFoundException;
import org.example.dto.exception.UserIsNotAdminException;
import org.example.dto.exception.UserNotFoundException;
import org.example.dto.orderDTO.OrderCreateReq;
import org.example.dto.orderDTO.OrderFilterReq;
import org.example.dto.orderDTO.OrderResp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    List<OrderResp> getAll(OrderFilterReq filter);

    OrderResp save(OrderCreateReq dto) throws UserNotFoundException, CarNotFoundException;

    OrderResp update(OrderResp dto) throws OrderNotFoundException, UserNotFoundException, CarNotFoundException;

    void delete(Long id) throws OrderNotFoundException;

    OrderResp allowOrder(Long idOrder, Long idAdmin, Boolean statusBoolean, String refuseReason) throws UserNotFoundException, UserIsNotAdminException, OrderNotFoundException;
}
