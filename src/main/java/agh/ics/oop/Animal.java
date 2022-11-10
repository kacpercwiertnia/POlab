package agh.ics.oop;

import java.util.List;
import java.util.ArrayList;


public class Animal implements IMapElement{
    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;

    private final List<IPositionChangeObserver> observers;
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.direction = MapDirection.NORTH;
        this.map = map;
        this.position = initialPosition;
        this.observers = new ArrayList<IPositionChangeObserver>();
        this.observers.add((IPositionChangeObserver) map);
    }

    public Animal(IWorldMap map){
        this(map, new Vector2d(2,2));
    }

    public String toString(){
        switch (this.direction){
            case NORTH:
                return "^";
            case EAST:
                return ">";
            case SOUTH:
                return "v";
            case WEST:
                return "<";
            default:
                return "ERR";
        }
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public MapDirection getDirection(){
        return this.direction;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public void move(MoveDirection direction){
        switch (direction){
            case RIGHT:
                this.direction = this.direction.next();
                break;
            case LEFT:
                this.direction = this.direction.previous();
                break;
            case FORWARD:
                if(map.canMoveTo(this.position.add(this.direction.toUnitVector()))){
                    this.positionChanged(this.position, this.position.add(this.direction.toUnitVector()));
                    this.position = this.position.add(this.direction.toUnitVector());
                }
                break;
            case BACKWARD:
                if(map.canMoveTo(this.position.subtract(this.direction.toUnitVector()))){
                    this.positionChanged(this.position, this.position.subtract(this.direction.toUnitVector()));
                    this.position = this.position.subtract(this.direction.toUnitVector());
                }
                break;
            default:
                break;
        }
    }

    public void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        for( IPositionChangeObserver observer: this.observers ){
            observer.positionChanged(oldPosition, newPosition);
        }
    }
}
