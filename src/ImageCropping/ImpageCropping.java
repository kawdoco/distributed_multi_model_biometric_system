package ImageCropping;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author Mohammed Abdalla
 */
public class ImpageCropping {

    public static void main(String args[]) throws IOException {
        //  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Image im = ImageIO.read(new File("C:\\Imgs\\sss.png"));
        BufferedImage buff = (BufferedImage) im;
        cropFaceFromImage(buff, "C:\\Imgs\\cropped.7.png");
    }
 
    public static void cropFaceFromImage(BufferedImage originalImage, String croppedFaceImagePath) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat frame = bufferedImageToMat(originalImage);
        CascadeClassifier faceDetector = new CascadeClassifier(facerecognization_fromfile.AddData_FaceDetection.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(frame, faceDetections);
        System.out.print(faceDetections.toArray().length);
        Rect rect_Crop = null;
        for (Rect rect : faceDetections.toArray()) {
            Core.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
            rect_Crop = new Rect(rect.x, rect.y, 200, 200);
        }

        Mat image_roi = new Mat(frame, rect_Crop); 
         Highgui.imwrite(croppedFaceImagePath, image_roi);
       // Mat image_roi = new Mat(frame, rect_Crop);
       // Mat mat1 = new Mat(200, 200, CvType.CV_8UC1);
       // Imgproc.cvtColor(image_roi, mat1, Imgproc.COLOR_RGB2GRAY);
        //Highgui.imwrite(croppedFaceImagePath, mat1);

    }
 public static Mat cropFaceFromImage(BufferedImage originalImage) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat frame = bufferedImageToMat(originalImage);
        CascadeClassifier faceDetector = new CascadeClassifier(facerecognization_fromfile.AddData_FaceDetection.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(frame, faceDetections);
        System.out.print(faceDetections.toArray().length);
        Rect rect_Crop = null;
        for (Rect rect : faceDetections.toArray()) {
            Core.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
            rect_Crop = new Rect(rect.x, rect.y, 200, 200);
        }
        Mat image_roi = new Mat(frame, rect_Crop);
        Mat mat1 = new Mat(200, 200, CvType.CV_8UC1);
        Imgproc.cvtColor(image_roi, mat1, Imgproc.COLOR_RGB2GRAY);
        return mat1;

    }
 public static BufferedImage cropFaceFromImageAndReturnBufferedImge(BufferedImage originalImage) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat frame = bufferedImageToMat(originalImage);
        CascadeClassifier faceDetector = new CascadeClassifier(facerecognization_fromfile.AddData_FaceDetection.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(frame, faceDetections);
        System.out.print(faceDetections.toArray().length);
        Rect rect_Crop = null;
        for (Rect rect : faceDetections.toArray()) {
            Core.rectangle(frame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 255, 0));
            rect_Crop = new Rect(rect.x, rect.y, 200, 200);
        }
        Mat image_roi = new Mat(frame, rect_Crop);
        Mat mat1 = new Mat(200, 200, CvType.CV_8UC1);
        //Imgproc.cvtColor(image_roi, mat1, Imgproc.COLOR_RGB2GRAY);
        return MatToBufferedImage(mat1);

    }
 
 public static BufferedImage MatToBufferedImage(Mat matBGR){  
      long startTime = System.nanoTime();  
      int width = matBGR.width(), height = matBGR.height(), channels = matBGR.channels() ;  
      byte[] sourcePixels = new byte[width * height * channels];  
      matBGR.get(0, 0, sourcePixels);  
      // create new image and get reference to backing data  
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);  
      final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();  
      System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);  
      long endTime = System.nanoTime();  
      System.out.println(String.format("Elapsed time: %.2f ms", (float)(endTime - startTime)/1000000));  
      
      return image;  
}  
    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}
