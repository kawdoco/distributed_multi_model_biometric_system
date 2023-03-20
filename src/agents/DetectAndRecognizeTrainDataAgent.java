/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import person.data.PersonData;

/**
 *
 * @author mmahmoud
 */
public class DetectAndRecognizeTrainDataAgent extends Agent {
    public static Hashtable<String, PersonData> allData;

    public static Hashtable<String, PersonData> getAllData() {
        return allData;
    }

    public void setAllData(Hashtable<String, PersonData> allData) {
        this.allData = allData;
    }
    
    @Override
    protected void setup() {
        addBehaviour(
                new OneShotBehaviour(this) {
                    @Override
                    public void action() {
                        //ACLMessage msg = this.getAgent().receive();
//                        if (msg != null) {
                         //   if (msg.getContent().equals("TrainDataToRecognize")) {
                                System.out.println("######Start Data Training#####################3");
                                try {
                                    Hashtable<String, PersonData> allData = buildFaceDBdata("C:\\Imgs\\Data.txt");
                                   // Test.TrainData.TrainData();
                                    
                                    
                                    setAllData(allData);
                                    System.out.println("###########Obtaining Training Data#################");
                                    if (DetectAndRecognizeAgent.getDetectAndRecognize() != null) {
                                        DetectAndRecognizeAgent.getDetectAndRecognize().setAllData(allData);
                                        System.out.println("###########Set Obtained Training Data#################");
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(DetectAndRecognizeTrainDataAgent.class.getName()).log(Level.SEVERE, null, ex);
                                }
                          //  }
                        }
                    //}
                });
    }



    public Hashtable<String, PersonData> buildFaceDBdata(String FilePath) throws IOException {
        Hashtable<String, PersonData> allData = new Hashtable<String, PersonData>();
        BufferedReader br = new BufferedReader(new FileReader(FilePath));
        try {
            String line = br.readLine();

            while (line != null) {

                String[] parts = line.split("-");
                String ID = parts[0];

                String name = parts[1];

                String Age = parts[2];

                String adress = parts[3];

                String phone = parts[4];

                PersonData data = new PersonData();
                data.setAddress(adress);
                data.setAge(Age);
                data.setPhone(phone);
                data.setName(name);
                allData.put(ID, data);
                line = br.readLine();
            }

        } finally {
            br.close();
        }
        return allData;
    }
}
