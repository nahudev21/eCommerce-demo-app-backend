package com.nahudev.electronic_shop.service.order;

import com.nahudev.electronic_shop.model.Order;

import java.util.List;

public interface IOrderService {

  public Order placeOrder(Long userId);

  public Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
