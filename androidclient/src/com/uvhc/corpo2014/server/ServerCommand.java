package com.uvhc.corpo2014.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.uvhc.corpo2014.core.Article;

public class ServerCommand {
	private static String REQUESTFORMAT = "http://%s:%d/%s"; // http://ip(string):port(int)/command(string)
	private static String HOST = "192.168.42.50";
	private static int PORT = 8080;
	
	private static ServerRequest serverrequest = new ServerRequest();
	
	public static JSONObject login(String email, String password) {
		List<NameValuePair> args = new ArrayList<NameValuePair>();
		String command = "login";
        
        args.add(new BasicNameValuePair("email", email));
        args.add(new BasicNameValuePair("password", password));
        
        return serverrequest.getJSON(String.format(REQUESTFORMAT, HOST, PORT, command), args);
	}
	
	public static JSONObject register(String email, String password) {
		List<NameValuePair> args = new ArrayList<NameValuePair>();
		String command = "register";
		
		args = new ArrayList<NameValuePair>();
        args.add(new BasicNameValuePair("email", email));
        args.add(new BasicNameValuePair("password", password));
        
        return serverrequest.getJSON(String.format(REQUESTFORMAT, HOST, PORT, command), args);
	}

	public static Article getArticle(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static JSONObject getArticles() {
		String command = "getarticles";
		
		JSONObject json = serverrequest.getJSON(String.format(REQUESTFORMAT, HOST, PORT, command));
		
		return json;
	}
}
