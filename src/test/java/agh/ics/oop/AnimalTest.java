package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void test() {
        Animal animal = new Animal();
        assertTrue(animal.isAt(new Vector2d(2,2)));

        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.EAST,animal.getDirection());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.RIGHT);
        assertEquals(MapDirection.NORTH,animal.getDirection());

        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2,3)));

        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2,4)));
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2,4)));

        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(3,4)));
        animal.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.EAST,animal.getDirection());
        assertTrue(animal.isAt(new Vector2d(4,4)));

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.NORTH,animal.getDirection());
        assertTrue(animal.isAt(new Vector2d(4,4)));

        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(4,3)));
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(4,1)));
        animal.move(MoveDirection.BACKWARD);
        assertTrue(animal.isAt(new Vector2d(4,0)));
        animal.move(MoveDirection.BACKWARD);
        animal.move(MoveDirection.BACKWARD);
        assertEquals(MapDirection.NORTH,animal.getDirection());
        assertTrue(animal.isAt(new Vector2d(4,0)));

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(3,0)));
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(2,0)));
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(1,0)));
        animal.move(MoveDirection.FORWARD);
        assertTrue(animal.isAt(new Vector2d(0,0)));
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        assertEquals(MapDirection.WEST,animal.getDirection());
        assertTrue(animal.isAt(new Vector2d(0,0)));

        String[] args = {"f", "forward", "a", "p", "r", "backward", "b", "r", "forwad", "forward" };
        MoveDirection[] directions = new OptionsParser().parse(args);

        assertEquals(MoveDirection.FORWARD, directions[0]);
        assertEquals(MoveDirection.FORWARD, directions[1]);
        assertEquals(MoveDirection.RIGHT, directions[2]);
        assertEquals(MoveDirection.BACKWARD, directions[3]);
        assertEquals(MoveDirection.BACKWARD, directions[4]);
        assertEquals(MoveDirection.RIGHT, directions[5]);
        assertEquals(MoveDirection.FORWARD, directions[6]);

        animal.move(directions[0]);
        assertTrue(animal.isAt(new Vector2d(0,0)));

        animal.move(directions[1]);
        assertTrue(animal.isAt(new Vector2d(0,0)));

        animal.move(directions[2]);
        assertEquals(MapDirection.NORTH,animal.getDirection());

        animal.move(directions[3]);
        assertTrue(animal.isAt(new Vector2d(0,0)));

        animal.move(directions[4]);
        assertTrue(animal.isAt(new Vector2d(0,0)));

        animal.move(directions[5]);
        assertEquals(MapDirection.EAST,animal.getDirection());

        animal.move(directions[6]);
        assertTrue(animal.isAt(new Vector2d(1,0)));

    }

}