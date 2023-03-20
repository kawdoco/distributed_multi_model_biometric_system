/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentBehaviours;

import agents.AddDataFaceDetectionAgent;
import jade.core.ProfileImpl;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mmahmoud
 */
public class PersonDataLoaderAddFaceAgentBehaviour extends CyclicBehaviour {

    public static AgentController imageCroperAndSaverAddFaceAgentSender;
    public static ContainerController addfaceAgentConainer;

    public static AgentController getImageCroperAndSaverAddFaceAgentSender() {
        return imageCroperAndSaverAddFaceAgentSender;
    }

    public static void setImageCroperAndSaverAddFaceAgentSender(AgentController imageCroperAndSaverAddFaceAgentSender) {
        PersonDataLoaderAddFaceAgentBehaviour.imageCroperAndSaverAddFaceAgentSender = imageCroperAndSaverAddFaceAgentSender;
    }

    public static ContainerController getAddfaceAgentConainer() {
        return addfaceAgentConainer;
    }

    public static void setAddfaceAgentConainer(ContainerController addfaceAgentConainer) {
        PersonDataLoaderAddFaceAgentBehaviour.addfaceAgentConainer = addfaceAgentConainer;
    }
    
    @Override
    public void action() {
        ACLMessage msg = this.getAgent().receive();
        if (msg != null) {
            if (msg.getContent().equals("LoadPersonData")) {
                try {
                    System.out.println("**New Message***");
                    System.out.println("Message Sender AID = " + msg.getSender().getLocalName());
                    System.out.println("Message content = " + msg.getContent());
                    
                    person.data.PersonData person = new person.data.PersonData();
                    person.setAddress(AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField4.getText());
                    person.setAge(AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField2.getText());
                    person.setName(AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField1.getText());
                    person.setPhone(AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField3.getText());
                    
                    System.out.println("Person Data " + AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField4.getText() + "-" + AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField2.getText() + "-" + AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField1.getText() + "-" + AddDataFaceDetectionAgent.frmAddDataFaceDetection.jTextField3.getText());
                    AddDataFaceDetectionAgent.frmAddDataFaceDetection.getMyThread().person = person;
                  
                    ProfileImpl pContainer = new ProfileImpl(null, 8888, null);
                    jade.core.Runtime rt = jade.core.Runtime.instance();
                    
                    addfaceAgentConainer = rt.createAgentContainer(pContainer);
                    setAddfaceAgentConainer(addfaceAgentConainer);
                    
                    imageCroperAndSaverAddFaceAgentSender = addfaceAgentConainer.createNewAgent("ImageCroperAndSaverAddFaceAgentSender", "agents.ImageCroperAndSaverAddFaceAgentSender", null);
                    setImageCroperAndSaverAddFaceAgentSender(imageCroperAndSaverAddFaceAgentSender);
                    imageCroperAndSaverAddFaceAgentSender.start();
                    
                } catch (StaleProxyException ex) {
                    Logger.getLogger(PersonDataLoaderAddFaceAgentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            block();
        }
    }

}
