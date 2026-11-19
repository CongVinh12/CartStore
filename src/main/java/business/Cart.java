package business;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {
    private ArrayList<LineItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public ArrayList<LineItem> getItems() {
        return items;
    }

    public int getCount() {
        return items.size();
    }

    public void addItem(LineItem item, boolean isUpdate) {
        String code = item.getProduct().getCode();
        int quantity = item.getQuantity();
        for (LineItem cartItem : items) {
            if (cartItem.getProduct().getCode().equals(code)) {
                if (isUpdate) {
                    // Nếu bấm nút Update trong giỏ hàng -> ghi đè số lượng mới
                    cartItem.setQuantity(quantity);
                } else {
                    // Nếu bấm nút Add To Cart -> cộng dồn thêm số lượng
                    cartItem.setQuantity(cartItem.getQuantity() + quantity);
                }
                return;
            }
        }
        items.add(item);
    }

    public void removeItem(LineItem item) {
        String code = item.getProduct().getCode();
        for (int i = 0; i < items.size(); i++) {
            LineItem lineItem = items.get(i);
            if (lineItem.getProduct().getCode().equals(code)) {
                items.remove(i);
                return;
            }
        }
    }
}