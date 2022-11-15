package agh.ics.oop;

import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver{

    private Vector2dXComparator xComperator = new Vector2dXComparator();
    private Vector2dYComparator yComperator = new Vector2dYComparator();
    private TreeSet<Vector2d> xSet = new TreeSet<Vector2d>(xComperator);
    private TreeSet<Vector2d> ySet = new TreeSet<Vector2d>(yComperator);
    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        this.xSet.remove(oldPosition);
        this.xSet.add(newPosition);
        this.ySet.remove(oldPosition);
        this.ySet.add(newPosition);
    }

    public void add(Vector2d position){
        this.xSet.add(position);
        this.ySet.add(position);
    }

    public void remove(Vector2d position){
        this.xSet.remove(position);
        this.ySet.remove(position);
    }

    public Vector2d getLowerLeft(){
        return new Vector2d(this.xSet.first().x, this.ySet.first().y);
    }

    public Vector2d getUpperRight(){
        return new Vector2d(this.xSet.last().x, this.ySet.last().y);
    }
}
