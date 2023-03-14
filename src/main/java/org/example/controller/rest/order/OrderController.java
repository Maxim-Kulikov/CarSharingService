package org.example.controller.rest.order;

import lombok.AllArgsConstructor;
import org.example.dto.OrderDTO.OrderCreationRequest;
import org.example.dto.OrderDTO.OrderResponse;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @RequestMapping("/get/all")
    public List<OrderResponse> getAll(){
        return orderService.getAll();
    }

    @RequestMapping("/get/all/{status}")
    public List<OrderResponse> getAll(@PathVariable Boolean status){
        return orderService.getAllAllowed(status);
    }

    @PostMapping("/save")
    public Long save(@RequestBody OrderCreationRequest dto){
        return orderService.save(dto);
    }

    @PatchMapping("/allow")
    public Long allow(@RequestParam Long idOrder, @RequestParam Long idAdmin, @RequestParam Boolean status){
        return orderService.allowOrder(idOrder, idAdmin, status);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        orderService.delete(id);
    }
}
