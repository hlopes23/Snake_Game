package academy.mindswap;

import academy.mindswap.field.Field;
import academy.mindswap.field.Position;
import academy.mindswap.gameobjects.fruit.Fruit;
import academy.mindswap.gameobjects.obstacle.Obstacle;
import academy.mindswap.gameobjects.snake.Direction;
import academy.mindswap.gameobjects.snake.Snake;
import com.googlecode.lanterna.input.Key;

import java.util.LinkedList;


public class Game {

    private Snake snake;
    private Fruit fruit;
    private Obstacle obstacle;
    private LinkedList<Obstacle> obstacleList = new LinkedList<>();
    private int delay;;


    public Game(int cols, int rows, int delay) {
        Field.init(cols, rows);
        snake = new Snake();
        this.delay = delay;
    }

    public void start() throws InterruptedException {

        generateFruit();

        while (snake.isAlive()) {
            Thread.sleep(delay);
            Field.clearTail(snake);
            moveSnake();
            checkCollisions();
            Field.drawSnake(snake);
            Field.drawSize(snake.getSnakeSize());
        }
    }


    private void generateFruit() {

        int maxCol = 100;
        int minCol = 0;

        int maxRow = 25;
        int minRow = 0;

        int col = (int) (Math.random() * (maxCol - minCol) + minCol);
        int row = (int) (Math.random() * (maxRow - minRow) + minRow);

        Position fruitPosition = new Position(col, row);
        this.fruit = new Fruit(fruitPosition);

        if (snake.getHead().equals(snake.getFullSnake().contains(fruitPosition))) {
            generateFruit();
            return;
        }

        Field.drawFruit(fruit);
    }


    private void randomObstacle(){
        int min = 1;
        int max = 4;

        int random = (int) (Math.random() * (max - min) + min);

        if (random == 1){
            generateObstacle();
        }
    }


    private void generateObstacle(){

        int maxCol = 100;
        int minCol = 0;

        int maxRow = 25;
        int minRow = 0;

        int col = (int) (Math.random() * (maxCol - minCol) + minCol);
        int row = (int) (Math.random() * (maxRow - minRow) + minRow);

        Position obstaclePosition = new Position(col, row);
        obstacle = new Obstacle(obstaclePosition);
        obstacleList.add(obstacle);

        Field.drawObstacle(obstacle);
    }


    private void moveSnake() {

        Key k = Field.readInput();

        if (k != null) {
            switch (k.getKind()) {
                case ArrowUp:
                    snake.move(Direction.UP);
                    return;

                case ArrowDown:
                    snake.move(Direction.DOWN);
                    return;

                case ArrowLeft:
                    snake.move(Direction.LEFT);
                    return;

                case ArrowRight:
                    snake.move(Direction.RIGHT);
                    return;
            }
        }
        snake.move();
    }


   /* private void restart() throws InterruptedException {

        Key k = Field.readInput();

        if(!snake.isAlive() && k != null){

            switch (k.getKind()) {
                case Enter:

            }
        }
    }*/


    private void checkCollisions() {

        Position fieldPosition = new Position(Field.getWidth(), Field.getHeight());
        Position head = snake.getHead();


        // COLLISION WITH BORDERS:

        if(head.getRow() == -1 || head.getCol() == -1 || head.getRow() == fieldPosition.getRow() || head.getCol() == fieldPosition.getCol()){
            snake.die();
            Field.printGameOver();
        }


        // COLLISION WITH ITSELF:

        if (snake.getFullSnake().subList(1, snake.getSnakeSize()).contains(snake.getHead())){
            snake.die();
            Field.printGameOver();
        }


        //COLLISION WITH FRUIT:

        if(snake.getHead().equals(fruit.getPosition())){
            generateFruit();
            snake.increaseSize();
            increaseDificulty();
            randomObstacle();
        }


        //COLLISION WITH OBSTACLE:

    }

    private void increaseDificulty(){
        this.delay = delay-4;
    }
}