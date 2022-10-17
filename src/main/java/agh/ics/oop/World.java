package agh.ics.oop;

public class World {
    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal.toString());

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        System.out.println(animal.toString());

        MoveDirection[] directions = new OptionsParser().parse(args);

        for( MoveDirection dir: directions){
            animal.move(dir);
            System.out.println(animal.toString());
        }
    }
}
