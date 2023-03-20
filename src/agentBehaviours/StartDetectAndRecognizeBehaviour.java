/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentBehaviours;

import facerecognization_fromfile.AddData_FaceDetection;
import facerecognization_fromfile.DetectAndRecognize;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author mmahmoud
 */
public class StartDetectAndRecognizeBehaviour extends CyclicBehaviour {

    DetectAndRecognize detectAndRecognize;

    @Override
    public void action() {
        ACLMessage msg = this.getAgent().receive();
        if (msg != null) {
            if (msg.getContent().equals("StartDetectAndRecognize")) {
                System.out.println("**New Message***");
                System.out.println("Message Sender AID = " + msg.getSender().getLocalName());
                System.out.println("Message content = " + msg.getContent());

                detectAndRecognize.webSource = new VideoCapture(0); // video capture from default cam
                DetectAndRecognize.DaemonThread myThread = detectAndRecognize.new DaemonThread();
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                t.start();//start thrad  
                detectAndRecognize.setMyThread(myThread);
                detectAndRecognize.jButton1.setEnabled(false);  // deactivate start button
            }
        } else {
            block();
        }
    }

    public StartDetectAndRecognizeBehaviour(DetectAndRecognize detectAndRecognize) {
        this.detectAndRecognize = detectAndRecognize;
    }
}
