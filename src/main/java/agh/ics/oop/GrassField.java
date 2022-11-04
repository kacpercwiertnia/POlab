package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Objects;

public class GrassField extends AbstractWorldMap{

    private final int grassQuantity;
    private final int grassUpperRange;
    private final List<Grass> grasses;

    public GrassField(int grassQuantity, Vector2d[] positions){
        this.grassQuantity = grassQuantity;
        this.grassUpperRange = (int) Math.sqrt(this.grassQuantity*10);
        this.lowerLeft = new Vector2d(0,0);
        this.upperRight = new Vector2d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.visualizer = new MapVisualizer(this);
        this.grasses = new ArrayList<Grass>();
        Random random = new Random();

        int grassesPlaced = 0;

        while( grassesPlaced < this.grassQuantity){
            int x = random.nextInt(this.grassUpperRange);
            int y = random.nextInt(this.grassUpperRange);

            if( !isOccupied(new Vector2d(x, y))){
                boolean placed = false;

                for( Vector2d position: positions ){
                    if( Objects.equals(position, new Vector2d(x, y))){
                        placed = true;
                        break;
                    }
                }

                if(!placed){
                    this.grasses.add(new Grass(new Vector2d(x,y)));
                    grassesPlaced++;
                }
            }
        }
    }
    @Override
    boolean checkIfCanMove(Vector2d position) {
        if( !isOccupied(position)){
            return true;
        }
        else{
            Object object = objectAt(position);

            if(object instanceof Grass){
                for( Grass grass: this.grasses ){
                    if( grass.getPosition().equals(position)){
                        this.grasses.remove(grass);
                        break;
                    }
                }
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
            for( Grass grass: this.grasses ){
                if( grass.getPosition().equals(animal.getPosition())){
                    this.grasses.remove(grass);
                    break;
                }
            }

            this.animals.add(animal);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    boolean checkIfOccupied(Vector2d position) {
        for( Grass grass: this.grasses ){
            if( grass.getPosition().equals(position) ){
                return true;
            }
        }

        return false;
    }

    @Override
    Object checkObjectAt(Vector2d position) {
        for( Grass grass: this.grasses ){
            if( grass.getPosition().equals(position) ){
                return grass;
            }
        }

        return null;
    }

    @Override
    Vector2d checkLowerLeft() {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for( Grass grass: this.grasses ){
            if( grass.getPosition().x < minX ){
                minX =  grass.getPosition().x;
            }

            if( grass.getPosition().y < minY ){
                minY = grass.getPosition().y;
            }
        }

        for( Animal animal: this.animals ) {
            if (animal.getPosition().x < minX) {
                minX = animal.getPosition().x;
            }

            if (animal.getPosition().y < minY) {
                minY = animal.getPosition().y;
            }
        }

        return new Vector2d(minX, minY);
    }

    @Override
    Vector2d checkUpperRight() {
        int maxX = 0;
        int maxY = 0;

        for( Animal animal: this.animals ){
            if( animal.getPosition().x > maxX ){
                maxX = animal.getPosition().x;
            }

            if( animal.getPosition().y > maxY ){
                maxY = animal.getPosition().y;
            }
        }

        for( Grass grass: this.grasses ){
            if( grass.getPosition().x > maxX ){
                maxX =  grass.getPosition().x;
            }

            if( grass.getPosition().y > maxY ){
                maxY = grass.getPosition().y;
            }
        }

        return new Vector2d(maxX, maxY);
    }
}
