package com.uvhc.corpo2014.activitys;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.uvhc.corpo2014.R;
import com.uvhc.corpo2014.R.id;
import com.uvhc.corpo2014.R.layout;
import com.uvhc.corpo2014.R.menu;
import com.uvhc.corpo2014.server.ServerCommand;
import com.uvhc.corpo2014.server.ServerRequest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	EditText emailEditText;
	EditText passwordEditText;
	
	Button registerButton;
	
	List<NameValuePair> args;
	ServerRequest sr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		emailEditText = (EditText)findViewById(R.id.emailEditText);
		passwordEditText = (EditText)findViewById(R.id.passwordEditText);
		
		registerButton = (Button)findViewById(R.id.authRegisterButton);
		
		registerButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                
                JSONObject json = ServerCommand.register(email, password);
                
                if(json != null){
                    try{
                        String jsonstr = json.getString("response");
                        
                        Toast.makeText(getApplication(),jsonstr,Toast.LENGTH_LONG).show();
                        
                        Log.d("Hello", jsonstr);
                    }catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
