/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentBehaviours;

import agents.AddDataFaceDetectionAgent;
import facerecognization_fromfile.AddData_FaceDetection;
import facerecognization_fromfile.AddData_FaceDetection.DaemonThread;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.opencv.highgui.VideoCapture;

/**
 *
 * @author mmahmoud
 */
public class StartAddFaceAgentBehaviour extends CyclicBehaviour {
    
    @Override
    public void action() {
        ACLMessage msg = this.getAgent().receive();
        if (msg != null) {
            if (msg.getContent().equals("StartAddFace")) {
                System.out.println("**New Message***");
                System.out.println("Message Sender AID = " + msg.getSender().getLocalName());
                System.out.println("Message content = " + msg.getContent());
                
                AddData_FaceDetection.DaemonThread myThread = AddDataFaceDetectionAgent.frmAddDataFaceDetection.new DaemonThread();
                AddDataFaceDetectionAgent.frmAddDataFaceDetection.webSource = new VideoCapture(0); // video capture from default cam
                Thread t = new Thread(myThread);
                t.setDaemon(true);
                myThread.runnable = true;
                AddDataFaceDetectionAgent.frmAddDataFaceDetection.setMyThread(myThread);
                t.start();                 //start thrad
                AddDataFaceDetectionAgent.frmAddDataFaceDetection.jButton1.setEnabled(false);  // deactivate start button
                AddDataFaceDetectionAgent.frmAddDataFaceDetection.jButton2.setEnabled(true);  //  activate stop button
            }
        } else {
            block();
        }
    } 
}
