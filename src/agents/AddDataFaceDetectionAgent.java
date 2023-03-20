/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import agentBehaviours.ImageCroperAndSaverAddFaceAgentBehaviour;
import agentBehaviours.PauseAddFaceAgentBehaviour;
import agentBehaviours.PersonDataLoaderAddFaceAgentBehaviour;
import agentBehaviours.StartAddFaceAgentBehaviour;
import facerecognization_fromfile.AddData_FaceDetection;
import jade.core.Agent;
import org.opencv.core.Core;

/**
 *
 * @author mmahmoud
 */
public class AddDataFaceDetectionAgent extends Agent {

    static int val;
    public static AddData_FaceDetection frmAddDataFaceDetection;

    @Override
    protected void setup() { //Runs on creation of the agent
        System.out.println("Local Name " + getAID().getLocalName());
        System.out.println("Global Name " + getAID().getName());
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        frmAddDataFaceDetection = new AddData_FaceDetection();
        System.out.println("Add Data_FaceDetection Form is Loaded");
        frmAddDataFaceDetection.setVisible(true);
        addBehaviour(new StartAddFaceAgentBehaviour());
        addBehaviour(new PauseAddFaceAgentBehaviour());   
        addBehaviour(new PersonDataLoaderAddFaceAgentBehaviour());         
        addBehaviour(new ImageCroperAndSaverAddFaceAgentBehaviour());     
    }
}
