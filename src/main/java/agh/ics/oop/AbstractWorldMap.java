package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver{

    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected MapVisualizer visualizer;
    protected Map<Vector2d, Animal> animals = new HashMap<Vector2d, Animal>();
    protected MapBoundary mapBoundary = new MapBoundary();

    abstract boolean checkIfCanMove(Vector2d position);
    abstract boolean checkIfCanPlace(Animal animal);
    abstract boolean checkIfOccupied(Vector2d position);
    abstract Object checkObjectAt(Vector2d position);

    public abstract Vector2d checkLowerLeft();
    public abstract Vector2d checkUpperRight();

    @Override
    public boolean canMoveTo(Vector2d position) {
        if( position.follows(this.lowerLeft) ){
            return checkIfCanMove(position);
        }

        return false;
    }
    @Override
    public boolean place(Animal animal) {
        if( !animal.getPosition().follows(this.lowerLeft) || !animal.getPosition().precedes(upperRight) ){
            throw new IllegalArgumentException("Position: " + animal.getPosition().toString() + " is outside the map");
        }else if( !isOccupied(animal.getPosition())) {
            this.animals.put(animal.getPosition(), animal);
            this.mapBoundary.add(animal.getPosition());
            return true;
        }

        return checkIfCanPlace(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        if( animals.get(position) != null ){
            return true;
        }

        return checkIfOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if( animals.get(position) != null ){
            return animals.get(position);
        }

        return checkObjectAt(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = this.animals.get(oldPosition);
        this.animals.remove(oldPosition);
        this.animals.put(newPosition, animal);
        this.mapBoundary.positionChanged(oldPosition, newPosition);
    }

    public String toString(){
        return this.visualizer.draw(checkLowerLeft(), checkUpperRight());
    }
}
