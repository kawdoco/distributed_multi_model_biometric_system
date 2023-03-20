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
import facerecognization_fromfile.MainForm;
import jade.core.Agent;

public class MainFormAgent extends Agent {

    static int val;
    MainForm a;

    @Override
    protected void setup() { //Runs on creation of the agent
                System.out.println("Local Name " + getAID().getLocalName());
        System.out.println("Global Name " + getAID().getName());
        a = new MainForm();
        a.setVisible(true);
        System.out.println("Main Form is Loaded");
    }
}
