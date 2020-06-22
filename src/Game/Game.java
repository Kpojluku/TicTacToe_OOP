package Game;

import javax.swing.*;

public class Game {
    private GameBoard board;                              // ссылка на игровое поле
    private GamePlayer[] gamePlayers = new GamePlayer[2];  // массив игроков
    private int playersTurn = 0;                          // индекс текущего игрока
    private static boolean SILLY_MODE = true;

    public GameBoard getBoard() {
        return board;
    }

    public Game() {
        this.board = new GameBoard(this);
    }

   public void initGame(){
        gamePlayers[0] = new GamePlayer(true, 'X');
        gamePlayers[1] = new GamePlayer(false, 'O');
    }


    /**
     * Метод передачи хода
     */
    void passTurn(){
        playersTurn = playersTurn == 0 ? 1 : 0;
    }

    /**
     * Получение объекта текущего игрока
     * @return GamePlayer объект игрока
     */
    GamePlayer getCurrentPlayer(){ return gamePlayers[playersTurn]; }

    /**
     * Метод показа popup-a для пользователя
     * @param messageText - текст сообщения
     */
    void showMessage(String messageText){
        JOptionPane.showMessageDialog(board, messageText);
    }

    /**
     * Геттеры и сеттеры для выбора режима игры
     */
    public static void setSillyMode(boolean sillyMode) {
        SILLY_MODE = sillyMode;
    }

    public static boolean isSillyMode() {
        return SILLY_MODE;
    }
}
