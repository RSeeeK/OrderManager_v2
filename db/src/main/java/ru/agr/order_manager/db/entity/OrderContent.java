package ru.agr.order_manager.db.entity;

import ru.agr.order_manager.db.meta.OrderManager;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Сущность "Состав заказа" (ru.agr.ordermanager.entity OrderContent)
 * предназначена для сохрания данных в таблицу БД "ordercontent",
 * представляет собой единицу состава заказа,
 * хранит в себе данные по продукции, цене, количеству и сумме,
 * имеет связь многие к одному с сущностью "Заказ" (ru.agr.ordermanager.entity Order) и связь многие к одному c сущностью "Прайс-лист" (ru.agr.ordermanager.entity Product).
 *
 * @author Rabadanov A.G.
 */
@XmlRootElement(name="ordercontent")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "ORDERSCONTENT")
@Table(name = OrderManager.ordercontent.name)
@NamedQueries({
        @NamedQuery(name="allOrdersContent", query="SELECT content FROM ORDERSCONTENT content"),
        @NamedQuery(name="allOrdersContentByOrder", query="SELECT content FROM ORDERSCONTENT content WHERE content.order = :order")})
public class OrderContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = OrderManager.ordercontent.seq)
    @SequenceGenerator( sequenceName = OrderManager.ordercontent.seq, allocationSize = 1, name = OrderManager.ordercontent.seq)
    @Column(name = OrderManager.ordercontent.fld.id)
    @XmlElement(required = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = OrderManager.ordercontent.fld.product_id)
    @XmlElement(required = true)
    private Product product;

    @NotNull(message = "Price should not be less than 0")
    @Min(value = 0, message = "Price should not be less than 0")
    @Column(name = OrderManager.ordercontent.fld.price)
    @XmlElement(required = true)
    private BigDecimal price;

    @NotNull(message = "Count should not be less than 0")
    @Min(value = 0, message = "Count should not be less than 0")
    @XmlElement(required = true)
    @Column(name = OrderManager.ordercontent.fld.count)
    private BigDecimal count;

    @XmlElement(required = true)
    @Column(name = OrderManager.ordercontent.fld.sum)
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = OrderManager.ordercontent.fld.order_id)
    @XmlTransient
    private Order order;

    public OrderContent() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderContent that = (OrderContent) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
