package br.com.java.solution.util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static byte[] imageToByteArr(String filePath) {
        BufferedImage bImage;
        ByteArrayOutputStream bos;
        try {
            bImage = ImageIO.read(new File("/Users/gabriel/pictures/" + filePath));
            bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", bos);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}