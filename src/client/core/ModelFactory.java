package client.core;

import client.frontEndModel.FrontEndModel;
import client.frontEndModel.FrontEndModelManager;

/**
 * A class handling the methods for modelFactory.
 * @author Group 7
 */
public class ModelFactory {

    private ClientFactory clientFactory;
    private FrontEndModelManager frontEndModelManager;

    /**
     * A constructor instantiating the variables.
     * @param clientFactory the clientFactory
     */
   public ModelFactory(ClientFactory clientFactory){
       this.clientFactory = clientFactory;
   }

    /**
     * Returns the frontEndModelManager.
     * @return frontEndModelManager
     */
    public FrontEndModel getFrontEndModelManager() {
        if(frontEndModelManager == null){
            frontEndModelManager = new FrontEndModel(clientFactory);
        }
        return (FrontEndModel) frontEndModelManager;
    }

    /**
     * Returns the clientFactory
     * @return clientFactory
     */
    public ClientFactory getClientFactory()
    {
        return clientFactory;
    }
}
