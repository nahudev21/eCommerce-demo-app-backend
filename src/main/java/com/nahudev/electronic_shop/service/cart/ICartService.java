package com.nahudev.electronic_shop.service.cart;

import com.nahudev.electronic_shop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {

    public Cart getCart(Long cartId);

    public void clearCart(Long cartId);

    public BigDecimal getTotalPrice(Long cartId);

    public Long initializeNewCart();
}
