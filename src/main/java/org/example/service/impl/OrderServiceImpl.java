package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.dao.repository.OrderDao;
import org.example.dao.repository.car.CarDao;
import org.example.dao.repository.user.UserDao;
import org.example.dto.OrderDTO.OrderCreationRequest;
import org.example.dto.OrderDTO.OrderResponse;
import org.example.mapper.OrderMapper;
import org.example.model.Car;
import org.example.model.Order;
import org.example.model.User;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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
    public List<OrderResponse> getAll() {
        return orderMapper.toListOrderCreationResponses(
                (List<Order>) orderDao.findAll()
        );
    }

    @Override
    public List<OrderResponse> getAllAllowed(Boolean status) {
        return orderMapper.toListOrderCreationResponses(
                orderDao.findAllByStatusIs(status)
        );
    }

    @Override
    public Long save(OrderCreationRequest dto) {
        Optional<User> user = userDao.findById(dto.getIdUser());
        Optional<Car> car = carDao.findById(dto.getIdCar());
        Order order = orderMapper.toOrder(dto, user.get(), car.get());
        Long difference = (dto.getFinishDate().getTime()-dto.getStartDate().getTime())/(24*60*60*1000);
        Long price = car.get().getPrice()*difference;
        order.setPrice(price);
        order.setStatus(false);
        orderDao.save(order);
        return order.getId();
    }

    @Override
    public Long update(OrderResponse dto) {
        return null;
    }

    @Override
    public void delete(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Long allowOrder(Long idOrder, Long idAdmin, Boolean status) {
        User admin = userDao.findById(idAdmin).get();
        Order order = orderDao.findById(idOrder).get();
        order.setAdminLogin(admin.getLogin());
        order.setStatus(status);
        orderDao.save(order);
        return order.getId();
    }
}
