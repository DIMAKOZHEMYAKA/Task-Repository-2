package com.example.task_5.Indicator;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BuilderSlideshow implements Builder{
    private Indicator indicator = new Indicator();
    private float start, stop;
    private float mark;
    private double yOffset = 0; // Смещение по вертикали для элементов

    @Override
    public void setView(int N, char norm, char select) {
        // Логика настройки отображения индикатора (если необходимо)
    }

    @Override
    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public void lineBounds(float start, float stop) {
        this.start = start;
        this.stop = stop;
        FlowPane pane = new FlowPane();

        Text startText = new Text(String.valueOf(start));

        AnchorPane linePane = new AnchorPane();
        double x = (200 - 5) * (mark - start) / (stop - start) + 5; // Вычисление положения на линии (координаты x)

        // Создаем линию
        Line line = new Line(5, 25, 200, 25);

        // Создаем арку
        Arc arc = new Arc();
        arc.setFill(Color.RED);
        arc.setCenterX(x);
        arc.setCenterY(25); // Центрирование арки
        arc.setRadiusX(5);
        arc.setRadiusY(5);
        arc.setLength(360); // Полный круг
        arc.setStartAngle(0);


        Text stopText = new Text(String.valueOf(stop));
        linePane.getChildren().addAll(line,arc);
        pane.getChildren().addAll(startText, linePane, stopText);

        startText.setLayoutY(25);
        stopText.setLayoutY(25);

        pane.setLayoutY(yOffset); // Установка вертикального смещения
        yOffset += 20; // Обновление смещения для следующего элемента
        indicator.add(pane);
    }

    @Override
    public void linePaint(float mesuare) {
        AnchorPane pane = new AnchorPane();
        double x = (200 - 5) * (mesuare - start) / (stop - start) + 5; // Вычисление положения на линии (координаты x)

        // Создаем линию
        Line line = new Line(5, 25, 200, 25);

        // Создаем арку
        Arc arc = new Arc();
        arc.setFill(Color.RED);
        arc.setCenterX(x);
        arc.setCenterY(25); // Центрирование арки
        arc.setRadiusX(5);
        arc.setRadiusY(5);
        arc.setLength(360); // Полный круг
        arc.setStartAngle(0);

        pane.getChildren().addAll(line, arc);
        pane.setLayoutY(yOffset); // Установка вертикального смещения
        yOffset += 50; // Обновление смещения для следующего элемента
        indicator.add(pane);
    }

    @Override
    public void lineMark(String measure) {
        Text measureText = new Text(measure);
        Pane pane = new Pane();
        pane.setPrefSize(200, 50); // Установка фиксированных размеров для Pane
        measureText.setLayoutX(100 - measureText.getBoundsInLocal().getWidth() / 2); // Центрирование текста по горизонтали
        measureText.setLayoutY(25); // Центрирование текста по вертикали
        pane.getChildren().add(measureText);
        pane.setLayoutY(yOffset); // Установка вертикального смещения
        yOffset += 20; // Обновление смещения для следующего элемента
        indicator.add(pane);
    }

    @Override
    public void addTitle(String name) {
        Text title = new Text(name);
        Pane pane = new Pane();
        pane.setPrefSize(200, 50); // Установка фиксированных размеров для Pane
        title.setLayoutX(100 - title.getBoundsInLocal().getWidth() / 2); // Центрирование текста по горизонтали
        title.setLayoutY(25); // Центрирование текста по вертикали
        pane.getChildren().add(title);
        pane.setLayoutY(yOffset); // Установка вертикального смещения
        yOffset += 20; // Обновление смещения для следующего элемента
        indicator.add(pane);
    }

    @Override
    public Indicator build() {
        return indicator;
    }
}
