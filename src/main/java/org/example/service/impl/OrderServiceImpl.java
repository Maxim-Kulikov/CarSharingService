package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dao.repository.OrderDao;
import org.example.dao.repository.car.CarDao;
import org.example.dao.repository.user.UserDao;
import org.example.dto.OrderDTO.OrderCreateReq;
import org.example.dto.OrderDTO.OrderResp;
import org.example.mapper.OrderMapper;
import org.example.model.Car;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@EqualsAndHashCode
@AllArgsConstructor
@ComponentScan("org.example")
public class OrderServiceImpl implements OrderService {

    @Autowired
    private final OrderDao orderDao;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final CarDao carDao;


    @Override
    public List<OrderResp> getAll() {
        return orderMapper.toListOrderCreationResponses(
                (List<Order>) orderDao.findAll()
        );
    }

    @Override
    public List<OrderResp> getAllAllowed(Boolean status) {
        return orderMapper.toListOrderCreationResponses(
                orderDao.findAllByStatusIs(status)
        );
    }

    @Override
    public OrderResp save(OrderCreateReq dto, Long idUser, Integer idCar) {
        User user = userDao.findById(idUser)
                .orElseThrow(() -> new RuntimeException("Could not find user by this id!"));
        Car car = carDao.findById(idCar)
                .orElseThrow(() -> new RuntimeException("Could not find car by this id!"));
        Order order = orderMapper.toOrder(dto, user, car);
        Long difference = (dto.getFinishDate().getTime()-dto.getStartDate().getTime())/(24*60*60*1000);
        Long price = car.getPrice()*difference;
        order.setPrice(price);
        order.setStatus(false);
        orderDao.save(order);
        return orderMapper.toOrderResp(order);
    }

    //TODO нету реализации в update
    @Override
    public Long update(OrderResp dto) {
        return null;

    }

    @Override
    public void delete(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Long allowOrder(Long idOrder, Long idAdmin, Boolean status) {
        User admin = userDao.findById(idAdmin)
                .orElseThrow(() -> new RuntimeException("Could not find admin by this id!"));
        Order order = orderDao.findById(idOrder)
                .orElseThrow(() -> new RuntimeException("Could not find order by this order!"));
        order.setAdminLogin(admin.getLogin());
        order.setStatus(status);
        orderDao.save(order);
        return order.getId();
    }

    //TODO доделать updateOrder
    private Order updateOrder(OrderResp dto, Order model){
        return model.changer()
                .id(model.getId())
                .adminLogin(dto.getAdminLogin().equals(model.getAdminLogin()) || dto.getAdminLogin() == null
                        ? model.getAdminLogin() : dto.getAdminLogin())
                .price(dto.getPrice().equals(model.getPrice()) || dto.getPrice() == null
                        ? model.getPrice() : dto.getPrice())
                .startDate(dto.getStartDate().equals(model.getStartDate()) || dto.getStartDate() == null
                        ? model.getStartDate() : dto.getStartDate())
                .finishDate(dto.getFinishDate().equals(model.getFinishDate()) || dto.getFinishDate() == null
                        ? model.getFinishDate() : dto.getFinishDate())
                .status(dto.getStatus().equals(model.getStatus()) || dto.getStatus() == null
                        ? model.getStatus() : dto.getStatus())
                .refuseReason(dto.getRefuseReason().equals(model.getRefuseReason()) || dto.getRefuseReason() == null
                        ? model.getRefuseReason() : dto.getRefuseReason())
                //.car(dto.get)
                .change();
    }
}
