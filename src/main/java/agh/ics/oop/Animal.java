package agh.ics.oop;

public class Animal {
    private MapDirection direction;
    private Vector2d coordinates;
    private IWorldMap map;
    public Animal(IWorldMap map, Vector2d initialPosition){
        this.direction = MapDirection.NORTH;
        this.map = map;
        this.coordinates = initialPosition;
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
                return this.coordinates.toString()+" N";
            case EAST:
                return this.coordinates.toString()+" E";
            case SOUTH:
                return this.coordinates.toString()+" S";
            case WEST:
                return this.coordinates.toString()+" W";
            default:
                return "ERR";
        }
    }

    public boolean isAt(Vector2d position){
        return this.coordinates.equals(position);
    }

    public MapDirection getDirection(){
        return this.direction;
    }

    public Vector2d getCoordinates(){
        return this.coordinates;
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
                if(map.canMoveTo(this.coordinates.add(this.direction.toUnitVector()))){
                    this.coordinates = this.coordinates.add(this.direction.toUnitVector());
                }
                break;
            case BACKWARD:
                if(map.canMoveTo(this.coordinates.subtract(this.direction.toUnitVector()))){
                    this.coordinates = this.coordinates.subtract(this.direction.toUnitVector());
                }
                break;
            default:
                break;
        }
    }
}
