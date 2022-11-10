package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    void test(){
        String[] args = {"b", "b", "r", "b", "b", "r", "f", "b", "r", "f", "b", "r",
                         "f", "b", "r", "f", "l", "r", "r", "f", "r", "f", "f", "r",
                         "f", "f", "r", "f", "f", "r", "f", "f", "r", "f", "f", "r"};
        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = { new Vector2d(0,0), new Vector2d(9,9), new Vector2d(4,4) };
        IWorldMap map = new GrassField(10);
        SimulationEngine engine = new SimulationEngine(directions, map, positions);

        System.out.println(map.toString());
        engine.run();
        System.out.println(map.toString());

        assertTrue(engine.getAnimal(0).isAt(new Vector2d(5,2)));
        assertTrue(engine.getAnimal(1).isAt(new Vector2d(5,4)));
        assertTrue(engine.getAnimal(2).isAt(new Vector2d(4,4)));
        assertEquals(MapDirection.EAST, engine.getAnimal(0).getDirection());
        assertEquals(MapDirection.WEST, engine.getAnimal(1).getDirection());
        assertEquals(MapDirection.NORTH, engine.getAnimal(2).getDirection());
    }
}