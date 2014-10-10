package com.uvhc.corpo2014.activitys;

import com.uvhc.corpo2014.R;
import com.uvhc.corpo2014.R.id;
import com.uvhc.corpo2014.R.layout;
import com.uvhc.corpo2014.R.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class OrdersActivity extends Activity {
	Button neworderbutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_orders);
		
		neworderbutton = (Button)findViewById(R.id.homeNewOrderButton);
		neworderbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent neworderintent = new Intent(OrdersActivity.this, NewOrderActivity.class);
				
				startActivity(neworderintent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
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
