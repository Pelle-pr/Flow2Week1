package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Entity
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @ManyToOne
    private Customer customer;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "orders")
    private List<OrderLine> orderLineList;


    public Orders(Customer customer, List<OrderLine> orderLineList) {
        this.customer = customer;
        this.orderLineList = orderLineList;
    }

    public Orders() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customers) {
        this.customer = customers;
    }
}