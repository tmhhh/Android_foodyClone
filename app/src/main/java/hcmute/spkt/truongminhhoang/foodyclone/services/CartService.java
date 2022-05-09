package hcmute.spkt.truongminhhoang.foodyclone.services;

import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.classes.Cart;
import hcmute.spkt.truongminhhoang.foodyclone.classes.CartItem;

public class CartService {
    public static Cart cart = new Cart();

    public static boolean addToCart(CartItem item) {
        return cart.addToCart(item);
    }

    public static boolean removeCart(CartItem item) {
        return cart.removeCart(item);
    }

    public static int getTotal() {
        return cart.getTotal();
    }
}
