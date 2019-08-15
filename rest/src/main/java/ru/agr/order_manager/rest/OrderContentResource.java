package ru.agr.order_manager.rest;


import ru.agr.order_manager.db.entity.OrderContent;
import ru.agr.order_manager.service.OrderContentService;

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
 * Ресурс сервиса "Состав заказа" (OrderContent)
 * реализует функционал API по взаимодействию с сущностью "Состав заказа" (OrderContent)
 * использует сервисный слой доступа к базе данных OrderContentService
 *
 * @author Rabadanov A.G.
 */

@Path("/ordercontent")
@Stateless
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
    @Produces(value = MediaType.TEXT_XML)
    @Path("get/id={id}")
    public Response get(@PathParam("id") Integer id) {
        return Response.ok(orderContentService.getOrderContentById(id)).build();
    }
}
