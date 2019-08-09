package entity;

import meta.OrderManager;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Сущность "Прайс-лист" (entity Product)
 * предназначена для сохрания данных в таблицу БД "products",
 * представляет собой единицу прайс-листа,
 * хранит в себе название товара, стоимость,
 * имеет связь многие к одному с сущностью "Состав заказа" (entity OrderContent).
 *
 * @author Rabadanov A.G.
 */
@XmlRootElement(name="product")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity(name = "PRODUCTS")
@Table(name = OrderManager.product.name)
@NamedQueries({
        @NamedQuery(name="allProducts", query="SELECT product FROM PRODUCTS product"),
        @NamedQuery(name="productByID", query="SELECT product FROM PRODUCTS product where product.id = :id")
})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = OrderManager.product.seq)
    @SequenceGenerator( sequenceName = OrderManager.product.seq, allocationSize = 1, name = OrderManager.product.seq)
    @Column(name = OrderManager.product.fld.id)
    @XmlElement(required = true)
    private Integer id;

    @XmlElement(required = true)
    @Column(name = OrderManager.product.fld.name)
    private String name;

    @XmlElement(required = true)
    @Column(name = OrderManager.product.fld.price)
    private BigDecimal price;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
