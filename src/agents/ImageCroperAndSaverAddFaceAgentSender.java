/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;
/**
 *
 * @author mmahmoud
 */
public class ImageCroperAndSaverAddFaceAgentSender extends Agent {

    @Override
    protected void setup() {
        System.out.println("Local Name " + getAID().getLocalName());
        System.out.println("Global Name " + getAID().getName());
        System.out.println("Pause PersonDataLoaderAddFaceAgentSender");        
        ACLMessage acl1 = new ACLMessage(ACLMessage.INFORM);
        acl1.addReceiver(new AID("AddDataFaceDetectionAgent", AID.ISLOCALNAME));
        acl1.setContent("CropAndSaveFaceImage");
        this.send(acl1);
    }
    
    @Override
    protected void takeDown() {
        System.out.println("PauseAddFaceAgentSender Agent Killed");
    }
}
