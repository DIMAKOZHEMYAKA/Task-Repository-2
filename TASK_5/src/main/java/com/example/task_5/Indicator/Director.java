package com.example.task_5.Indicator;

public class Director {
    public Indicator construct(Builder builder, float end, float pointer) {

        builder = new BuilderSlideshow();

        builder.addTitle("Счетчик");

        builder.lineMark(String.format("%.1f", pointer));
        builder.setMark(pointer);
        builder.lineBounds(0, end);
        Indicator indicator = builder.build();
        return indicator;
    }
}
