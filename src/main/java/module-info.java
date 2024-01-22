module com.example.gestiondepedidoshibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.sql;
    requires jasperreports;

    opens com.example.gestiondepedidoshibernate.domain.user;
    opens com.example.gestiondepedidoshibernate.domain.orders;
    opens com.example.gestiondepedidoshibernate.domain.products;
    opens com.example.gestiondepedidoshibernate.domain.items;

    opens com.example.gestiondepedidoshibernate to javafx.fxml;
    opens com.example.gestiondepedidoshibernate.controllers to javafx.fxml;

    exports com.example.gestiondepedidoshibernate;
    exports com.example.gestiondepedidoshibernate.controllers;
    exports com.example.gestiondepedidoshibernate.domain.user;
    exports com.example.gestiondepedidoshibernate.domain.orders;
}