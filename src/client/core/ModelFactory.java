package client.core;

import client.frontEndModel.FrontEndModel;
import client.frontEndModel.FrontEndModelManager;


public class ModelFactory {

    private ClientFactory clientFactory;
    private FrontEndModelManager frontEndModelManager;

   public ModelFactory(ClientFactory clientFactory){
       this.clientFactory = clientFactory;
   }

    public FrontEndModel getFrontEndModelManager() {
        if(frontEndModelManager == null){
            frontEndModelManager = new FrontEndModel(clientFactory);
        }
        return (FrontEndModel) frontEndModelManager;
    }
}
