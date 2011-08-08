package dayton.clickatell.smsTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;



public class SendSms {
	private static final String API_ID = "3322637";
	private static final String USER = "fransico.baker";
	private static final String PASSWORD = "Sybase123";
	private static final String POST_URL = "http://api.clickatell.com/xml/xml";
	
	public static void main(String[] args) {
		Authenticator.setDefault (new MyAuthenticator(USER, PASSWORD));
		String body = buildXML(API_ID,USER,PASSWORD,"27824211447","This is a test message");
		String response = "";
		try {
			response = postPage(POST_URL, body);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(response);
		
	}
	
	public static String sendMessage(String url,String apiId,String user,String password,String cellNumber,String message) {
		Authenticator.setDefault (new MyAuthenticator(user, password));
		String body = buildXML(apiId,user,password,cellNumber,message);
		String response = "";
		try {
			response = postPage(url, body);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String buildXML(String api,String user,String pwd,String cellNumber,String text) {
		String body = "<clickAPI>"
				+"<sendMsg>"
				+"<api_id>" +API_ID+ "</api_id>"
				+"<user>" +USER+ "</user>"
				+"<password>" +PASSWORD+ "</password>"
				+"<to>" +cellNumber+ "</to>"
				+"<text>" +text+ "</text>"
				+"</sendMsg>"
				+"</clickAPI>";
		return body;
	}
	
	public static String buildXMLAuthentication() {
		String body = "<clickAPI>"
				+"<auth>"
				+"<api_id>" +API_ID+ "</api_id>"
				+"<user>" +USER+ "</user>"
				+"<password>" +PASSWORD+ "</password>"
				+"</auth>"
				+"</clickAPI>";
		return body;
	}
	
	public static void postPageGetSessionId(final String page, final String body) 
	throws IOException {

		try
		{
			URL url = new URL(page);


			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setAllowUserInteraction(true); // can you ask the user
			conn.setDoOutput(true); // we want to send things
			conn.setDoInput(true);
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Content-Length", Integer.toString(body.length()));

			OutputStream rawOutStream = conn.getOutputStream();
			PrintWriter pw = new PrintWriter(rawOutStream);

			pw.print("data="+body); 
			pw.flush();
			pw.close();

			try {

				InputStream  rawInStream = (InputStream) conn.getInputStream();


				// get response
				BufferedReader rdr = new BufferedReader(new InputStreamReader(rawInStream));
				String line;

				StringBuffer sb = new StringBuffer();
				String newline = null;
				try {
					newline = System.getProperty("line.separator");
				} catch (Exception e) {
					newline = "\n";
				}
				while ((line = rdr.readLine()) != null) {
					sb.append(line);
					sb.append(newline);
				}


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			
		}


	}
	
	public static String postPage(final String page, final String body) 
	throws IOException {
		
		String response = "";

		try
		{
			URL url = new URL(page);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setAllowUserInteraction(true); // can you ask the user
			conn.setDoOutput(true); // we want to send things


			OutputStream rawOutStream = conn.getOutputStream();
			PrintWriter pw = new PrintWriter(rawOutStream);

			pw.print("data="+body); 
			pw.flush();
			pw.close();


			try {

				InputStream  rawInStream = (InputStream) conn.getInputStream();


				// get response
				BufferedReader rdr = new BufferedReader(new InputStreamReader(rawInStream));
				String line;

				StringBuffer sb = new StringBuffer();
				String newline = null;
				try {
					newline = System.getProperty("line.separator");
				} catch (Exception e) {
					newline = "\n";
				}
				while ((line = rdr.readLine()) != null) {
					sb.append(line);
					sb.append(newline);
				}
				
				response = sb.toString();


			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		catch (Exception e)
		{
			
		}
		
		return response;

	}
}
