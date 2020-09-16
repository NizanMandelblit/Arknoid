import biuoop.GUI;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.File;
import java.util.List;

/**
 * The type Ass 6 game.
 */
public class Ass7Game {
    /**
     * The constant GUI.
     */
    public static final GUI GUI = new GUI("Arknoid", 800, 600);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws IOException the io exception
     */
    public static void main(String[] args) throws IOException {

        //highscoreTable
        HighScoresTable loadTable;
        File name = new File("highscores");
        try {
            //rescorces
            loadTable = HighScoresTable.loadFromFile(name);

        } catch (Exception e) {
            loadTable = new HighScoresTable(GameFlow.MAX_TABLE_SIZE);
            loadTable.save(name);
        }

        GameFlow g = new GameFlow(new AnimationRunner(GUI, 60), GUI.getKeyboardSensor(), loadTable);


        InputStream file = null;
        //read the settings
        if (args.length == 0) {
            try {
                file = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            file = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);
        }

        Reader setLevelDef = new InputStreamReader(file);
        SubReader subReader = new SubReader();
        subReader.readSub(setLevelDef);
        Menu<Task> subMenu = new MenuAnimation<Task>();
        for (int i = 0; i < subReader.getPath().size(); i++) {
            //rescorces
            file = ClassLoader.getSystemClassLoader().getResourceAsStream(subReader.getPath().get(i));
            Reader readerLevel = new InputStreamReader(file);
            LevelSpecificationReader levelSpecificationReader = new LevelSpecificationReader();
            List<LevelInformation> levelInformationList = levelSpecificationReader.fromReader(readerLevel);
            subMenu.addSelection(subReader.getKeys().get(i), subReader.getLevelSetName().get(i), new PlayGameTask(g,
                    levelInformationList));
        }


        Menu<Task> menu = new MenuAnimation<Task>();
        // the parameters to addSelection are:
        // key to wait for, line to print, what to return
        menu.addSelection("s", "Start Game", new SubMenuTask(subMenu));
        // menu.addSubMenu("s", "Start Game", subMenu);
        menu.addSelection("h", "High Scores", new HighScoreTask(new HighScoresAnimation(loadTable)));
        menu.addSelection("q", "Quit", new QuitGameTask(Ass7Game.GUI));

        AnimationRunner runner = new AnimationRunner(GUI, 60);
        while (true) {
            runner.run(menu);
            // A menu with the selections is displayed.
            // Runs until user presses "s", "h"  or "q"

            Task result = menu.getStatus();
            if (result != null) {
                // result will contain "option a", "option b"
                // or "option c"
                result.run();
            }
        }


    }

}
