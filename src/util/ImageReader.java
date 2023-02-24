package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageReader {

    // [ EXPLAIN ]
    /*
     * this path parameter is path's image, which need to read.
     * Ex: BufferedImage Object = ImageReader.Read("../images/Object.png");
     *  --> path : ./src/resources/images/Object.png
     */
    public static BufferedImage Read(String path) throws IOException {
        return ImageIO.read(new File(System.getProperty("user.dir") + "/src/resources/" + path));
    }
}
