package academy.mindswap.gameobjects.obstacle;

import academy.mindswap.field.Position;

import java.util.LinkedList;

public class Obstacle{

    private Position obstaclePosition;


    public Obstacle (Position obstaclePosition){
        this.obstaclePosition = obstaclePosition;
    }


    public Position getPosition(){
        return this.obstaclePosition;
    }

}