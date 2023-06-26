package org.example.service.impl;

import lombok.AllArgsConstructor;
import org.example.dto.exception.CarNotFoundException;
import org.example.dto.exception.OrderNotFoundException;
import org.example.dto.exception.UserIsNotAdminException;
import org.example.dto.exception.UserNotFoundException;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final Short ROLE_ADMIN_ID = 1;
    @Autowired
    private final OrderDao orderDao;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final CarDao carDao;


    @Transactional
    @Override
    public List<OrderResp> getAll(OrderFilterReq filter) {
        return orderMapper.toListOrderCreationResponses(
                ((List<Order>) orderDao.findAll()).stream()
                        .filter(order -> (filter.getStatus() == null || filter.getStatus().equals(order.getStatus()))
                                && (filter.getIdAdmin() == null || filter.getIdAdmin().equals(order.getAdmin().getId()))
                                && (filter.getIdUser() == null || filter.getIdUser().equals(order.getUser().getId()))
                        ).collect(Collectors.toList()));
    }

    @Transactional
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

    @Transactional
    @Override
    public OrderResp update(OrderResp dto) throws OrderNotFoundException, UserNotFoundException, CarNotFoundException {
        Order order = orderDao.findById(dto.getId())
                .orElseThrow(() -> new OrderNotFoundException(dto.getId()));
        updateOrder(dto, order);
        orderDao.save(order);
        return orderMapper.toOrderResp(order);
    }

    @Transactional
    @Override
    public void delete(Long id) throws OrderNotFoundException {
        if (!orderDao.existsById(id)) {
            throw new OrderNotFoundException(id);
        }
        orderDao.deleteById(id);
    }

    @Transactional
    @Override
    public OrderResp allowOrder(Long idOrder, Long idAdmin, Boolean status, String refuseReason) throws UserNotFoundException, UserIsNotAdminException, OrderNotFoundException {
        User admin = userDao.findById(idAdmin)
                .orElseThrow(() -> new UserNotFoundException(idAdmin));

        if (!admin.getRole().getId().equals(ROLE_ADMIN_ID)) {
            throw new UserIsNotAdminException(idAdmin);
        }

        Order order = orderDao.findById(idOrder)
                .orElseThrow(() -> new OrderNotFoundException(idOrder));
        order.setAdmin(admin);
        order.setStatus(status);

        if (!status) {
            if (refuseReason == null || refuseReason.isBlank()) {
                throw new RuntimeException("You did not write the reason of refuse");
            }
            order.setRefuseReason(refuseReason);
        }
        orderDao.save(order);
        return orderMapper.toOrderResp(order);
    }

    private Order updateOrder(OrderResp dto, Order model) throws UserNotFoundException, CarNotFoundException {
        return model.changer()
                .admin(userDao.findById(dto.getIdAdmin())
                        .orElseThrow(() -> new UserNotFoundException(dto.getIdAdmin())))
                .price(dto.getPrice())
                .startDate(dto.getStartDate())
                .finishDate(dto.getFinishDate())
                .status(dto.getStatus())
                .refuseReason(dto.getRefuseReason())
                .car(carDao.findById(dto.getIdCar()).orElseThrow(() -> new CarNotFoundException(dto.getIdCar())))
                .change();
    }
}
