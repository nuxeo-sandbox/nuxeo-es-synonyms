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
