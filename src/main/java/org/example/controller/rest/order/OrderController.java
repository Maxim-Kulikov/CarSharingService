package org.example.controller.rest.order;

import lombok.AllArgsConstructor;
import org.example.dto.OrderDTO.OrderCreateReq;
import org.example.dto.OrderDTO.OrderResp;
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
    public List<OrderResp> getAll(){
        return orderService.getAll();
    }

    @RequestMapping("/get/all/{status}")
    public List<OrderResp> getAll(@PathVariable Boolean status){
        return orderService.getAllAllowed(status);
    }

    @PostMapping("/save/{idUser}/{idCar}")
    public OrderResp save(@RequestBody OrderCreateReq dto, @PathVariable Long idUser, @PathVariable Integer idCar){
        return orderService.save(dto, idUser, idCar);
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
