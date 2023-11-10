module com.example.gestiondepedidoshibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires lombok;
    requires org.hibernate.orm.core;


    opens com.example.gestiondepedidoshibernate to javafx.fxml;
    exports com.example.gestiondepedidoshibernate;
    exports com.example.gestiondepedidoshibernate.controllers;
    opens com.example.gestiondepedidoshibernate.controllers to javafx.fxml;
}