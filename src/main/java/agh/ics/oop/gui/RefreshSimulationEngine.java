package agh.ics.oop.gui;

import agh.ics.oop.*;
import java.util.ArrayList;
import java.util.List;

public class RefreshSimulationEngine implements IEngine, Runnable{

    private final IWorldMap map;
    private List<MoveDirection> directions;
    private final List<Animal> animals;
    private final int moveDelay;
    private final List<IMapRefreshObserver> observers;

    public RefreshSimulationEngine( IWorldMap map, Vector2d[] positions, int moveDelay){
        this.map = map;
        this.animals = new ArrayList<>();
        this.moveDelay = moveDelay;
        this.observers = new ArrayList<IMapRefreshObserver>();
        for (Vector2d position: positions){
            Animal animal = new Animal(this.map, position);
            animals.add(animal);
            this.map.place(animal);
        }
    }

    public RefreshSimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions, int moveDelay){
        this(map, positions,moveDelay);
        setMoves(directions);
    }

    public Animal getAnimal(int i){
        return animals.get(i);
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < directions.size(); i++) {
                animals.get(i % animals.size()).move(directions.get(i));
                mapRefresh();
                Thread.sleep(this.moveDelay);
            }
        }catch (InterruptedException e){
            System.out.println("Interruption while waiting for animal move!");
        }

    }

    void mapRefresh(){
        for (IMapRefreshObserver observer : this.observers) {
            observer.refresh();
        }
    }

    public void setMoves(MoveDirection[] directions) {
        this.directions = List.of(directions);
    }

    public void addObserver(IMapRefreshObserver observer) {
        this.observers.add(observer);
    }
    public void removeObserver(IMapRefreshObserver observer) {
        this.observers.remove(observer);
    }
}
