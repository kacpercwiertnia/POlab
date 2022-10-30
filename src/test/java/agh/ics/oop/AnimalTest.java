package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void test() {
        Animal animal = new Animal(new RectangularMap(10,1), new Vector2d(0,0));
        assertTrue(animal.isAt(new Vector2d(0,0)));

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST,animal.getDirection());

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(3,0)));

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.NORTH,animal.getDirection());

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(3,0)));
        assertEquals(MapDirection.NORTH,animal.getDirection());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(0,0)));
        assertEquals(MapDirection.WEST,animal.getDirection());

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH,animal.getDirection());

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(0,0)));

        animal.move(MoveDirection.LEFT);
        assertEquals(MapDirection.WEST,animal.getDirection());

        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(9,0)));

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(9,0)));
        assertEquals(MapDirection.SOUTH,animal.getDirection());

        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(9,0)));

        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();

        assertTrue(engine.getAnimal(0).isAt(new Vector2d(2,0)));
        assertEquals(MapDirection.SOUTH, engine.getAnimal(0).getDirection());
        assertTrue(engine.getAnimal(1).isAt(new Vector2d(3,4)));
        assertEquals(MapDirection.NORTH, engine.getAnimal(1).getDirection());
    }

}