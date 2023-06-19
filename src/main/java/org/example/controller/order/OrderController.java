package org.example.controller.order;

import lombok.AllArgsConstructor;
import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.UserNotFoundException;
import org.example.dto.orderDTO.OrderCreateReq;
import org.example.dto.orderDTO.OrderFilterReq;
import org.example.dto.orderDTO.OrderResp;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @GetMapping("")
    public List<OrderResp> getAll(@RequestBody OrderFilterReq filter) {
        return orderService.getAll(filter);
    }

    @PostMapping("/save")
    public OrderResp save(@RequestBody OrderCreateReq dto) throws UserNotFoundException, CarNotFoundException {
        return orderService.save(dto);
    }

    @PatchMapping("/allow")
    public Long allow(@RequestParam Long idOrder, @RequestParam Long idAdmin, @RequestParam Boolean status) throws UserNotFoundException {
        return orderService.allowOrder(idOrder, idAdmin, status);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        orderService.delete(id);
    }
}
