package com.nahudev.electronic_shop.service.cart;

import com.nahudev.electronic_shop.exceptions.ResourceNotFoundException;
import com.nahudev.electronic_shop.model.Cart;
import com.nahudev.electronic_shop.repository.ICartItemRepository;
import com.nahudev.electronic_shop.repository.ICartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final ICartRepository cartRepository;

    private final ICartItemRepository cartItemRepository;

    private final AtomicLong cartIdGenerator = new AtomicLong(0);

    @Transactional
    @Override
    public Cart getCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(() ->
                new ResourceNotFoundException("Cart not found!"));

        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);

        return cart;
    }

    @Transactional
    @Override
    public void clearCart(Long cartId) {

        Cart cart = getCart(cartId);
        cartItemRepository.deleteAllByCartId(cartId);
        cart.getItems().clear();
        cartRepository.deleteById(cartId);

    }

    @Override
    public BigDecimal getTotalPrice(Long cartId) {

        Cart cart = getCart(cartId);

        return cart.getTotalAmount();
    }

    @Override
    public Long initializeNewCart() {
        Cart newCart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        newCart.setId(newCartId);
        return cartRepository.save(newCart).getId();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

}
