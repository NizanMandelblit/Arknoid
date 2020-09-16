import java.awt.Color;
import java.util.TreeMap;

/**
 * The type Colors parser.
 */
public class ColorsParser {
    /**
     * Color from string java . awt . color.
     *
     * @param s the s
     * @return the java . awt . color
     */
// parse color definition and return the specified color.
    public java.awt.Color colorFromString(String s) {
        if (!s.startsWith("color(RGB")) {
            TreeMap<String, Color> colorTreeMap = new TreeMap<>();
            colorTreeMap.put("color(black)", Color.black);
            colorTreeMap.put("color(blue)", Color.blue);
            colorTreeMap.put("color(cyan)", Color.cyan);
            colorTreeMap.put("color(gray)", Color.gray);
            colorTreeMap.put("color(lightGray)", Color.lightGray);
            colorTreeMap.put("color(green)", Color.green);
            colorTreeMap.put("color(orange)", Color.orange);
            colorTreeMap.put("color(pink)", Color.pink);
            colorTreeMap.put("color(red)", Color.red);
            colorTreeMap.put("color(white)", Color.white);
            colorTreeMap.put("color(yellow)", Color.yellow);

            return colorTreeMap.get(s);
        } else if (s.startsWith("color(RGB")) {
            s = s.substring("color(RGB(".length());
            s = s.substring(0, s.length() - 1);
            String[] strings = s.split(",");
            Color color = new Color(Integer.parseInt(strings[0]), Integer.parseInt(strings[1])
                    , Integer.parseInt(strings[2].substring(0, strings.length - 2)));

            return color;

        }
        return null;
    }
}