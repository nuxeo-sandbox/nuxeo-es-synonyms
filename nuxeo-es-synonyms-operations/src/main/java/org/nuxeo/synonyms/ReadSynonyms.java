/*
 * (C) Copyright 2016 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 *     mgena
 */

package org.nuxeo.synonyms;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;
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
	    	if(StringUtils.isNotBlank(synonymsPath)){
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
