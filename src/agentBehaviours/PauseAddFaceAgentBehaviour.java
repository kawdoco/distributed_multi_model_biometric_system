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
public class PauseAddFaceAgentBehaviour extends CyclicBehaviour {

    public static ContainerController getAddfaceAgentConainer() {
        return addfaceAgentConainer;
    }

    public static void setAddfaceAgentConainer(ContainerController addfaceAgentConainer) {
        PauseAddFaceAgentBehaviour.addfaceAgentConainer = addfaceAgentConainer;
    }
    public static AgentController personDataLoaderAddFaceAgentSender;
    public static ContainerController addfaceAgentConainer;
    
    @Override
    public void action() {
        ACLMessage msg = this.getAgent().receive();
        if (msg != null) {
            if (msg.getContent().equals("PauseAddFace")) {
                try {
                    System.out.println("**New Message***");
                    System.out.println("Message Sender AID = " + msg.getSender().getLocalName());
                    System.out.println("Message content = " + msg.getContent());
                    
                    AddDataFaceDetectionAgent.frmAddDataFaceDetection.getMyThread().runnable = false;            // stop thread
                    AddDataFaceDetectionAgent.frmAddDataFaceDetection.jButton2.setEnabled(false);   // activate start button
                    AddDataFaceDetectionAgent.frmAddDataFaceDetection.webSource.release();  // stop caturing fron cam
                    
                    ProfileImpl pContainer = new ProfileImpl(null, 8888, null);
                    jade.core.Runtime rt = jade.core.Runtime.instance();
                    
                    addfaceAgentConainer = rt.createAgentContainer(pContainer);
                    setAddfaceAgentConainer(addfaceAgentConainer);
                    
                    personDataLoaderAddFaceAgentSender = addfaceAgentConainer.createNewAgent("PersonDataLoaderAddFaceAgentSender", "agents.PersonDataLoaderAddFaceAgentSender", null);
                    setPersonDataLoaderAddFaceAgentSender(personDataLoaderAddFaceAgentSender);
                    personDataLoaderAddFaceAgentSender.start();
                } catch (StaleProxyException ex) {
                    Logger.getLogger(PauseAddFaceAgentBehaviour.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            block();
        }
    }

    public static AgentController getPersonDataLoaderAddFaceAgentSender() {
        return personDataLoaderAddFaceAgentSender;
    }

    public static void setPersonDataLoaderAddFaceAgentSender(AgentController personDataLoaderAddFaceAgentSender) {
        PauseAddFaceAgentBehaviour.personDataLoaderAddFaceAgentSender = personDataLoaderAddFaceAgentSender;
    }

}
