package agh.ics.oop;

import java.util.HashMap;

public class RectangularMap extends AbstractWorldMap{
    private final int width;
    private final int height;

    public RectangularMap(int width, int height){
        this.width = width;
        this.height = height;
        lowerLeft = new Vector2d(0,0);
        upperRight = new Vector2d(this.width-1, this.height-1);
        this.animals = new HashMap<>();
        this.visualizer = new MapVisualizer(this);
    }

    @Override
    boolean checkIfCanMove(Vector2d position) {
        return position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    boolean checkIfCanPlace(Animal animal) {
        throw new IllegalArgumentException("Position: " + animal.getPosition().toString() + " is occupied");
    }

    @Override
    boolean checkIfOccupied(Vector2d position) {
        return false;
    }

    @Override
    Object checkObjectAt(Vector2d position) {
        return null;
    }

    @Override
    public Vector2d checkLowerLeft() {
        return this.lowerLeft;
    }

    @Override
    public Vector2d checkUpperRight() {
        return this.upperRight;
    }

}
