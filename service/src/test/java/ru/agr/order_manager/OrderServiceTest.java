package ru.agr.order_manager;

import ru.agr.order_manager.db.entity.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.agr.order_manager.service.OrderService;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

public class OrderServiceTest  extends BaseServiceTest{

    @Inject
    OrderService orderService;

    @Before
    public void initTest() {

    }

    @After
    public void finishTest() {

    }

    @Test
    public void testAddOrder() throws Exception {
        String email = "vasya_test@inbox.ru";

        Order order = orderService.getOrderByEmail(email);
        if (order != null) {
            orderService.removeOrder(order.getId());
        }

        order = new Order();
        order.setNumber(102032);
        order.setCustomerEmail(email);

        orderService.addOrder(order);

        Order orderFromDatabase = orderService.getOrderByEmail(order.getCustomerEmail());

        assertThat(orderFromDatabase, not(nullValue()));
    }

}
