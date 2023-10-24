module sio.hlr {
    requires javafx.controls;
    requires javafx.fxml;


    opens sio.hlr to javafx.fxml;
    exports sio.hlr;
    exports sio.hlr.Controller;
    opens sio.hlr.Controller to javafx.fxml;
}