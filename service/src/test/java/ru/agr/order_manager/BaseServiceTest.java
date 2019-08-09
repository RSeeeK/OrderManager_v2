package ru.agr.order_manager;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BaseServiceTest {

    @Deployment
    public static JavaArchive createDeployments() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
                .addPackages(true,"ru.agr.order_manager")
                .addPackages(true, "org.apache.commons")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");

        return jar;
    }

    @Test
    public void someTest() {

    }

}
