package agh.ics.oop;

import java.util.Comparator;

public class Vector2dXComparator implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d o1, Vector2d o2) {
        if( o1.x != o2.x){
            return o1.x - o2.x;
        }else{
            return o1.y - o2.y;
        }
    }
}
