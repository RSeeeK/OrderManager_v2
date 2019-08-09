package api;


import entity.OrderContent;
import service.OrderContentService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Ресурс сервиса "Состав заказа" (OrderContent)
 * реализует функционал API по взаимодействию с сущностью "Состав заказа" (OrderContent)
 * использует сервисный слой доступа к базе данных OrderContentService
 *
 * @author Rabadanov A.G.
 */
@RequestScoped
@Path("ordercontent")
public class OrderContentResource {

    @Inject
    OrderContentService orderContentService;

    @GET()
    @Produces(MediaType.APPLICATION_XML)
    @Path("/contents")
    public List<OrderContent> getOrdersContent() {
        return orderContentService.getListOfOrdersContent();
    }

    @GET
    @Path("get/id={id}")
    @Produces(value = MediaType.TEXT_XML)
    public Response get(@PathParam("id") Integer id) {
        return Response.ok(orderContentService.getOrderContentById(id)).build();
    }
}
