import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;


/**
 * The type High scores table.
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> scoreInfos;


    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.scoreInfos = new ArrayList<ScoreInfo>(this.size);
    }

    /**
     * Instantiates a new High scores table.
     *
     * @param size  the size
     * @param table the table
     */
    public HighScoresTable(int size, List<ScoreInfo> table) {
        this.size = size;
        this.scoreInfos = table;
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     * @throws IOException the io exception
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) throws IOException {
        List<ScoreInfo> newTable = new ArrayList<>();
        BufferedReader is = null;
        try {
            // wrapper that reads ahead
            is = new BufferedReader(
                    // wrapper that reads characters
                    new InputStreamReader(
                            new FileInputStream(filename)));

            String line = is.readLine();
            // null no more data in the stream
            while (line != null) {
                if (line == null) {
                    break;
                }
                String name = line;
                line = is.readLine();
                int score = 0;
                try {
                    score = Integer.parseInt(line);
                } catch (Exception e) {
                    break;
                }
                newTable.add(new ScoreInfo(name, score));
                line = is.readLine();
            }
        } finally {
            // Exception might have happened at constructor
            if (is != null) {
                try {
                    // closes FileInputStream too
                    is.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
        return new HighScoresTable(GameFlow.MAX_TABLE_SIZE, newTable);
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {
        if (this.scoreInfos.size() < this.size) {
            scoreInfos.add(score);
        } else {
            if (this.scoreInfos.get(this.size - 1).getScore() < score.getScore()) {
                this.scoreInfos.remove(this.size - 1);
                this.scoreInfos.add(score);
            }
        }

    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        Collections.sort(this.scoreInfos, new Comparator<ScoreInfo>() {
            @Override
            public int compare(ScoreInfo o1, ScoreInfo o2) {
                return o2.getScore() - o1.getScore();
            }
        });
        return this.scoreInfos;

    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        int counter = 1;
        for (ScoreInfo scoreLoop : scoreInfos) {
            if (scoreLoop.getScore() < score) {
                break;
            }
            counter++;
        }
        return counter;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.scoreInfos.clear();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        BufferedReader is = null;
        try {
            // wrapper that reads ahead
            is = new BufferedReader(
                    // wrapper that reads characters
                    new InputStreamReader(
                            new FileInputStream(filename)));
            String line;
            // null no more data in the stream
            while ((line = is.readLine()) != null) {
                System.out.print(line);
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while reading !");
        } finally {
            // Exception might have happened at constructor
            if (is != null) {
                try {
                    // closes FileInputStream too
                    is.close();
                } catch (IOException e) {
                    System.out.println(" Failed closing the file !");
                }
            }
        }
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {

        PrintWriter os = null;
        try {
            // wrapper with many ways of writing strings
            os = new PrintWriter(
                    // wrapper that can write strings
                    new OutputStreamWriter(
                            new FileOutputStream(filename)));
            for (ScoreInfo scoreinfo : this.scoreInfos) {
                os.println(scoreinfo.getName());
                os.println(Integer.toString(scoreinfo.getScore()));
            }
        } catch (IOException e) {
            System.out.println(" Something went wrong while writing !");
        } finally {
            System.out.println(this.getHighScores().size());
            if (os != null) { // Exception might have happened at constructor
                os.close(); // closes fileOutputStream too
            }
        }


    }
}
