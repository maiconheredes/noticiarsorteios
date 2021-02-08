package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.AuthProvider;

public class RequestController extends AbstractController {
	public static final String USER = "maicon.lopes";
	public static final String PASSWORD = "##########";
	public static final String DOMAIN = "http://hesperides.fazenda.mg.gov.br";
	//public static final String DOMAIN = "http://notamineira.dnfm.fazenda.mg.gov.br";
	
	public static String request(String url, int port, String method, String body) {
		try {
			URL urlInstance;
			
			if (port == 80) {
				urlInstance = new URL(DOMAIN + "/" + url);
			} else {
				urlInstance = new URL(DOMAIN + ":" + port + "/" + url);
			}
			
			HttpURLConnection connection = (HttpURLConnection) urlInstance.openConnection();
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("accept", "application/json");
			
			if (method.equals("POST")) {
				connection.setDoOutput(true);
				connection.setRequestMethod(method);
				
				OutputStream os = connection.getOutputStream();
				OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");    
				osw.write(body);
				osw.flush();
				osw.close();
				os.close();
			}
			
			BufferedReader input = new BufferedReader(
				new InputStreamReader(connection.getInputStream(), Charset.forName("UTF8"))
			);
			
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = input.readLine()) != null) {
			    content.append(inputLine);
			}
			
			input.close();
			connection.disconnect();
			
			return content.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void authentication() {
		
	}
}
