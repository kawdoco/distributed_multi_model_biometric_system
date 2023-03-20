/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentBehaviours;

import ImageCropping.ImpageCropping;
import agents.AddDataFaceDetectionAgent; 
import agents.start.automatic.starterAgentsAutomaticlly;
import static agents.start.automatic.starterAgentsAutomaticlly.startAddFaceAgentSender;
import facerecognization_fromfile.AddData_FaceDetection;
import static facerecognization_fromfile.AddData_FaceDetection.pauseAddFaceAgentSender;
import static facerecognization_fromfile.AddData_FaceDetection.setPauseAddFaceAgentSender;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage; 
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.core.Mat;
/**
 *
 * @author mmahmoud
 */
public class ImageCroperAndSaverAddFaceAgentBehaviour extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = this.getAgent().receive();
        if (msg != null) {
            if (msg.getContent().equals("CropAndSaveFaceImage")) {
 
                    System.out.println("**New Message***");
                    System.out.println("Message Sender AID = " + msg.getSender().getLocalName());
                    System.out.println("Message content = " + msg.getContent());
              
                    person.data.PersonData person = AddDataFaceDetectionAgent.frmAddDataFaceDetection.getMyThread().person;
                      //if (AddDataFaceDetectionAgent.frmAddDataFaceDetection.getMyThread().rect_Crop != null) {
                        try {
                            Mat image_roi = new Mat(AddDataFaceDetectionAgent.frmAddDataFaceDetection.frame, AddDataFaceDetectionAgent.frmAddDataFaceDetection.getMyThread().rect_Crop);
                            System.out.println("image_roi" + image_roi);
                            System.out.println("person" + person.getName());
                            AddDataFaceDetectionAgent.frmAddDataFaceDetection.saveBufferedImageAndAssocatedData(ImpageCropping.MatToBufferedImage(image_roi), person);
                            AddDataFaceDetectionAgent.frmAddDataFaceDetection.jButton1.setEnabled(true);
                            AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField1.setText("");
                            AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField2.setText("");
                            AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField3.setText("");
                            AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField4.setText("");
                            
                            starterAgentsAutomaticlly.startAddFaceAgentSender.kill();  
                            AddData_FaceDetection.pauseAddFaceAgentSender.kill();
                            PauseAddFaceAgentBehaviour.personDataLoaderAddFaceAgentSender.kill();
                            PersonDataLoaderAddFaceAgentBehaviour.imageCroperAndSaverAddFaceAgentSender.kill();
                            
                            /*starterAgentsAutomaticlly.addfaceAgentConainer.kill();
                            AddData_FaceDetection.addfaceAgentConainer.kill();
                            PauseAddFaceAgentBehaviour.addfaceAgentConainer.kill();
                            PersonDataLoaderAddFaceAgentBehaviour.addfaceAgentConainer.kill();*/
                            
                            jade.core.Runtime rt = jade.core.Runtime.instance();                 
                            ProfileImpl pContainer = new ProfileImpl(null, 8888, null);
                            /* Part 1 : Create Add Face Info Container*/
                            ContainerController addfaceAgentConainer = rt.createAgentContainer(pContainer);
                            AgentController startAddFaceAgentSender = addfaceAgentConainer.createNewAgent("StartAddFaceAgentSender", "agents.StartAddFaceAgentSender", null);
                            starterAgentsAutomaticlly.setStartAddFaceAgentSender(startAddFaceAgentSender);
                            starterAgentsAutomaticlly.getStartAddFaceAgentSender().start(); 
                            
                        } catch (Exception ex) {
                            Logger.getLogger(ImageCroperAndSaverAddFaceAgentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
                        }
                                  //  }
            }
        } else {
            block();
        }
    }

}
