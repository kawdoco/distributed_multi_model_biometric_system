/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TrainData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import person.data.PersonData;

/**
 *
 * @author mmahmoud
 */
public class TrainData {

  

    public static Hashtable<String, PersonData> buildFaceDBdata(String FilePath) throws IOException {
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
