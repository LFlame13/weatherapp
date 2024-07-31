module com.weatherapp.myweatherapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;


    opens com.weatherapp.myweatherapp to javafx.fxml;
    exports com.weatherapp.myweatherapp;
}