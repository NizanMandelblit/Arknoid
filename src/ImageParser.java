import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

/**
 * The type Image parser.
 */
public class ImageParser {

    /**
     * Image from string image.
     *
     * @param s the s
     * @return the image
     */
    public Image imageFromString(String s) {
        if (s.startsWith("color")) {
            return null;
        }
        String temp = s.substring("image(".length(), s.length() - 1);
        Image image = null;
        InputStream inputStream = null;
        try {
            //using rescources
            inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(temp);
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            System.out.println("Error");
        }
        try {
            inputStream.close();

        } catch (IOException e) {
            System.out.println("Error");

        }
        return image;

    }
}
