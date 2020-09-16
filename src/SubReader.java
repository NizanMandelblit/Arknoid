import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Sub reader.
 */
public class SubReader implements Task<Void> {
    private List<String> keys;
    private List<String> levelSetName;
    private List<String> path;
    private Map<String, String> keysLevelSetsName;
    private Map<String, String> levelSetsNamePath;

    /**
     * Instantiates a new Sub reader.
     */
    public SubReader() {
        this.keys = new ArrayList<>();
        this.levelSetName = new ArrayList<>();
        this.path = new ArrayList<>();
        this.keysLevelSetsName = new TreeMap<>();
        this.levelSetsNamePath = new TreeMap<>();
    }

    /**
     * Gets keys.
     *
     * @return the keys
     */
    public List<String> getKeys() {
        return keys;
    }

    /**
     * Gets level set name.
     *
     * @return the level set name
     */
    public List<String> getLevelSetName() {
        return levelSetName;
    }

    /**
     * Gets path.
     *
     * @return the path
     */
    public List<String> getPath() {
        return path;
    }

    /**
     * Read sub.
     *
     * @param file the file
     */
    public void readSub(java.io.Reader file) {
        String levelSetNamel;
        String key;
        //   BufferedReader bufferedReader = new BufferedReader(file);
        LineNumberReader lineNumberReader = new LineNumberReader(file);
        while (true) {
            try {
                if (file == null) {
                    break;
                }
                String line;
                //level names
                if (lineNumberReader.getLineNumber() % 2 == 0) {
                    line = lineNumberReader.readLine();
                    if (line == null) {
                        break;
                    }
                    key = line.substring(0, line.indexOf(":"));
                    levelSetNamel = line.substring(line.indexOf(":") + 1);
                    this.keys.add(key);
                    this.levelSetName.add(levelSetNamel);
                    this.keysLevelSetsName.put(key, levelSetNamel);
                    continue;
                }
                //path to file
                if (lineNumberReader.getLineNumber() % 2 != 0) {
                    line = lineNumberReader.readLine();
                    if (line == null) {
                        break;
                    }
                    this.path.add(line);
                    continue;
                }

                for (int i = 0; i < this.keys.size(); i++) {
                    this.levelSetsNamePath.put(this.levelSetName.get(i), this.path.get(i));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public Void run() throws IOException {
        return null;
    }
}
