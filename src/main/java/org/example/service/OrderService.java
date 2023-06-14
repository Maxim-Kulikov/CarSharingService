package org.example.service;

import org.example.controller.exception.CarNotFoundException;
import org.example.controller.exception.UserNotFoundException;
import org.example.dto.orderDTO.OrderCreateReq;
import org.example.dto.orderDTO.OrderFilterReq;
import org.example.dto.orderDTO.OrderResp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface OrderService {
    List<OrderResp> getAll(OrderFilterReq filter);

    OrderResp save(OrderCreateReq dto) throws UserNotFoundException, CarNotFoundException;

    Long update(OrderResp dto);

    void delete(Long id);

    Long allowOrder(Long idOrder, Long idAdmin, Boolean statusBoolean) throws UserNotFoundException;
}
