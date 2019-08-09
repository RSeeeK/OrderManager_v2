package controller;

import entity.Product;
import service.ProductService;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Контроллер "Прайс-лист", существует в течении диалоги,
 * управляется главным контроллером "ApplicationController",
 * реализует бизнес логику операций с продукцией прайс-листа,
 * использует сервисный слой "ProductService", зависит от "OrderContentController"
 *
 * @author Rabadanov A.G.
 */
@ConversationScoped
@Named
public class ProductController implements Serializable {

    @Inject
    private Conversation conversation;

    @Inject
    private ProductService productService;

    private String selectedProductID;
    private Product product;
    private List<Product> products;

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

    public List<Product> getProducts() {
        if (products == null) {
            initializeProduct();
        }
        return products;
    }

    public String getSelectedProductID() {
        return selectedProductID;
    }

    public void setSelectedProductID(String selectedProductID) {
        this.selectedProductID = selectedProductID;
        product = null;
        if (!selectedProductID.isEmpty()) {
            Integer product_id = Integer.parseInt(selectedProductID);
            product = productService.getProductByID(product_id);
        }
    }

    Product getProduct() {
        return product;
    }

    private void initializeProduct() {
        products = productService.getListOfProducts();
    }
}
