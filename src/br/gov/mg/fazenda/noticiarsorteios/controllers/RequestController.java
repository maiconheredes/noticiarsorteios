package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RequestController {
	public static final String USER = "maicon.lopes";
	public static final String PASSWORD = "##########";
	public static final String DOMAIN = "http://hesperides.fazenda.mg.gov.br";
	//public static final String DOMAIN = "http://notamineira.dnfm.fazenda.mg.gov.br";
	
	public static String request(String url, int port) {
		try {
			URL urlInstance;
			
			if (port == 80) {
				urlInstance = new URL(DOMAIN + "/" + url);
			} else {
				urlInstance = new URL(DOMAIN + ":" + port + "/" + url);
			}
			
			HttpURLConnection connection = (HttpURLConnection) urlInstance.openConnection();
			connection.setRequestProperty("accept", "application/json");
			
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
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
