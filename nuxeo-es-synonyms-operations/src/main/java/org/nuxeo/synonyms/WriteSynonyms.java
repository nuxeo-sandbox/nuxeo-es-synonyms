/**
 * 
 */

package org.nuxeo.synonyms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.nuxeo.common.Environment;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.elasticsearch.ElasticSearchComponent;
import org.nuxeo.runtime.api.Framework;

/**
 * @author mgena
 */
@Operation(id=WriteSynonyms.ID, category=Constants.CAT_DOCUMENT, label="WriteSynonyms", description="")
public class WriteSynonyms {

    public static final String ID = "WriteSynonyms";
    
    @Param(name = "synonyms")
    protected String synonyms;
    
    @OperationMethod
    public void run() {
    	String nuxeoHomePath = Environment.getDefault().getServerHome().getAbsolutePath(); 
    	writeToFile(nuxeoHomePath+File.separator+"nxserver"+File.separator+"config"+File.separator+"synonyms.txt", synonyms);
    	writeToFile(nuxeoHomePath+File.separator+"Templates"+File.separator+"es"+File.separator+"config"+File.separator+"synonyms.txt", synonyms);
    	
    	//For Standalone ElasticSearch
    	String synonymsPath = Framework.getProperty("org.nuxeo.synonyms.path");
    	if(synonymsPath != null && !("").equals(synonymsPath)){
    		writeToFile(synonymsPath, synonyms);
    	}
    	 	
    	//Auto Reindex ElastichSearch
    	String autoReindex = Framework.getProperty("org.nuxeo.synonyms.autoreindex");
    	if(autoReindex != null && ("true").equals(autoReindex)){
    		ElasticSearchComponent esc = (ElasticSearchComponent) Framework.getRuntime().getComponent("org.nuxeo.elasticsearch.ElasticSearchComponent");    	
    		String repositoryName = "default";
    		esc.dropAndInitRepositoryIndex(repositoryName);
    		esc.runReindexingWorker(repositoryName, "SELECT ecm:uuid FROM Document");
    	}
    }    
    
    public void writeToFile(String path, String synonyms) {
		FileOutputStream fop = null;
		File file;
		try {
			file = new File(path);
			fop = new FileOutputStream(file);

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// get the content in bytes
			byte[] contentInBytes = synonyms.getBytes();

			fop.write(contentInBytes);
			fop.flush();
			fop.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 

}
