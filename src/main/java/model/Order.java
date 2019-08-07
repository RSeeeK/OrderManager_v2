package model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Сущность "Заказ" (entity Order)
 * предназначена для сохрания данных в таблицу БД "orders",
 * представляет собой единицу заказа клиента,
 * хранит в себе номер заказа, е-майл адрес, время создания заказа,
 * имеет связь 1 ко многим с сущностью "Состав заказа" (entity OrderContent).
 *
 * @author Rabadanov A.G.
 */
@XmlRootElement(name="order")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "ORDERS")
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name="allOrders", query="SELECT o FROM ORDERS o")
})
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(required=true)
    private Integer id;

    @NotNull(message = "Number cannot be empty")
    @XmlElement(required=true)
    private Integer number;

    @Column(name="ordertime")
    @Temporal(TemporalType.TIMESTAMP)
    @XmlElement(required=true)
    private Date orderTime;

    @PrePersist
    protected void onCreate() {
        orderTime = new Date();
    }

    @Column(name="customeremail")
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    @XmlElement(required=true)
    private String customerEmail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlElement(required=true)
    private List<OrderContent> orderContents = new ArrayList<>();

    @Column(name="ordersum")
    @XmlElement(required=true)
    private BigDecimal orderSum;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<OrderContent> getOrderContents() {
        return orderContents;
    }

    public void setOrderContents(List<OrderContent> orderContents) {
        this.orderContents = orderContents;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public BigDecimal getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(BigDecimal orderSum) {
        this.orderSum = orderSum;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return id != null ? id.equals(order.id) : order.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
