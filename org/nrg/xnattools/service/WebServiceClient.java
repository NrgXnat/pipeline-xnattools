/* 
 *	Copyright Washington University in St Louis 2006
 *	All rights reserved
 * 	
 * 	@author Mohana Ramaratnam (Email: mramarat@wustl.edu)

*/

package org.nrg.xnattools.service;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.nrg.xnattools.xml.AbsService;
import org.restlet.Client;
import org.restlet.data.Cookie;
import org.restlet.data.Method;
import org.restlet.data.Reference;
import org.restlet.data.Request;
import org.restlet.data.Response;

public class WebServiceClient extends AbsService {


	
	public WebServiceClient(String host, String username, String password) {
		super(host, username, password);
	}
	
	public String connect(String uriStr) throws URISyntaxException, ServiceException,MalformedURLException, RemoteException, IOException{
		String rtn = "";
		URI uri = new URI(host +  uriStr);
		String userSessionId = createServiceSession();
		 // Define our Restlet HTTP client.
	      Client client = new Client(uri.getScheme());
	      // The URI of the resource "list of items".
	      Reference rscUri = new Reference(uri.toString());
	      rtn = get(userSessionId, client,rscUri);
	      closeServiceSession(userSessionId);
		return rtn;
	}
	
	
	
	public void connect(String uriStr, OutputStream stream) throws URISyntaxException, ServiceException,MalformedURLException, RemoteException, IOException{
		String rtn = "";
		URI uri = new URI(host +  uriStr);
		String userSessionId = createServiceSession();
		 // Define our Restlet HTTP client.
	      Client client = new Client(uri.getScheme());
	      // The URI of the resource "list of items".
	      Reference rscUri = new Reference(uri.toString());
	      get(userSessionId, client,rscUri, stream);
	      closeServiceSession(userSessionId);
	}
		
	
	 public String get(String userSessionId, Client client, Reference reference)   throws IOException {
		 	Request request = new Request(Method.GET, reference);
		 	request.getCookies().add(new Cookie("JSESSIONID", userSessionId));
		 	String rtn = "";
		 	StringWriter stringWriter = new StringWriter();
		 	try {
		 	Response response = client.handle(request);
		 	if (response.getStatus().isSuccess()) {
		 		if (response.isEntityAvailable()) {
		 			response.getEntity().write(stringWriter);
		 			rtn = stringWriter.toString();
		 		}
		 	}
		 	}catch (IOException ioe) {
		 		stringWriter.close(); throw ioe;
		 	}
		 	return rtn;
	 }
	 
	 public String post(String userSessionId, Client client, Reference reference)   throws IOException {
		 	Request request = new Request(Method.POST, reference);
		 	request.getCookies().add(new Cookie("JSESSIONID", userSessionId));
		 	String rtn = "";
		 	StringWriter stringWriter = new StringWriter();
		 	try {
		 	Response response = client.handle(request);
		 	if (response.getStatus().isSuccess()) {
		 		if (response.isEntityAvailable()) {
		 			response.getEntity().write(stringWriter);
		 			rtn = stringWriter.toString();
		 		}
		 	}
		 	}catch (IOException ioe) {
		 		stringWriter.close(); throw ioe;
		 	}
		 	return rtn;
	 }
	 
	 public String put(String userSessionId, Client client, Reference reference)   throws IOException {
		 	Request request = new Request(Method.PUT, reference);
		 	request.getCookies().add(new Cookie("JSESSIONID", userSessionId));
		 	String rtn = "";
		 	StringWriter stringWriter = new StringWriter();
		 	try {
		 	Response response = client.handle(request);
		 	if (response.getStatus().isSuccess()) {
		 		if (response.isEntityAvailable()) {
		 			response.getEntity().write(stringWriter);
		 			rtn = stringWriter.toString();
		 		}
		 	}
		 	}catch (IOException ioe) {
		 		stringWriter.close(); throw ioe;
		 	}
		 	return rtn;
	 }

	 
	 public void get(String userSessionId, Client client, Reference reference, OutputStream stream)   throws IOException {
		 	Request request = new Request(Method.GET, reference);
		 	request.getCookies().add(new Cookie("JSESSIONID", userSessionId));
		 	try {
		 	Response response = client.handle(request);
		 	if (response.getStatus().isSuccess()) {
		 		if (response.isEntityAvailable()) {
		 			response.getEntity().write(stream);
		 		}
		 	}
		 	}catch (IOException ioe) {
		 		 throw ioe;
		 	}
	 }


	
	
	
	public static void main(String args[]) {
		String host = args[0];
		String uriStr = args[1];
		String uname = args[2];
		String passwd = args[3];
		try {
			WebServiceClient client = new WebServiceClient(host, uname, passwd);
			String rtn = client.connect(uriStr);
			System.out.println("DONE:"+rtn);
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("URI is invalid " + uriStr);
			System.exit(1);
		}
		System.exit(0);
	}
	
}
