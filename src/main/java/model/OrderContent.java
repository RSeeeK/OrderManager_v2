package model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Сущность "Состав заказа" (entity OrderContent)
 * предназначена для сохрания данных в таблицу БД "ordercontent",
 * представляет собой единицу состава заказа,
 * хранит в себе данные по продукции, цене, количеству и сумме,
 * имеет связь многие к одному с сущностью "Заказ" (entity Order) и связь многие к одному c сущностью "Прайс-лист" (entity Product).
 *
 * @author Rabadanov A.G.
 */
@XmlRootElement(name="ordercontent")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "ORDERSCONTENT")
@Table(name = "ordercontent")
@NamedQueries({
        @NamedQuery(name="allOrdersContent", query="SELECT content FROM ORDERSCONTENT content"),
        @NamedQuery(name="allOrdersContentByOrder", query="SELECT content FROM ORDERSCONTENT content WHERE content.order = :order")})
public class OrderContent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement(required = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @XmlElement(required = true)
    private Product product;

    @NotNull(message = "Price should not be less than 0")
    @Min(value = 0, message = "Price should not be less than 0")
    @XmlElement(required = true)
    private BigDecimal price;

    @NotNull(message = "Count should not be less than 0")
    @Min(value = 0, message = "Count should not be less than 0")
    @XmlElement(required = true)
    private BigDecimal count;

    @XmlElement(required = true)
    private BigDecimal sum;

    @ManyToOne
    @JoinColumn(name = "order_id")
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
