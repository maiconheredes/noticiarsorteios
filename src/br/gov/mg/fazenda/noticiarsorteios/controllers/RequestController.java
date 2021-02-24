package br.gov.mg.fazenda.noticiarsorteios.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.opencms.util.CmsRequestUtil;

import br.gov.mg.fazenda.noticiarsorteios.utils.Env;
import br.gov.mg.fazenda.noticiarsorteios.utils.Routes;

public class RequestController extends AbstractController {	
	public static String request(
		String url, int port, String method, 
		String body, HttpServletRequest request
	) {
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
			connection.setRequestProperty("Authorization", authentication(request));
			
			System.out.println("URL: " + urlInstance);
			
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
	
	private static String authentication(HttpServletRequest request) {
		if (Env.getEnv().equals("DES") || request == null) return "";
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Long timming = (Long) CmsRequestUtil.getSessionValue(request, "timming");
		String token = (String) CmsRequestUtil.getSessionValue(request, "token");
		
		if (timming == null || timming < timestamp.getTime()) {
			timming = (timestamp.getTime() + (3600 * 1000 * 3));
			
			try {
				URL urlInstance = new URL(Routes.domain() + "/prefix");
				
				HttpURLConnection connection = (HttpURLConnection) urlInstance.openConnection();
				connection.setRequestProperty("App-Get", "59f7508dbf673313aeeb9a1b519b1cea");
				
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
				
				token = "Bearer " + content.toString();
			} catch (Exception e) {
				e.printStackTrace();
				token = "";
			}
			
			CmsRequestUtil.setSessionValue(request, "timming", timming);
			CmsRequestUtil.setSessionValue(request, "token", token);
		}
		
		return token;
	}
}
