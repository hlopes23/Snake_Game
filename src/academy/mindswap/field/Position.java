package academy.mindswap.field;

import academy.mindswap.gameobjects.snake.Snake;

public class Position {

    private int col;
    private int row;


    public Position (int col, int row){
        this.col = col;
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    @Override
    public boolean equals(Object object){
        if (object instanceof Position obj) {
            return this.getRow() == obj.getRow() && this.getCol() == obj.getCol();
        }
        return false;
    }
}
