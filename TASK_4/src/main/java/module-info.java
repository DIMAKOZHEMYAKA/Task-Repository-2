module com.example.task_4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task_4 to javafx.fxml;
    exports com.example.task_4;
}