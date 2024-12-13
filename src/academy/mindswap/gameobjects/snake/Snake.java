package academy.mindswap.gameobjects.snake;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;

import java.util.ArrayList;
import java.util.LinkedList;

public class Snake {

    private final static int SNAKE_INITIAL_SIZE = 3;
    private Direction direction;
    private boolean alive = true;
    private LinkedList<Position> body = new LinkedList<>();



    public Snake(){
        this.direction = Direction.RIGHT;
        for (int i = 0; i < SNAKE_INITIAL_SIZE; i++){
            body.add(new Position(12-i, 10));
        }
    }

    public void increaseSize() {
        body.add(new Position(getTail().getCol(), getTail().getRow()));
        }


    public void move(Direction direction) {

        switch (direction) {
            case UP:
                if (this.direction == Direction.DOWN) {
                    move();
                    break;
                }

                body.removeLast();
                body.addFirst(new Position(body.getFirst().getCol(), body.getFirst().getRow() - 1));
                this.direction = direction;
                break;

            case DOWN:
                if (this.direction == Direction.UP) {
                    move();
                    break;
                }

                body.removeLast();
                body.addFirst(new Position(body.getFirst().getCol(), body.getFirst().getRow() + 1));
                this.direction = direction;
                break;

            case LEFT:
                if (this.direction == Direction.RIGHT) {
                    move();
                    break;
                }

                body.removeLast();
                body.addFirst(new Position(body.getFirst().getCol() - 1, body.getFirst().getRow()));
                this.direction = direction;
                break;

            case RIGHT:
                if (this.direction == Direction.LEFT) {
                    move();
                    break;
                }
                    body.removeLast();
                    body.addFirst(new Position(body.getFirst().getCol() + 1, body.getFirst().getRow()));
                    this.direction = direction;
                    break;
                }
        }


    public void move(){
        move(direction);
    }

    public void die() {
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public Position getHead() {
        return body.getFirst();
    }

    public Position getTail() {
        return body.getLast();
    }

    public LinkedList<Position> getFullSnake(){
        return body;
    }

    public int getSnakeSize() {
        return body.size();
    }
}

