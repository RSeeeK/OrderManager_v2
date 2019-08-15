package ru.agr.order_manager.rest;

import ru.agr.order_manager.db.entity.Product;
import ru.agr.order_manager.service.ProductService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Ресурс сервиса "Прайс-лист" (Product)
 * реализует функционал API по взаимодействию с сущностью "Прайс-лист" (Product)
 * использует сервисный слой доступа к базе данных ProductService
 *
 * @author Rabadanov A.G.
 */

@Path("/product")
@Stateless
public class ProductResource {

    @Inject
    ProductService productService;

    @GET()
    @Produces(MediaType.TEXT_XML)
    @Path("/products")
    public List<Product> getProducts() {
        return productService.getListOfProducts();
    }

    @GET
    @Path("get/id={id}")
    @Produces(value = MediaType.TEXT_XML)
    public Response get(@PathParam("id") Integer id) {
        return Response.ok(productService.getProductByID(id)).build();
    }

}
