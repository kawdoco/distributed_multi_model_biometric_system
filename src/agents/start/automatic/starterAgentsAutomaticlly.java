/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agents.start.automatic;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
/**
 *
 * @author mmahmoud
 */
public class starterAgentsAutomaticlly {

    public static AgentController getStartAddFaceAgentSender() {
        return startAddFaceAgentSender;
    }

    public static void setStartAddFaceAgentSender(AgentController startAddFaceAgentSender) {
        starterAgentsAutomaticlly.startAddFaceAgentSender = startAddFaceAgentSender;
    }

    public static ContainerController getAddfaceAgentConainer() {
        return addfaceAgentConainer;
    }

    public static void setAddfaceAgentConainer(ContainerController addfaceAgentConainer) {
        starterAgentsAutomaticlly.addfaceAgentConainer = addfaceAgentConainer;
    }

    public static AgentController startAddFaceAgentSender;
    public static ContainerController addfaceAgentConainer;
    
    public static void main(String[] args) throws StaleProxyException, InterruptedException {
        /*Runtime rt = Runtime.instance();
        Profile pMain = new ProfileImpl();
        ContainerController agentConainer = rt.createMainContainer(pMain);
 
        AgentController ag = agentConainer.createNewAgent("rma", "jade.tools.rma.rma", null);
        ag.start();
       
        ContainerController faceDetectAgentConainer = rt.createAgentContainer(pMain);
        AgentController detectAndRecognizeAgent= faceDetectAgentConainer.createNewAgent("DetectAndRecognizeAgent", "agents.DetectAndRecognizeAgent", null);
        AgentController startDetectAndRecognizeAgent= faceDetectAgentConainer.createNewAgent("StartDetectAndRecognizeAgent", "agents.StartDetectAndRecognizeAgent", null);

        detectAndRecognizeAgent.start();
        startDetectAndRecognizeAgent.start();*/
        
        Runtime rt = Runtime.instance();
        Profile pMain = new ProfileImpl("127.0.0.1", 8888, null);
        
        /*Create Main Container*/
        AgentContainer mainContainerRef = rt.createMainContainer(pMain); //DF and AMS are include
        System.out.println("Main container created.");
        AgentController rmaAgent = mainContainerRef.createNewAgent("rma", "jade.tools.rma.rma", null);
        rmaAgent.start();
        ProfileImpl pContainer = new ProfileImpl(null, 8888, null);
        
        /* Part 1 : Create Add Face Info Container 
        addfaceAgentConainer = rt.createAgentContainer(pContainer);
        setAddfaceAgentConainer(addfaceAgentConainer);
        
        System.out.println("AddfaceAgent container created.");
        AgentController addDataFaceAgent = addfaceAgentConainer.createNewAgent("AddDataFaceDetectionAgent", "agents.AddDataFaceDetectionAgent", null);
        startAddFaceAgentSender = addfaceAgentConainer.createNewAgent("StartAddFaceAgentSender", "agents.StartAddFaceAgentSender", null);
        setStartAddFaceAgentSender(startAddFaceAgentSender);
        addDataFaceAgent.start();
        startAddFaceAgentSender.start();
    */
        
        /* Part 2: Create DetectAndRecognize Container  */ 
        ContainerController faceDetectAgentConainer = rt.createAgentContainer(pContainer);
        System.out.println("faceDetectAgent container created.");
        AgentController detectAndRecognizeAgent = faceDetectAgentConainer.createNewAgent("DetectAndRecognizeAgent", "agents.DetectAndRecognizeAgent", null);
        AgentController startDetectAndRecognizeAgent = faceDetectAgentConainer.createNewAgent("StartDetectAndRecognizeAgent", "agents.StartDetectAndRecognizeAgent", null);
        AgentController clearFieldsDetectAndRecognizeAgent = faceDetectAgentConainer.createNewAgent("ClearFieldsDetectAndRecognizeAgent", "agents.ClearFieldsDetectAndRecognizeAgent", null);
        AgentController reteriveFieldsDetectAndRecognizeAgent = faceDetectAgentConainer.createNewAgent("ReteriveFieldsDetectAndRecognizeAgent", "agents.ReteriveFieldsDetectAndRecognizeAgent", null);
        AgentController detectAndRecognizeTrainDataAgent = faceDetectAgentConainer.createNewAgent("DetectAndRecognizeTrainDataAgent", "agents.DetectAndRecognizeTrainDataAgent", null);

        detectAndRecognizeTrainDataAgent.start();
        Thread.sleep(3000);
        detectAndRecognizeAgent.start();       

        startDetectAndRecognizeAgent.start();      
        clearFieldsDetectAndRecognizeAgent.start();
        reteriveFieldsDetectAndRecognizeAgent.start();
       
    }
}
//		AgentController ageCont = myAgent.getContainerController().getAgent(loadAgentName);
//		
//Local Name AddDataFaceDetectionAgent
//Local Name StartAddFaceAgentSender
// PauseAddFaceAgentSender
// PersonDataLoaderAddFaceAgentSender
// 
// 
//AgentController startAddFaceAgentSender = addfaceAgentConainer.createNewAgent("StartAddFaceAgentSender", "agents.StartAddFaceAgentSender", null);
//addDataFaceAgent.start();