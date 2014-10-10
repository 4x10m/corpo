package com.uvhc.corpo2014.core;

import java.io.Console;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.uvhc.corpo2014.server.ServerCommand;

public class User {
	private String email;
	private String password;
	private String token;
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getToken() {
		return token;
	}
	
	public User(String email, String password, String token) {
		this.email = email;
		this.password = password;
		this.token = token;
	}
	
	public static User login(String email, String password) {
		JSONObject json = ServerCommand.login(email, password);
		User user = null;
		
		try {
		if(json != null) {
			String jsonstr = json.getString("response");
			
			if(json.getBoolean("res")){
				user = new User(email, password, json.getString("token"));
			}
			
			Log.v("log", jsonstr);
		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return user;
	}
}
