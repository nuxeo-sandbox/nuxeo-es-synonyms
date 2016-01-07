/**
 * 
 */

package org.nuxeo.synonyms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.nuxeo.common.Environment;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Context;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.automation.core.annotations.Param;
import org.nuxeo.ecm.automation.core.collectors.DocumentModelCollector;
import org.nuxeo.ecm.automation.core.collectors.BlobCollector;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;

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
