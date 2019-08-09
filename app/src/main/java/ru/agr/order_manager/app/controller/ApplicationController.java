package ru.agr.order_manager.app.controller;


import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


/**
 * Главный контроллер приложения, существует в течении сессии,
 * упрвляет жизненным циклом контроллеров "OrderController", "OrderContentController", "ProductController".
 *
 * @author Rabadanov A.G.
 */
@SessionScoped
@Named
public class ApplicationController implements Serializable {
    @Inject
    OrderController orderController;

    @Inject
    OrderContentController orderContentController;

    @Inject
    ProductController productController;

    public void preRenderViewEvent() {

        if (!FacesContext.getCurrentInstance().isPostback()
                && orderController.isTransientConversation()) {
            orderController.beginConversation();
        }

    }

    public void preRenderViewOrderEvent() {
        if (!FacesContext.getCurrentInstance().isPostback()
                && orderContentController.isTransientConversation()) {
            orderContentController.beginConversation();
        }

        if (!FacesContext.getCurrentInstance().isPostback()
                && productController.isTransientConversation()) {
            productController.beginConversation();
        }
    }

    public void preRenderViewOrderContentEvent() {

    }

    public String saveData() {
        orderController.setOrderSum(orderContentController.getOrderSum());
        orderController.saveOrder();
        orderContentController.saveOrderContent();
        orderContentController.endConversation();
        productController.endConversation();
        return "Index.xhtml?faces-redirect=true";
    }

    public String cancelDataEdit() {
        orderContentController.endConversation();
        productController.endConversation();
        return "Index.xhtml?faces-redirect=true";
    }

}
