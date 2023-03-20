/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import agentBehaviours.StartDetectAndRecognizeBehaviour;
import facerecognization_fromfile.DetectAndRecognize;
import jade.core.Agent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Core;

/**
 *
 * @author Mohammed Abdalla
 */
public class DetectAndRecognizeAgent extends Agent {

    static int val;
    public static DetectAndRecognize detectAndRecognize;

    public static DetectAndRecognize getDetectAndRecognize() {
        return detectAndRecognize;
    }

    public static void setDetectAndRecognize(DetectAndRecognize detectAndRecognize) {
        DetectAndRecognizeAgent.detectAndRecognize = detectAndRecognize;
    }

    @Override
    protected void setup() {
            System.out.println("Local Name " + getAID().getLocalName());
            System.out.println("Global Name " + getAID().getName());
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            try {
                detectAndRecognize = new DetectAndRecognize();
            } catch (IOException ex) {
                Logger.getLogger(DetectAndRecognizeAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Add DetectAndRecognize Form is Loaded");
            detectAndRecognize.setVisible(true);
            addBehaviour(new StartDetectAndRecognizeBehaviour(detectAndRecognize));
            detectAndRecognize.setAllData(DetectAndRecognizeTrainDataAgent.getAllData());
    }
  }
