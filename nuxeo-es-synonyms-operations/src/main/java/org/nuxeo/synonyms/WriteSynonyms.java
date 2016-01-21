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
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
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
    	if(StringUtils.isNotBlank(synonymsPath)){
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

			// if file doesn't exists, then create it
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
