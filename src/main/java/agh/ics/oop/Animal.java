package agh.ics.oop;

public class Animal {
    private MapDirection direction = MapDirection.NORTH;
    private Vector2d coordinates = new Vector2d(2,2);

    public String toString(){
        return "Zwierze znajduje się na współrzędnych: " + this.coordinates.toString() + " w kierunku: " + this.direction.toString();
    }

    public boolean isAt(Vector2d position){
        return this.coordinates.equals(position);
    }

    public MapDirection getDirection(){
        return this.direction;
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
                if(this.coordinates.add(this.direction.toUnitVector()).follows(new Vector2d(0,0)) && this.coordinates.add(this.direction.toUnitVector()).precedes(new Vector2d(4,4))) {
                    this.coordinates = this.coordinates.add(this.direction.toUnitVector());
                }
                break;
            case BACKWARD:
                if(this.coordinates.subtract(this.direction.toUnitVector()).follows(new Vector2d(0,0)) && this.coordinates.subtract(this.direction.toUnitVector()).precedes(new Vector2d(4,4))) {
                    this.coordinates = this.coordinates.subtract(this.direction.toUnitVector());
                }
                break;
            default:
                break;
        }
    }
}
