import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        Map<String, Integer> spacerWidths = new TreeMap<>();
        Map<String, BlockCreator> blockCreators = new TreeMap<>();
        TreeMap<String, String> defaultValues = new TreeMap<>();
        TreeMap<String, String> bdefValues = new TreeMap<>();
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = null;
        BlocksDefinitionReader blocksDefinitionReader = new BlocksDefinitionReader();

        while (true) {
            try {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (line.startsWith("default")) {
                    line = line.substring("default ".length());
                    String[] strings = line.split(" ");
                    for (int i = 0; i < strings.length; i++) {
                        defaultValues.put(strings[i].substring(0, strings[i].indexOf(":")),
                                strings[i].substring(strings[i].indexOf(":") + 1));
                    }
                } else if (line.startsWith("bdef")) {
                    line = line.substring("bdef ".length());
                    String[] strings = line.split(" ");
                    for (int i = 0; i < strings.length; i++) {
                        bdefValues.put(strings[i].substring(0, strings[i].indexOf(":")),
                                strings[i].substring(strings[i].indexOf(":") + 1));
                    }
                    blockCreators.putAll(blocksDefinitionReader.createBlock(bdefValues, defaultValues));
                }

                if (line.startsWith("sdef")) {
                    line = line.substring("bdef ".length());
                    String[] strings = line.split(" ");
                    spacerWidths.put(strings[0].split(":")[1], Integer.parseInt(strings[1].split(":")[1]));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return new BlocksFromSymbolsFactory(spacerWidths, blockCreators);
    }

    /**
     * createBlock.
     *
     * @param defaultValues sfd
     * @param bdefValues sfsdf
     * @return the blocks from symbols factory
     */
    private TreeMap<String, BlockCreator> createBlock(TreeMap<String, String> bdefValues,
                                                      TreeMap<String, String> defaultValues) {

        int width = 0;
        int height = 0;
        int hitPoints = 0;
        List<String> colorListStrings = new ArrayList<>();
        List<String> imageListStrings = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();
        List<Image> imageList = new ArrayList<>();
        Color stroke = null;
        ColorsParser colorsParser = new ColorsParser();
        ImageParser imageParser = new ImageParser();

        //color
        colorListStrings.add(bdefValues.get("fill"));
        //image
        imageListStrings.add(bdefValues.get("fill"));

        if (bdefValues.get("fill") == null) {
            colorListStrings.add(defaultValues.get("fill"));
            imageListStrings.add(defaultValues.get("fill"));
        }
        //height
        if (bdefValues.get("height") != null) {
            height = Integer.parseInt(bdefValues.get("height"));
        } else {
            height = Integer.parseInt(defaultValues.get("height"));
        }
        //width
        if (bdefValues.get("width") != null) {
            width = Integer.parseInt(bdefValues.get("width"));
        } else {
            width = Integer.parseInt(defaultValues.get("width"));
        }
        //hit points
        if (bdefValues.get("hit_points") != null) {
            hitPoints = Integer.parseInt(bdefValues.get("hit_points"));
        } else {
            hitPoints = Integer.parseInt(defaultValues.get("hit_points"));
        }

        //stroke
        if (bdefValues.get("stroke") != null) {
            stroke = colorsParser.colorFromString(bdefValues.get("stroke"));
        } else if (defaultValues.get("stroke") != null) {
            stroke = colorsParser.colorFromString(defaultValues.get("stroke"));
        }

        for (int i = 0; i <= hitPoints; i++) {
            if (bdefValues.get("fill-" + i) != null) {
                colorListStrings.add(bdefValues.get("fill-" + i));
                imageListStrings.add(bdefValues.get("fill-" + i));
            } else if (defaultValues.get("fill-" + i) != null) {
                colorListStrings.add(defaultValues.get("fill-" + i));
                imageListStrings.add(defaultValues.get("fill-" + i));
            }
        }

        for (String string : colorListStrings) {
            if (string != null) {
                colorList.add(colorsParser.colorFromString(string));
            }
        }
        for (String image : imageListStrings) {
            if (image != null) {
                imageList.add(imageParser.imageFromString(image));
            }
        }


        BlockCreator blockCreator = new BlockCreatorImpl(width, height, hitPoints, stroke, colorList, imageList);
        TreeMap<String, BlockCreator> stringBlockCreatorTreeMap = new TreeMap<>();
        stringBlockCreatorTreeMap.put(bdefValues.get("symbol"), blockCreator);
        return stringBlockCreatorTreeMap;
    }


}