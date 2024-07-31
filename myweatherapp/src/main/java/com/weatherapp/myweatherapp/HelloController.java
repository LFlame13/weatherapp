package com.weatherapp.myweatherapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class HelloController {

    @FXML
    private AnchorPane black;

    @FXML
    private TextField city;

    @FXML
    private Button getData;

    @FXML
    private Text info;

    @FXML
    private Text pressure;

    @FXML
    private Text temp_feels;

    @FXML
    private Text temp_info;

    @FXML
    private Text temp_max;

    @FXML
    private Text temp_min;

    @FXML
    private Text weather;

    @FXML
    private URL location; // Добавьте эту строку здесь

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            String apiKey = "7f4f5d7ca7b053930728a71a65e8cc78";
            String apiUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + getUserCity + "&units=metric&APPID=" + apiKey;
            String output = getUrlContent(apiUrl);
            if(!output.isEmpty()) {
                JSONObject obj = new JSONObject(output);

                temp_info.setText("Температура: " + obj.getJSONObject("main").getDouble("temp") + "°C");
                temp_feels.setText("Ощущается как: " + obj.getJSONObject("main").getDouble("feels_like") + "°C");
                pressure.setText("Давление: " + obj.getJSONObject("main").getInt("pressure") + " hPa");
                temp_max.setText("Макс. температура: " + obj.getJSONObject("main").getDouble("temp_max") + "°C");
                temp_min.setText("Мин. температура: " + obj.getJSONObject("main").getDouble("temp_min") + "°C");

                double temp = obj.getJSONObject("main").getDouble("temp");
                if (temp > 10) {
                    System.out.println("Можно ехать");
                }
            }


            System.out.println("Все работает!");
        });
    }

    private static String getUrlContent(String urlAddress) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(urlAddress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Такой город не был найден!");
        }
        return content.toString();
    }
}
