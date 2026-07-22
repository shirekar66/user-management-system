package com.scode.User_Management.service;

import com.scode.User_Management.dto.CreateOrderDto;
import com.scode.User_Management.dto.OrderDto;
import com.scode.User_Management.entity.Order;
import com.scode.User_Management.entity.User;
import com.scode.User_Management.repositories.OrderRepo;
import com.scode.User_Management.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final UserRepo userRepo;

    @Transactional
    public OrderDto createOrder(Long userId, CreateOrderDto createOrderDto) {
        User user = userRepo.findById(userId).orElseThrow();
        Order order = new Order();
        order.setUser(user);
        order.setProductName(createOrderDto.getProductName());

        Order saveOrder = orderRepo.save(order);
        return new OrderDto(saveOrder.getId(), saveOrder.getProductName(), saveOrder.getUser());
    }

    public List<OrderDto> getOrderByUser(Long userId) {
        List<Order> orders = orderRepo.findByUserId(userId);
        List<OrderDto> orderDtos = new ArrayList<>();
        orders.forEach(order -> {
            OrderDto orderDto = new OrderDto(order.getId(), order.getProductName(), order.getUser());
            orderDtos.add(orderDto);
        });
        return orderDtos;
    }

    public OrderDto updateOrderByUser(Long userId, Long orderId, CreateOrderDto updateOrderDto) {
        userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User Not found with id: " + userId));
        Order order = orderRepo.findByIdAndUserId(orderId,userId).orElseThrow(() -> new RuntimeException("Order Not found for the selected user"));
        order.setProductName(updateOrderDto.getProductName());
        Order updatedOrder = orderRepo.save(order);
        return new OrderDto(updatedOrder.getId(), updatedOrder.getProductName(), updatedOrder.getUser());
    }

    public void deleteOrder(Long userId, Long orderId){
        Order order = orderRepo.findByIdAndUserId(orderId,userId).orElseThrow(() -> new RuntimeException("order id not found: " + orderId));
        orderRepo.delete(order);
    }

    /*@Override
    public List<OrderDto> getAllOrder() {
        return orderRepo.findAll().stream()
                .map(order -> new OrderDto(order.getId(), order.getProductName(), order.getUser())).toList();
    }*/
}
