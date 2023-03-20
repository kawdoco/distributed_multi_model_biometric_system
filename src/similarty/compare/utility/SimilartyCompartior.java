/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package similarty.compare.utility;

 
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author mmahmoud
 */
public class SimilartyCompartior {

   
    public void getSimilartyResult(File file_1, File file_2) throws IOException {
        long start = System.currentTimeMillis();
        int q = 0;
        File file1 = new File("C:\\Imgs\\filename.txt");

        /* if file doesnt exists, then create it
         if (!file.exists()) {
         file.createNewFile();
         }*/
        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        //File file_1 = new File("C:\\Imgs\\3.jpg");
        BufferedImage image = ImageIO.read(file_1);
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        int[][] clr = new int[width][height];
        // File file_2 = new File("C:\\Imgs\\4.jpg");
        BufferedImage images = ImageIO.read(file_2);
        int widthe = images.getWidth(null);
        int heighte = images.getHeight(null);
        int[][] clre = new int[widthe][heighte];
        int smw = 0;
        int smh = 0;
        int p = 0;
        //CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
        if (width > widthe) {
            smw = widthe;
        } else {
            smw = width;
        }
        if (height > heighte) {
            smh = heighte;
        } else {
            smh = height;
        }
        //CHECKING NUMBER OF PIXELS SIMILARITY
        for (int a = 0; a < smw; a++) {
            for (int b = 0; b < smh; b++) {
                clre[a][b] = images.getRGB(a, b);
                clr[a][b] = image.getRGB(a, b);
                if (clr[a][b] == clre[a][b]) {
                    p = p + 1;
                    bw.write("\t");
                    bw.write(Integer.toString(a));
                    bw.write("\t");
                    bw.write(Integer.toString(b));
                    bw.write("\n");
                } else {
                    q = q + 1;
                }
            }
        }

        float w, h = 0;
        if (width > widthe) {
            w = width;
        } else {
            w = widthe;
        }
        if (height > heighte) {
            h = height;
        } else {
            h = heighte;
        }
        float s = (smw * smh);
        //CALUCLATING PERCENTAGE
        float x = (100 * p) / s;

        System.out.println("THE PERCENTAGE SIMILARITY BETWEEN " + file_1.getName() + " AND " + file_2.getName() + " IS APPROXIMATELY =" + x + "%");
        long stop = System.currentTimeMillis();
        System.out.println("TIME TAKEN IS =" + (stop - start));
        System.out.println("NO OF PIXEL GETS VARIED:=" + q);
        System.out.println("NO OF PIXEL GETS MATCHED:=" + p);
    }

    public static float getMaxSimilartyResult(File file_1, File file_2) throws IOException {
        if (!file_1.getName().contains(".txt")) {
            int q = 0;

            BufferedImage image = ImageIO.read(file_1);
            int width = image.getWidth();
            int height = image.getHeight();
            int[][] clr = new int[width][height];
            // File file_2 = new File("C:\\Imgs\\4.jpg");
            BufferedImage images = ImageIO.read(file_2);
            int widthe = images.getWidth();
            int heighte = images.getHeight();
            int[][] clre = new int[widthe][heighte];
            int smw = 0;
            int smh = 0;
            int p = 0;
            //CALUCLATING THE SMALLEST VALUE AMONG WIDTH AND HEIGHT
            if (width > widthe) {
                smw = widthe;
            } else {
                smw = width;
            }
            if (height > heighte) {
                smh = heighte;
            } else {
                smh = height;
            }
            //CHECKING NUMBER OF PIXELS SIMILARITY
            for (int a = 0; a < smw; a++) {
                for (int b = 0; b < smh; b++) {
                    clre[a][b] = images.getRGB(a, b);
                    clr[a][b] = image.getRGB(a, b);
                    if (clr[a][b] == clre[a][b]) {
                        p = p + 1;

                    } else {
                        q = q + 1;
                    }
                }
            }

            float w, h = 0;
            if (width > widthe) {
                w = width;
            } else {
                w = widthe;
            }
            if (height > heighte) {
                h = height;
            } else {
                h = heighte;
            }
            float s = (smw * smh);
            //CALUCLATING PERCENTAGE
            float x = (100 * p) / s;

            System.out.println("THE PERCENTAGE SIMILARITY BETWEEN " + file_1.getName() + " AND " + file_2.getName() + " IS APPROXIMATELY =" + x + "%");
            return x;
        }
        return -1;
    }

    
   
}
 
