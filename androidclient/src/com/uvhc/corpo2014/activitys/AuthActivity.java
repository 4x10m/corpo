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
import com.uvhc.corpo2014.core.User;
import com.uvhc.corpo2014.server.ServerCommand;
import com.uvhc.corpo2014.server.ServerRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AuthActivity extends Activity {
	EditText usernameEditText;
	EditText passwordEditText;
	
	Button loginButton;
	Button registerButton;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        
        usernameEditText = (EditText)findViewById(R.id.emailEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        
        loginButton = (Button)findViewById(R.id.authRegisterButton);
        registerButton = (Button)findViewById(R.id.registerButton);
        
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                
                User user = User.login(username, password);
                
                if(user != null){
                	Intent profileintent = new Intent(AuthActivity.this, HomeActivity.class);
                	profileintent.putExtra("token", user.getToken());
                			
	               	startActivity(profileintent);
                }
            }
        });
        
        registerButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(AuthActivity.this, RegisterActivity.class));
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.auth, menu);
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
