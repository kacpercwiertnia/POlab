package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    void test(){
        String[] args = {"b", "f", "r", "b", "b", "r", "f", "b", "r", "f", "b", "r",
                         "f", "b", "r", "f", "b", "r", "r", "l", "r", "f", "f", "r",
                         "f", "f", "r", "f", "f", "r", "f", "f", "r", "f", "f", "r"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 10);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(9,9), new Vector2d(4,4) };
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        System.out.println(map.toString());
        engine.run();
        System.out.println(map.toString());

        assertTrue(engine.getAnimal(0).isAt(new Vector2d(3,4)));
        assertTrue(engine.getAnimal(1).isAt(new Vector2d(5,4)));
        assertTrue(engine.getAnimal(2).isAt(new Vector2d(4,4)));
        assertEquals(MapDirection.EAST, engine.getAnimal(0).getDirection());
        assertEquals(MapDirection.WEST, engine.getAnimal(1).getDirection());
        assertEquals(MapDirection.NORTH, engine.getAnimal(2).getDirection());
    }
}