package agh.ics.oop;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField extends AbstractWorldMap{

    private final int grassQuantity;
    private final int grassUpperRange;
    private final Map<Vector2d, Grass> grasses;

    public GrassField(int grassQuantity){
        this.grassQuantity = grassQuantity;
        this.grassUpperRange = (int) Math.sqrt(this.grassQuantity*10);
        this.lowerLeft = new Vector2d(Integer.MIN_VALUE,Integer.MIN_VALUE);
        this.upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.visualizer = new MapVisualizer(this);
        this.grasses = new HashMap<Vector2d, Grass>();

        placeGrass(this.grassQuantity);
    }
    void placeGrass(int grassQuantity){
        Random random = new Random();
        int grassesPlaced = 0;

        while( grassesPlaced < grassQuantity){
            int x = random.nextInt(this.grassUpperRange);
            int y = random.nextInt(this.grassUpperRange);

            if( !isOccupied(new Vector2d(x, y))){
                this.grasses.put(new Vector2d(x,y), new Grass(new Vector2d(x,y)));
                this.mapBoundary.add(new Vector2d(x,y));
                grassesPlaced++;
            }
        }
    }
    @Override
    boolean checkIfCanMove(Vector2d position) {
        if( !isOccupied(position) ){
            return true;
        }
        else{
            Object object = objectAt(position);

            if(object instanceof Grass){
                this.grasses.remove(position);
                this.mapBoundary.remove(position);
                placeGrass(1);
                return true;
            }
            else{
                return false;
            }
        }
    }

    @Override
    boolean checkIfCanPlace(Animal animal) {
        Object object = objectAt(animal.getPosition());

        if( object instanceof Grass ){
            this.grasses.remove(animal.getPosition());
            this.animals.put(animal.getPosition(), animal);
            placeGrass(1);
            return true;
        }
        else{
            throw new IllegalArgumentException("Position: " + animal.getPosition().toString() + " is occupied");
        }
    }

    @Override
    boolean checkIfOccupied(Vector2d position) {
        if( grasses.get(position) != null ){
            return true;
        }

        return false;
    }

    @Override
    Object checkObjectAt(Vector2d position) {
        return grasses.get(position);
    }

    @Override
    public Vector2d checkLowerLeft() {
        return this.mapBoundary.getLowerLeft();
    }

    @Override
    public Vector2d checkUpperRight() {
        return this.mapBoundary.getUpperRight();
    }

}
