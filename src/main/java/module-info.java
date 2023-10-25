module sio.hlr {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    opens sio.hlr.Entities;

    opens sio.hlr to javafx.fxml;
    exports sio.hlr;
    exports sio.hlr.Controller;
    opens sio.hlr.Controller to javafx.fxml;
}