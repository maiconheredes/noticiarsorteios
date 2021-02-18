package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

import br.gov.mg.fazenda.noticiarsorteios.utils.Env;
import br.gov.mg.fazenda.noticiarsorteios.utils.Routes;

public class RequestController extends AbstractController {	
	public static String request(String url, int port, String method, String body) {
		try {
			URL urlInstance;
			
			if (Env.getEnv() != "DES") {
				urlInstance = new URL(Routes.domain() + "/" + url);
			} else {
				urlInstance = new URL(Routes.domain() + ":" + port + "/" + url);
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
