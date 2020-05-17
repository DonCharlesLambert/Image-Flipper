import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
	String imgFolder = getImageFolder();
        File[] imgsFile  = getAllFiles(imgFolder);
        for(File img: imgsFile){
            BufferedImage image = ImageIO.read(img);
            image = flipBufferedImage(image);
            File outputFile = new File(img.getAbsolutePath());
            ImageIO.write(image, "png", outputFile);
        }
	System.out.println("All the images at " + imgFolder + " have been flipped");
    }

    private static String getImageFolder(){
        System.out.println("Input Image Folder");
        System.out.print(">>> ");
        Scanner s = new Scanner(System.in);
        String imageFolder = s.next();
        return imageFolder;
    }

    private static File[] getAllFiles(String dir){
        File folder = new File(dir);
        return folder.listFiles();
    }

    // I do not take without credit
    // http://www.java2s.com/Tutorial/Java/0261__2D-Graphics/Fliptheimagehorizontally.htm
    private static BufferedImage flipBufferedImage(BufferedImage image){
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);
        return image;
    }
}
