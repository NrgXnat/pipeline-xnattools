/* 
 *	Copyright Washington University in St Louis 2006
 *	All rights reserved
 * 	
 * 	@author Mohana Ramaratnam (Email: mramarat@wustl.edu)

*/

package org.nrg.xnattools.om;

import org.nrg.xnattools.xml.XMLSearch;

public class MrSession {
    
    public MrSession() {
        
    }
    
    public Object[] getAllMrSessionIds(String host, String username, String password) throws Exception {
        XMLSearch search = new XMLSearch(host,username,password);
        String service_session = search.createServiceSession();
        Object[] sessionIds = search.getIdentifiers(service_session,"xnat:mrSessionData.ID","xnat:mrSessionData");
        search.closeServiceSession(service_session);
        if (sessionIds != null) {
            System.out.println("Total sesions are " + sessionIds.length);
        }else {
            System.out.println("Couldnt get the sessions");
        }
        return sessionIds;
    }
    
}
