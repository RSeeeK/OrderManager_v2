package ru.agr.order_manager.app.controller;

import ru.agr.order_manager.db.entity.OrderContent;
import ru.agr.order_manager.OrderContentService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер "Состав заказа", существует в течении диалоги,
 * управляется главным контроллером "ApplicationController",
 * реализует бизнес логику операций с составаом заказа,
 * использует сервисный слой "orderContentService", зависит от "OrderController", управляет "ProductController"
 *
 * @author Rabadanov A.G.
 */
@ConversationScoped
@Named
public class OrderContentController implements Serializable {
    @Inject
    private Conversation conversation;

    @Inject
    private OrderContentService orderContentService;

    @Inject
    private OrderController orderController;

    @Inject
    private ProductController productController;

    private OrderContent orderContent;
    private Integer orderContentId;
    private List<OrderContent> currentOrderContent;
    private List<OrderContent> deletedOrderContent;
    private boolean editing;

    void beginConversation() {
        conversation.begin();
    }

    void endConversation() {
        if (!isTransientConversation()) {
            conversation.end();
        }
    }

    boolean isTransientConversation() {
        return conversation.isTransient();
    }

    public OrderContent getOrderContent() {
        if (orderContent == null) {
            orderContent = new OrderContent();
        }
        return orderContent;
    }

    public void setOrderContent(OrderContent orderContent) {
        this.orderContent = orderContent;
    }

    public List<OrderContent> getCurrentOrderContent() {
        if (currentOrderContent == null) {
            refreshCurrentOrderContent();
        }
        return currentOrderContent;
    }

    public Integer getOrderContentId() {
        return orderContentId;
    }

    public void setOrderContentId(Integer orderContentId) {
        this.orderContentId = orderContentId;
    }

    BigDecimal getOrderSum() {
        BigDecimal sum = new BigDecimal(0);
        for (OrderContent content :
                currentOrderContent) {
            sum = sum.add(content.getSum());
        }
        return sum;
    }

    void saveOrderContent() {
        //ADD, UPDATE ORDER CONTENT
        for (OrderContent content:currentOrderContent) {
            if (content.getId() == null) {
                orderContentService.addOrderContent(content);
            } else {
                orderContentService.updateOrderContent(content);
            }
        }
        //REMOVE ORDER CONTENT
        for (OrderContent content:deletedOrderContent
             ) {
            if (content.getId() != null) {
                orderContentService.removeOrderContent(content.getId());
            }
        }
    }

    public String addNewOrderContent() {
        orderContent = new OrderContent();
        editing = false;
        productController.setSelectedProductID("");
        return "Edit-OrderContent.xhtml";
    }

    public void removeOrderContent(OrderContent orderContent) {
        deletedOrderContent.add(orderContent);
        currentOrderContent.remove(orderContent);
    }

    public String addOrderContent() {
        orderContent.setOrder(orderController.getOrder());
        orderContent.setProduct(productController.getProduct());
        if (!editing) {
            currentOrderContent.add(orderContent);
        } else {
            editing = false;
        }
        orderContent = null;
        orderContentId = null;
        return "Edit-Order.xhtml?faces-redirect=true";
    }

    public String cancelOrderContentEdit() {
        orderContent = null;
        orderContentId = null;
        return "Edit-Order.xhtml?faces-redirect=true";
    }

    public String editCurrentOrderContent(OrderContent orderContent) {
        orderContentId = orderContent.getId();
        this.orderContent = orderContent;
        if (orderContent.getProduct() != null) {
            productController.setSelectedProductID(Integer.toString(orderContent.getProduct().getId()));
        }
        editing = true;
        return "Edit-OrderContent.xhtml";
    }

    private void refreshCurrentOrderContent() {
        currentOrderContent = orderContentService.getOrdersContentByOrder(orderController.getOrder());
        deletedOrderContent = new ArrayList<>();
    }

    private void calculateSum() {
        BigDecimal currentCount = (orderContent.getCount() == null) ? new BigDecimal(0) : orderContent.getCount();
        BigDecimal currentPrice = (orderContent.getPrice() == null) ? new BigDecimal(0) : orderContent.getPrice();
        orderContent.setSum(currentCount.multiply(currentPrice));
    }

    public void onChangeCount(AjaxBehaviorEvent event) {
        calculateSum();
    }

    public void onChangePrice(AjaxBehaviorEvent event) {
        calculateSum();
    }

    public void onChangeSelectedProduct(AjaxBehaviorEvent event) {
        orderContent.setPrice(productController.getProduct().getPrice());
        calculateSum();
    }
}
