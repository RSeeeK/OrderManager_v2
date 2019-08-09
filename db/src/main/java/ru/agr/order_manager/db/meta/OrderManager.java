package ru.agr.order_manager.db.meta;

public class OrderManager {

    public static final class order {
        public static final String name = "orders";
        public static final String seq = "seq_order_id";

        public static final class fld {
            public static final String id = "id";
            public static final String number = "number";
            public static final String ordertime = "ordertime";
            public static final String customeremail = "customeremail";
            public static final String ordersum = "ordersum";
        }
    }

    public static final class ordercontent {
        public static final String name = "ordercontent";
        public static final String seq = "seq_ordercontent_id";

        public static final class fld {
            public static final String id = "id";
            public static final String product_id = "product_id";
            public static final String price = "price";
            public static final String count = "count";
            public static final String sum = "sum";
            public static final String order_id = "order_id";
        }
    }

    public static final class product {
        public static final String name = "products";
        public static final String seq = "seq_product_id";

        public static final class fld {
            public static final String id = "id";
            public static final String name = "name";
            public static final String price = "price";
        }
    }
}
