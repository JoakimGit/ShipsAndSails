package sailsandheroes.demo.Utility;

import sailsandheroes.demo.Enums.GameResult;
import sailsandheroes.demo.Enums.TurnResult;
// Mads og Michael
public class PlayerOrderResult {
    private GameResult gameResult;
    private TurnResult turnResult;

    public PlayerOrderResult(GameResult gameResult, TurnResult turnResult) {
        this.gameResult = gameResult;
        this.turnResult = turnResult;
    }

    public PlayerOrderResult(){
        gameResult = GameResult.NONE;
        turnResult = TurnResult.SUCCESSFUL;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public TurnResult getTurnResult() {
        return turnResult;
    }

    public void setTurnResult(TurnResult turnResult) {
        this.turnResult = turnResult;
    }

    @Override
    public String toString() {
        return "PlayerOrderResult{" +
                "gameResult=" + gameResult +
                ", turnResult=" + turnResult +
                '}';
    }
}

