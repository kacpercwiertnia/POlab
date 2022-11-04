package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractWorldMap implements IWorldMap{

    protected Vector2d lowerLeft;
    protected Vector2d upperRight;
    protected MapVisualizer visualizer;
    protected List<Animal> animals = new ArrayList<Animal>();

    abstract boolean checkIfCanMove(Vector2d position);
    abstract boolean checkIfCanPlace(Animal animal);
    abstract boolean checkIfOccupied(Vector2d position);
    abstract Object checkObjectAt(Vector2d position);
    abstract Vector2d checkLowerLeft();
    abstract Vector2d checkUpperRight();

    @Override
    public boolean canMoveTo(Vector2d position) {
        if( position.follows(this.lowerLeft) ){
            return checkIfCanMove(position);
        }

        return false;
    }
    @Override
    public boolean place(Animal animal) {
        if( !isOccupied(animal.getPosition())) {
            this.animals.add(animal);
            return true;
        }

        return checkIfCanPlace(animal);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : this.animals) {
            if (animal.isAt(position)) {
                return true;
            }
        }

        return checkIfOccupied(position);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : this.animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }

        return checkObjectAt(position);
    }

    public String toString(){
        return this.visualizer.draw(checkLowerLeft(), checkUpperRight());
    }
}
