/**
 * 
 */

package org.nuxeo.es;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.elasticsearch.ElasticSearchComponent;
import org.nuxeo.runtime.api.Framework;
/**
 * @author mgena
 */
@Operation(id=Reindex.ID, category=Constants.CAT_EXECUTION, label="Reindex", description="")
public class Reindex {

    public static final String ID = "Reindex";
    
    /*@Param(name = "repositoryName")
    protected String repositoryName;*/
    
    @OperationMethod
    public void run() { 
    	ElasticSearchComponent esc = (ElasticSearchComponent) Framework.getRuntime().getComponent("org.nuxeo.elasticsearch.ElasticSearchComponent");    	
    	String repositoryName = "default";
    	esc.dropAndInitRepositoryIndex(repositoryName);
    	esc.runReindexingWorker(repositoryName, "SELECT ecm:uuid FROM Document");
    }    

}
