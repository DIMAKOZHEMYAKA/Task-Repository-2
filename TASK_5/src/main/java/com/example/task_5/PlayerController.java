package com.example.task_5;

import com.example.task_5.Indicator.BuilderSlideshow;
import com.example.task_5.Indicator.Director;
import com.example.task_5.Indicator.Indicator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.util.Duration;

import java.io.File;
import java.util.Iterator;

public class PlayerController {
    @FXML
    ImageView screen;
    @FXML
    Pane paneIndicator;
    Director director = new Director();

    public ConcreteAggregate concreteAggregate = new ConcreteAggregate("iterator");
    public Iterator iterator = concreteAggregate.getIterator();
    public Timeline time = new Timeline();
    private float sizeImage, correct;

    public void initialize() {
        time.setCycleCount(Timeline.INDEFINITE);
        time.getKeyFrames().add(new KeyFrame(Duration.seconds(2), _ -> {
            if (iterator.hasNext()) {
                screen.setImage((Image)
                        iterator.next());
                sizeImage = concreteAggregate.getSize();
                correct = concreteAggregate.getCorrect();
                Indicator indicator = director.construct(new BuilderSlideshow(), sizeImage, correct);
                paneIndicator.getChildren().clear();
                indicator.show(paneIndicator);
            }
        }));
        sizeImage = concreteAggregate.getSize();
        correct = concreteAggregate.getCorrect();
        Indicator indicator = director.construct(new BuilderSlideshow(), sizeImage, correct);
        paneIndicator.getChildren().clear();
        indicator.show(paneIndicator);
    }

    public void play() {
        time.play();
    }

    public void pause() {
        time.pause();
    }

    public void stop() {
        time.stop();
        start();
    }

    public void start() {
        iterator = concreteAggregate.getIterator();
        screen.setImage(null);
        sizeImage = concreteAggregate.getSize();
        correct = 0;
        Indicator indicator = director.construct(new BuilderSlideshow(), sizeImage, correct);
        paneIndicator.getChildren().clear();
        indicator.show(paneIndicator);
    }

    public void end() {
        Image last = null;
        while (iterator.hasNext()) {
            last = (Image) iterator.next();
        }
        if (last != null)
            screen.setImage(last);
        sizeImage = concreteAggregate.getSize();
        correct = concreteAggregate.getCorrect();
        Indicator indicator = director.construct(new BuilderSlideshow(), sizeImage, correct);
        paneIndicator.getChildren().clear();
        indicator.show(paneIndicator);
    }

    @FXML
    public void open() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выбрать папку");
        File file = directoryChooser.showDialog(screen.getScene().getWindow());
        if (file != null && !file.getPath().isEmpty()) {
            concreteAggregate.setPath(file.getPath());
            iterator = concreteAggregate.getIterator();
            screen.setImage(null);
            sizeImage = concreteAggregate.getSize();
            correct = 0;
            Indicator indicator = director.construct(new BuilderSlideshow(), sizeImage, correct);
            paneIndicator.getChildren().clear();
            indicator.show(paneIndicator);
        }
        stop();
    }


}