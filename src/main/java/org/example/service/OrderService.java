package org.example.service;

import org.example.dto.OrderDTO.OrderCreateReq;
import org.example.dto.OrderDTO.OrderResp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface OrderService {
    @Transactional
    List<OrderResp> getAll();
    @Transactional
    List<OrderResp> getAllAllowed(Boolean status);
    @Transactional
    OrderResp save(OrderCreateReq dto, Long idUser, Integer idCar);
    @Transactional
    Long update(OrderResp dto);
    @Transactional
    void delete(Long id);
    @Transactional
    Long allowOrder(Long idOrder, Long idAdmin, Boolean statusBoolean);
}
