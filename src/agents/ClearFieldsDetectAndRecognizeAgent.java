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
import jade.core.Agent;
import jade.core.behaviours.*;

public class ClearFieldsDetectAndRecognizeAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(
            new CyclicBehaviour(this) {
                @Override
                public void action() {
                    if (DetectAndRecognizeAgent.getDetectAndRecognize() != null) {
                        //System.out.println("Local Name " + getAID().getLocalName());
                        //System.out.println("Global Name " + getAID().getName());
                        if (DetectAndRecognizeAgent.getDetectAndRecognize().getFileNameWithMaxValueOnly() == null) {
                            DetectAndRecognizeAgent.getDetectAndRecognize().jTextField1.setText("");
                            DetectAndRecognizeAgent.getDetectAndRecognize().jTextField2.setText("");
                            DetectAndRecognizeAgent.getDetectAndRecognize().jTextField3.setText("");
                            DetectAndRecognizeAgent.getDetectAndRecognize().jTextField4.setText("");
                        }
                    }
                }
            });
        }
}
