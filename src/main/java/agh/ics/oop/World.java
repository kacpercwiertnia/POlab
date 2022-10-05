package agh.ics.oop;

public class World {
    public static Direction[] translate(String[] args) {
        int size = args.length;
        Direction[] results = new Direction[size];

        for (int i = 0; i < size; i++) {
            switch (args[i]) {
                case "f":
                    results[i] = Direction.FORWARD;
                    break;
                case "b":
                    results[i] = Direction.BACKWARD;
                    break;
                case "r":
                    results[i] = Direction.RIGHT;
                    break;
                case "l":
                    results[i] = Direction.LEFT;
                    break;
                default:
                    results[i] = null;
                    break;
            }
        }

        return results;
    }
    public static void run(Direction[] directions){

        for(Direction dir: directions){
            switch (dir){
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                default:
                    break;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Start");
        Direction[] directions = translate(args);
        run(directions);
        System.out.println("Stop");
    }
}
