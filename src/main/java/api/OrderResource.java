package api;

import model.Order;
import service.OrderService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Ресурс сервиса "Заказ" (Order)
 * реализует функционал API по взаимодействию с сущностью "Заказ" (Order)
 * использует сервисный слой доступа к базе данных OrderService
 *
 * @author Rabadanov A.G.
 */
@RequestScoped
@Path("order")
public class OrderResource {

    @Inject
    OrderService orderService;

    @GET()
    @Produces(MediaType.APPLICATION_XML)
    @Path("/orders")
    public List<Order> getOrders() {
        return orderService.getListOfOrders();
    }

    @GET
    @Path("get/id={id}")
    @Produces(value = MediaType.APPLICATION_XML)
    public Response get(@PathParam("id") Integer id) {
        return Response.ok(orderService.getOrderById(id)).build();
    }

    @DELETE
    @Path("delete/id={id}")
    public Response delete(@PathParam("id") Integer id) {
        orderService.removeOrder(id);
        return Response.noContent().build();
    }
}
