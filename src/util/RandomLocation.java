package util;

import Game.GamePanel;

import java.awt.*;

public class RandomLocation {

    private int max, min;

    public RandomLocation(int min, int max) {
        this.max = max;
        this.min = min;
    }

    public int rand() {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
