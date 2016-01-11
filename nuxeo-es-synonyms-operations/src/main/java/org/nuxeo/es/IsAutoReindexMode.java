/**
 * 
 */

package org.nuxeo.es;

import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.collectors.BlobCollector;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.runtime.api.Framework;

/**
 * @author mgena
 */
@Operation(id=IsAutoReindexMode.ID, category=Constants.CAT_SERVICES, label="IsAutoReindexMode", description="")
public class IsAutoReindexMode {

    public static final String ID = "IsAutoReindexMode";

    @OperationMethod(collector=BlobCollector.class)
    public Blob run() {
    	//Auto Reindex ElastichSearch
    	String autoReindex = Framework.getProperty("org.nuxeo.synonyms.autoreindex");
    	if(autoReindex != null && !("true").equals(autoReindex)){
    		return new StringBlob("true");
    	}else{
    		return new StringBlob("false");
    	}
    }    

}
