package sailsandheroes.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sailsandheroes.demo.GameModul.Game;
import sailsandheroes.demo.Repository.GameRepository;

import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public Game fetchGameById(int id) {
        return gameRepository.fetchGameById(id);
    }

    public List<Game> fetchAllGames() {
        return gameRepository.fetchAllGames();
    }

    public int createGame(Game g) {
        return gameRepository.createGame(g);
    }

    public void updateGame(Game g) {
        gameRepository.updateGame(g);
    }

    public void deleteGame(int id) {
        gameRepository.deleteGame(id);
    }
}
