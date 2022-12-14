package agh.ics.oop;

public class Grass implements IMapElement{

    private Vector2d position;

    public Grass( Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    @Override
    public String getImage() {
        return "/Users/kacpercwiertnia/JAVA/oolab/src/main/resources/grass.png";
    }

    @Override
    public String toCaption() {
        return "Trawa";
    }

    public String toString(){
        return "*";
    }
}
