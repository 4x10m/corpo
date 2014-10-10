package com.uvhc.corpo2014.core;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.uvhc.corpo2014.server.ServerCommand;

import android.util.Log;
import android.view.View;

public class Article {
	private String id;
	private String pictureurl;
	private String name;
	private String description;
	private String price;
	
	public String getPictureURL() {
		return pictureurl;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getPrice() {
		return price;
	}
	
	public Article(String id, String pictureurl, String name, String description, String price) {
		this.id = id;
		this.pictureurl = pictureurl;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public void save() {
		//TODO
	}
	
	public static Article loadArticleFromDatabase(int id) {
		return ServerCommand.getArticle(id);
	}
	
	public static Article[] loadArticlesFromDatabase() {
		JSONObject json = ServerCommand.getArticles();
		ArrayList<Article> articles = new ArrayList<Article>();
		Article article;
		
		try {
			if(json != null) {
				String jsonstr = json.getString("response");
				
				if(json.getBoolean("res")){
					JSONArray jsonarticles = json.getJSONArray("articles");

					if(jsonarticles.length() > 0) {
						for(int i = 0; i < jsonarticles.length(); i++) {
							String id = jsonarticles.getJSONObject(i).getString("_id");
							String name = jsonarticles.getJSONObject(i).getString("name");
							String description = jsonarticles.getJSONObject(i).getString("description");
							String pictureurl = jsonarticles.getJSONObject(i).getString("pictureurl");
							String price = jsonarticles.getJSONObject(i).getString("price");
							
							article = new Article(id, pictureurl, name, description, price);
							
							articles.add(article);
						}
					}
				}
				
				Log.v("log", jsonstr);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return (Article[]) articles.toArray(new Article[articles.size()]);
	}
}
