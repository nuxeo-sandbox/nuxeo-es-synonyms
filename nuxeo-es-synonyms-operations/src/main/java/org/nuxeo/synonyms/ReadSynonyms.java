/**
 * 
 */

package org.nuxeo.synonyms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.nuxeo.common.Environment;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.runtime.api.Framework;

/**
 * @author mgena
 */
@Operation(id=ReadSynonyms.ID, category=Constants.CAT_DOCUMENT, label="ReadSynonyms", description="")
public class ReadSynonyms {

    public static final String ID = "ReadSynonyms";

    @OperationMethod
    public Blob run() {
    	String nuxeoHomePath = Environment.getDefault().getServerHome().getAbsolutePath();    	
    	Scanner s;
    	String synonyms = "";
		try {
			//For Standalone ElasticSearch
	    	String synonymsPath = Framework.getProperty("org.nuxeo.synonyms.path");
	    	if(synonymsPath != null && !("").equals(synonymsPath)){
	    		s = new Scanner(new File(synonymsPath));
	    	}else{
	    		s = new Scanner(new File(nuxeoHomePath+File.separator+"nxserver"+File.separator+"config"+File.separator+"synonyms.txt"));
	    	}
			ArrayList<String> list = new ArrayList<String>();
	    	while (s.hasNextLine()){
	    		String synonymLine = s.nextLine();
	    	    list.add(synonymLine);
	    	    synonyms += synonymLine+"\n";
	    	}
	    	s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
        //return synonyms;
		return new StringBlob(synonyms); 
    }    

}
