package Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameActionListener implements ActionListener {
    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton qButton) {
        this.row = row;
        this.cell = cell;
        this.button = qButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board = button.getBoard();

        if(board.isTurnable(row, cell)){
            updateByPlayerData(board);

            if(board.isFull()){
                board.getGame().showMessage("Ничья!");
                board.emptyField();
            }else {
                updateByAiData(board);
            }
        }else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }

    /**
     * Ход человека
     * @param board GameBoards - ссылка на игровое поле
     */
    private void updateByPlayerData(GameBoard board){
        // обновить матрицу игры
        board.updateGameField(row, cell);

        // Обновить содержимое кнопки
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSing()));

        if(board.checkWin()){
            button.getBoard().getGame().showMessage("Вы выиграли");
            board.emptyField();
        }else
            board.getGame().passTurn();
    }

    private void updateByAiData(GameBoard board){
        // генерация координат хода компьютера

        int x, y;
        Random rnd = new Random();
        int [] BestСhoice = new int[] {0, 0, 0};
        int rating = 0;

        if(Game.isSillyMode()) {
            do {
                x = rnd.nextInt(GameBoard.dimension);
                y = rnd.nextInt(GameBoard.dimension);
            }
            while (!board.isTurnable(x, y));
        }else {
            for(int i = 0; i<GameBoard.dimension; i++){
                for(int j = 0; j<GameBoard.dimension; j++){
                    if(board.gameField[i][j] == GameBoard.nullSymbol){
                        if(i-1>=0 && board.gameField[i-1][j] == board.getGame().getCurrentPlayer().getPlayerSing() ) {
                            rating++;
                        }
                        if(i+1<3 && board.gameField[i + 1][j] == board.getGame().getCurrentPlayer().getPlayerSing()) {
                            rating++;
                        }
                        if(j-1>=0 && board.gameField[i][j-1] == board.getGame().getCurrentPlayer().getPlayerSing() ){
                            rating ++;
                        }

                        if(j+1<3 && board.gameField[i][j+1] == board.getGame().getCurrentPlayer().getPlayerSing()){
                            rating ++;
                        }
                        if(i-1>=0 && j-1>=0 && board.gameField[i-1][j-1] == board.getGame().getCurrentPlayer().getPlayerSing()){
                            rating ++;
                        }

                        if(i-1>=0 && j+1 <3 && board.gameField[i-1][j+1] == board.getGame().getCurrentPlayer().getPlayerSing()){
                            rating ++;
                        }

                        if(i+1 < 3 && j-1>=0 && board.gameField[i+1][j-1] == board.getGame().getCurrentPlayer().getPlayerSing() ){
                            rating ++;
                        }

                        if(i+1 < 3 && j+1<3 && board.gameField[i+1][j+1] == board.getGame().getCurrentPlayer().getPlayerSing()){
                            rating ++;
                        }
                        if(rating > BestСhoice[0]){
                            BestСhoice[0] = rating;
                            BestСhoice[1] = i;
                            BestСhoice[2] = j;
                        }
                    }

                    rating = 0;
                }
            }

            if(BestСhoice[0] == 0) {
                do {
                    x = rnd.nextInt(GameBoard.dimension);
                    y = rnd.nextInt(GameBoard.dimension);
                }while (!board.isTurnable(x, y));
            }else {
                y = BestСhoice[1];
                x = BestСhoice[2];
            }
        }
        // обновить матрицу игры
        board.updateGameField(x,y);

        // обновить содержимое кнопки
        int cellIndex = GameBoard.dimension * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSing()));

        // проверить победу
        if (board.checkWin()) {
            button.getBoard().getGame().showMessage("Компьютер выграл");
            board.emptyField();
        }else {
            // передать ход
            board.getGame().passTurn();
        }
    }


}
