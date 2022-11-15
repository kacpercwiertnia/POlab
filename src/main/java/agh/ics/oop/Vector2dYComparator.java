package agh.ics.oop;

import java.util.Comparator;

public class Vector2dYComparator implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d o1, Vector2d o2) {
        if( o1.y != o2.y){
            return o1.y - o2.y;
        }else{
            return o1.x - o2.x;
        }
    }
}
