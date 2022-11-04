package agh.ics.oop;

public class Animal implements IMapElement{
    private MapDirection direction;
    private Vector2d position;
    private IWorldMap map;
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.direction = MapDirection.NORTH;
        this.map = map;
        this.position = initialPosition;
    }

    public Animal(IWorldMap map){
        this(map, new Vector2d(2,2));
    }

    public Animal(){
        this(new RectangularMap(5,5));
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
                    this.position = this.position.add(this.direction.toUnitVector());
                }
                break;
            case BACKWARD:
                if(map.canMoveTo(this.position.subtract(this.direction.toUnitVector()))){
                    this.position = this.position.subtract(this.direction.toUnitVector());
                }
                break;
            default:
                break;
        }
    }
}
