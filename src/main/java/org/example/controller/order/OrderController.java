package org.example.controller.order;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.OrderNotFoundException;
import org.example.dto.exception.UserIsNotAdminException;
import org.example.dto.exception.UserNotFoundException;
import org.example.dto.orderDTO.OrderCreateReq;
import org.example.dto.orderDTO.OrderFilterReq;
import org.example.dto.orderDTO.OrderResp;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<List<OrderResp>> getAll(@RequestBody OrderFilterReq filter) {
        return new ResponseEntity<>(orderService.getAll(filter), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<OrderResp> save(@RequestBody OrderCreateReq dto) throws UserNotFoundException, CarNotFoundException {
        return new ResponseEntity<>(orderService.save(dto), HttpStatus.OK);
    }

    @PatchMapping("/allow")
    public ResponseEntity<OrderResp> allow(@RequestParam Long idOrder,
                           @RequestParam Long idAdmin,
                           @RequestParam String status,
                           @RequestParam(required = false) String refuseReason)
            throws UserNotFoundException, OrderNotFoundException, UserIsNotAdminException {
        return new ResponseEntity<>(
                orderService.allowOrder(idOrder, idAdmin, Boolean.parseBoolean(status), refuseReason), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) throws OrderNotFoundException {
        orderService.delete(id);
    }
}
