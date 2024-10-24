package com.nahudev.electronic_shop.service.cart;

import com.nahudev.electronic_shop.exceptions.ResourceNotFoundException;
import com.nahudev.electronic_shop.model.Cart;
import com.nahudev.electronic_shop.model.CartItem;
import com.nahudev.electronic_shop.repository.ICartItemRepository;
import com.nahudev.electronic_shop.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService{

    private final ICartRepository cartRepository;

    private final ICartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long cartId) {

        Cart cart = cartRepository.findById(cartId).orElseThrow(() ->
                new ResourceNotFoundException("Cart not found!"));

        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);

        return cartRepository.save(cart);
    }

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
}
