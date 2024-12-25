package com.example.task_5.Indicator;

public interface Builder {
    public void setView(int N, char norm, char select);

    public void lineBounds(float start, float stop);

    public void linePaint(float mesuare);

    public void lineMark(String mesuare);

    public void addTitle(String name);

    public Indicator build();

    public void setMark(float mark);
}
