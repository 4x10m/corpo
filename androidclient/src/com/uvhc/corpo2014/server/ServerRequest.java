package com.uvhc.corpo2014.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;

public class ServerRequest {
	static InputStream inputstream = null;
	static JSONObject jObj = null;
	static String json;

	public ServerRequest() {
	}
	
	public JSONObject getJSONFromUrl(String url) {
		Log.v("debug", url);
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			
			HttpPost httpPost = new HttpPost(url);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			
			inputstream = httpEntity.getContent();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputstream, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			inputstream.close();
			
			json = sb.toString();
			Log.v("JSON", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		
		return jObj;
	}

	public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
		Log.v("debug", url);
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			inputstream = httpEntity.getContent();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputstream, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			inputstream.close();
			
			json = sb.toString();
			Log.e("JSON", json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}
		return jObj;
	}

	JSONObject jobj;

	public JSONObject getJSON(String url, List<NameValuePair> params) {
		Params param = new Params(url, params);
		Request myTask = new Request();
		
		try {
			jobj = myTask.execute(param).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return jobj;
	}
	
	public JSONObject getJSON(String url) {
		RequestWithoutArgs myTask = new RequestWithoutArgs();
		
		try {
			jobj = myTask.execute(url).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return jobj;
	}

	private static class Params {
		String url;
		List<NameValuePair> params;

		Params(String url, List<NameValuePair> params) {
			this.url = url;
			this.params = params;
		}
	}
	
	private class RequestWithoutArgs extends AsyncTask<String, String, JSONObject> {
		@Override
		protected JSONObject doInBackground(String... args) {
			ServerRequest request = new ServerRequest();
			
			JSONObject json = request.getJSONFromUrl(args[0]);
			
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			super.onPostExecute(json);
		}
	}

	private class Request extends AsyncTask<Params, String, JSONObject> {
		@Override
		protected JSONObject doInBackground(Params... args) {
			ServerRequest request = new ServerRequest();
			
			JSONObject json = request.getJSONFromUrl(args[0].url,
					args[0].params);
			
			return json;
		}

		@Override
		protected void onPostExecute(JSONObject json) {
			super.onPostExecute(json);
		}
	}
}