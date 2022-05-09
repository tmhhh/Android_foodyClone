package hcmute.spkt.truongminhhoang.foodyclone.classes;

import java.util.ArrayList;
import java.util.List;

import hcmute.spkt.truongminhhoang.foodyclone.enums.CartStatus;

public class Cart {
    private List<CartItem> list = new ArrayList<CartItem>();
    private CartStatus status = CartStatus.PAID;

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    public int getTotal() {
        int total = 0;
        for (CartItem item: list) {
            total += item.getFood().getPrice() * item.getQuantity();
        }
        return total;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public boolean addToCart(CartItem item) {
        return this.list.add(item);
    }

    public boolean removeCart(CartItem item) {
        return this.list.remove(item);
    }
}
