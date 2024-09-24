package model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class OrderDetails {
    @Getter
    @Setter
    @Id
    private UUID uuid;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "orderDetails"
    )

    private List<OrderItem> orderItems = new ArrayList<>();

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private Status status;

    public OrderDetails addOrderItem(OrderItem orderItem) {
        orderItem.setOrderDetails(this);
        orderItems.add(orderItem);
        return this;
    }

    public OrderDetails removeOrderItem(OrderItem orderItem) {
        orderItem.setOrderDetails(null);
        orderItems.remove(orderItem);
        return this;
    }

    public BigDecimal calculateTotalPrice() {
        return orderItems.stream()
                .map(orderItem -> {
                    BigDecimal price = orderItem.getProduct().getPrice();
                    return price.multiply(new BigDecimal(orderItem.getQuantity()));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public OrderDetails addCustomer(Customer customer){
        this.customer = customer;
        return this;
    }

    public int getProductCount() {
        return orderItems.size();
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "uuid=" + uuid +
                ", status=" + status +
                '}';
    }
}
