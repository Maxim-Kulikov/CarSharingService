package org.example.service.impl;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.example.controller.exception.CarNotFoundException;
import org.example.controller.exception.UserNotFoundException;
import org.example.dto.orderDTO.OrderFilterReq;
import org.example.repository.OrderDao;
import org.example.repository.car.CarDao;
import org.example.repository.user.UserDao;
import org.example.dto.orderDTO.OrderCreateReq;
import org.example.dto.orderDTO.OrderResp;
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
import java.util.stream.Collectors;

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
    public List<OrderResp> getAll(OrderFilterReq filter) {
        return orderMapper.toListOrderCreationResponses(
                ((List<Order>) orderDao.findAll()).stream()
                        .filter(order -> (filter.getStatus() == null || filter.getStatus().equals(order.getStatus()))
                                && (filter.getIdAdmin() == null || filter.getIdAdmin().equals(order.getAdmin().getId()))
                                && (filter.getIdUser() == null || filter.getIdUser().equals(order.getUser().getId()))
                        ).collect(Collectors.toList()));
    }

    @Override
    public OrderResp save(OrderCreateReq dto) throws UserNotFoundException, CarNotFoundException {
        Long idUser = dto.getIdUser();
        Integer idCar = dto.getIdCar();

        User user = userDao.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException(idUser));
        Car car = carDao.findById(idCar)
                .orElseThrow(() -> new CarNotFoundException(idCar));
        Order order = orderMapper.toOrder(dto, user, car);

        Long difference = (dto.getFinishDate().getTime() - dto.getStartDate().getTime()) / (24 * 60 * 60 * 1000);
        Long price = car.getPrice() * difference;

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
    public Long allowOrder(Long idOrder, Long idAdmin, Boolean status) throws UserNotFoundException {
        User admin = userDao.findById(idAdmin)
                .orElseThrow(() -> new UserNotFoundException(idAdmin));
        Order order = orderDao.findById(idOrder)
                .orElseThrow(() -> new RuntimeException("Could not find order by this id!"));
        order.setAdmin(admin);
        order.setStatus(status);
        orderDao.save(order);
        return order.getId();
    }

    //TODO доделать updateOrder
    /*private Order updateOrder(OrderResp dto, Order model) {
        return model.changer()
                .id(model.getId())
                .admin(dto.getAdminLogin().equals(model.getAdmin()) || dto.getAdminLogin() == null
                        ? model.getAdmin() : dto.())
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
    }*/
}
