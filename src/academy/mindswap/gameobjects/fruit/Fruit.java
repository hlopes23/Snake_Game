package academy.mindswap.gameobjects.fruit;

import academy.mindswap.field.Position;

public class Fruit {

    private Position fruitPosition;


    public Fruit (Position fruitPosition){
        this.fruitPosition = fruitPosition;
    }

    public Position getPosition(){
        return this.fruitPosition;
    }
}