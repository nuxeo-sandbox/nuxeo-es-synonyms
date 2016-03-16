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

import org.apache.commons.lang.StringUtils;
import org.nuxeo.ecm.automation.core.Constants;
import org.nuxeo.ecm.automation.core.annotations.Operation;
import org.nuxeo.ecm.automation.core.annotations.OperationMethod;
import org.nuxeo.ecm.core.api.Blob;
import org.nuxeo.ecm.core.api.impl.blob.StringBlob;
import org.nuxeo.runtime.api.Framework;

/**
 * @author mgena
 */
@Operation(id=IsAutoReindexMode.ID, category=Constants.CAT_SERVICES, label="IsAutoReindexMode", description="")
public class IsAutoReindexMode {

    public static final String ID = "IsAutoReindexMode";

    @OperationMethod
    public Blob run() {
    	//Auto Reindex ElastichSearch
    	String autoReindex = Framework.getProperty("org.nuxeo.synonyms.autoreindex");
    	if(StringUtils.isNotBlank(autoReindex)){
    		return new StringBlob("true");
    	}else{
    		return new StringBlob("false");
    	}
    }    

}
