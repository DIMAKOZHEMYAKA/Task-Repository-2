package com.example.task_5;

import javafx.scene.image.Image;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

public class ConcreteAggregate extends Aggregate {
    private String name;
    private ImageIterator imageIterator;
    private String path = "src/main/resources/image";

    @Override
    public Iterator getIterator() {
        imageIterator = new ImageIterator();
        imageIterator.setPath(path);
        return imageIterator;
    }

    public float getSize() {
        return imageIterator.getSize();
    }

    public float getCorrect() {
        return imageIterator.current;
    }

    public ConcreteAggregate(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
        imageIterator.setPath(path);
    }

    private class ImageIterator implements Iterator {
        private int current = 0;
        private ArrayList<String> image = new ArrayList<>();
        private ArrayList<String> formatImage = new ArrayList<>();
        private String path;

        private ImageIterator() {
            formatImage.add(".jpg");
            formatImage.add(".png");
            imgFromFile(path);
        }

        private void imgFromFile(String path) {
            if (path != null && !path.isEmpty()) {
                File f = new File(path);
                if (f.isDirectory()) {
                    String[] fContents = f.list();
                    for (int i = 0; i < fContents.length; i++) {
                        for (String item : formatImage) {
                            if (fContents[i].contains(item))
                                image.add(f.getPath() + "\\" + fContents[i]);
                        }
                        if (!fContents[i].contains("."))
                            imgFromFile(f.getPath() + "\\" + fContents[i]);
                    }
                }
            }
        }

        private Image getImage(int iterator) {
            String filename =
                    Paths.get(image.get(iterator - 1)).toUri().toString();
            return new Image(filename);
        }

        protected int getSize() {
            return image.size();
        }

        private void setPath(String path) {
            current = 0;
            image.clear();
            this.path = path;
            imgFromFile(path);
        }

        @Override
        public boolean hasNext() {
            if (image.size() < current + 1) return false;
            return !getImage(current + 1).isError();
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return getImage(++current);
            } else {
                return null;
            }
        }

        public Object preview() {
            current = 1;
            return getImage(current);
        }
    }
}
