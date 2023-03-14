package org.example.service;

import org.example.dto.OrderDTO.OrderCreationRequest;
import org.example.dto.OrderDTO.OrderResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface OrderService {
    @Transactional
    List<OrderResponse> getAll();
    @Transactional
    List<OrderResponse> getAllAllowed(Boolean status);
    @Transactional
    Long save(OrderCreationRequest dto);
    @Transactional
    Long update(OrderResponse dto);
    @Transactional
    void delete(Long id);
    @Transactional
    Long allowOrder(Long idOrder, Long idAdmin, Boolean statusBoolean);
}
