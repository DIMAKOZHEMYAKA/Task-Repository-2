package com.example.task_4;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Iterator;

public class PlayerController {
    @FXML
    ImageView screen;

    public ConcreteAggregate concreteAggregate = new ConcreteAggregate("img");
    public Iterator iterator = concreteAggregate.getIterator();
    public Timeline time = new Timeline();

    public void initialize() {
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(new KeyFrame(Duration.seconds(1), _ -> {
            if (iterator.hasNext())
                screen.setImage((Image)
                        iterator.next());
        }));
    }
    @FXML
    public void play() {
        time.play();
    }
    public void pause() {
        time.pause();
    }
    public void stop() {
        time.stop();
    }
    public void start() {
        iterator = concreteAggregate.getIterator();
        screen.setImage((Image) iterator.next());
    }
    public void end() {
        Image last = null;
        while (iterator.hasNext()) {
            last = (Image) iterator.next();
        }
        if (last != null)
            screen.setImage(last);
    }


}