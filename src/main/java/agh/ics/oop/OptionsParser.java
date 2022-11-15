package agh.ics.oop;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        int size = 0;

        for( String arg: args ){
            if( arg.equals("f") || arg.equals("forward") || arg.equals("b") || arg.equals("backward") || arg.equals("l") || arg.equals("left") || arg.equals("r") || arg.equals("right")){
                size++;
            }
        }

        MoveDirection[] directions = new MoveDirection[size];
        int i = 0;

        for( String arg: args ){
            switch (arg){
                case "f", "forward":
                    directions[i] = MoveDirection.FORWARD;
                    i++;
                    break;
                case "b", "backward":
                    directions[i] = MoveDirection.BACKWARD;
                    i++;
                    break;
                case "l", "left":
                    directions[i] = MoveDirection.LEFT;
                    i++;
                    break;
                case "r", "right":
                    directions[i] = MoveDirection.RIGHT;
                    i++;
                    break;
                default:
                    throw new IllegalArgumentException(arg + " is not legal move specification");
            }
        }

        return directions;
    }
}
