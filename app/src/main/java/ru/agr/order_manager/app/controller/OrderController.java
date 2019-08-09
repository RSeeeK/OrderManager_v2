package ru.agr.order_manager.app.controller;

import ru.agr.order_manager.db.entity.Order;
import ru.agr.order_manager.OrderService;


import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Контроллер "Заказ", существует в течении диалоги,
 * управляется главным контроллером "ApplicationController",
 * реализует бизнес логику операций с заказом,
 * использует сервисный слой "orderService"
 *
 * @author Rabadanov A.G.
 */
@ConversationScoped
@Named
public class OrderController implements Serializable{

    @Inject
    private Conversation conversation;

    @Inject
    private OrderService orderService;

    private List<Order> orders;

    private Order order;
    private Integer orderId;

    void beginConversation() {
        conversation.begin();
    }

    public void endConversation() {
        conversation.end();
    }

    boolean isTransientConversation() {
        return conversation.isTransient();
    }

    public List<Order> getOrders() {
        if (orders == null) {
            refreshOrders();
        }
        return orders;
    }

    public Order getOrder() {
        if (order == null) {
            initializeOrder();
        }
        return order;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(final Integer orderId) {
        this.orderId = orderId;
    }

    public String addNewOrder() {
        return "Edit-Order.xhtml";
    }

    public void removeCurrentOrder(Integer id) {
        orderService.removeOrder(id);
        refreshOrders();
    }

    private void refreshOrders() {
        orders = orderService.getListOfOrders();
    }

    public String editCurrentOrder(Order order) {
        orderId = order.getId();
        this.order = order;
        return "Edit-Order.xhtml";
    }

    String saveOrder() {
        if (orderId == null) {
            orderService.addOrder(order);
        } else {
            orderService.updateOrder(order);
        }
        return "Index.xhtml?faces-redirect=true";
    }

    private void initializeOrder() {
        if (orderId == null) {
            order = new Order();
            return;
        }
        order = orderService.getOrderById(orderId);
    }

    void setOrderSum(BigDecimal sum) {
        order.setOrderSum(sum);
    }

    public String getFormattedOrderDate(Date orderDate) {
        if (orderDate == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss yyyy.MM.dd");
        return dateFormat.format(orderDate);
    }
}
