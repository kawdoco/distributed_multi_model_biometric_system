/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

/**
 *
 * @author mmahmoud
 */
import facerecognization_fromfile.AddData_FaceDetection;
import facerecognization_fromfile.AddData_FaceDetection.DaemonThread;
import facerecognization_fromfile.DetectAndRecognize;
import static facerecognization_fromfile.DetectAndRecognize.allData; 
import jade.core.Agent;
import jade.core.behaviours.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.highgui.VideoCapture;
import person.data.PersonData;

public class ReteriveFieldsDetectAndRecognizeAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(
                new CyclicBehaviour(this) {
                    @Override
                    public void action() {
                        if (DetectAndRecognizeAgent.getDetectAndRecognize() != null) {
                           // System.out.println("Local Name " + getAID().getLocalName());
                            //System.out.println("Global Name " + getAID().getName());
                            if (DetectAndRecognizeAgent.getDetectAndRecognize().getFileNameWithMaxValueOnly() != null) {
                                PersonData data = allData.get(DetectAndRecognizeAgent.getDetectAndRecognize().getFileNameWithMaxValueOnly());
                                DetectAndRecognizeAgent.getDetectAndRecognize().jTextField1.setText(data.getName());
                                DetectAndRecognizeAgent.getDetectAndRecognize().jTextField2.setText(data.getAge());
                                DetectAndRecognizeAgent.getDetectAndRecognize().jTextField3.setText(data.getPhone());
                                DetectAndRecognizeAgent.getDetectAndRecognize().jTextField4.setText(data.getAddress());
                               // System.out.println(data.getName());
//                                DetectAndRecognizeAgent.getDetectAndRecognize().getMyThread().runnable = false;
//                                DetectAndRecognizeAgent.getDetectAndRecognize().webSource.release();
//                                try {
//                                    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//
//                                    Thread.sleep(5000);
//                                } catch (InterruptedException ex) {
//                                    Logger.getLogger(ReteriveFieldsDetectAndRecognizeAgent.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                                //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$//
//                                DetectAndRecognizeAgent.getDetectAndRecognize().webSource = new VideoCapture(0); // video capture from default cam
//                                DetectAndRecognize.DaemonThread myThread = DetectAndRecognizeAgent.getDetectAndRecognize().new DaemonThread();
//
//                                Thread t = new Thread(myThread);
//                                t.setDaemon(true);
//                                myThread.runnable = true;
//                                DetectAndRecognizeAgent.getDetectAndRecognize().setMyThread(myThread);
//                                t.start();
                            }
                             
 
                        }
                    }
                });
    }
}
