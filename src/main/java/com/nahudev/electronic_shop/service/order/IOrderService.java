package com.nahudev.electronic_shop.service.order;

import com.nahudev.electronic_shop.model.Order;

public interface IOrderService {

  public Order placeOrder(Long userId);

  public Order getOrder(Long orderId);

}
