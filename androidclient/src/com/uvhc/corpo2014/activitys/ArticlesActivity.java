package com.uvhc.corpo2014.activitys;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.uvhc.corpo2014.R;
import com.uvhc.corpo2014.R.id;
import com.uvhc.corpo2014.R.layout;
import com.uvhc.corpo2014.R.menu;
import com.uvhc.corpo2014.core.Article;
import com.uvhc.corpo2014.server.ServerCommand;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ArticlesActivity extends ListActivity {
	ListView listview;
	
	ImageLoaderConfiguration config;
	Article[] articles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_articles); 
		
		config = new ImageLoaderConfiguration.Builder(this).build();
		ImageLoader.getInstance().init(config);
		
		Log.v("dev", "plop");
		articles = Article.loadArticlesFromDatabase();
		
	    ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

	    for (int i = 0; i < articles.length; i++) {



		    HashMap<String, String> map = new HashMap<String, String>();
		    
		    map.put("id", String.valueOf(i));
		    map.put("name", articles[i].getName());
		    map.put("imageurl", articles[i].getPictureURL());
		    map.put("price", articles[i].getPrice());
		    mylist.add(map);
		}
	    ArticlesListViewAdapter adapter = new ArticlesListViewAdapter(this, mylist, R.layout.article_layout,
	            new String[] { "name", "price" }, new int[] { R.id.articlenametextview,
	                    R.id.articledescriptiontextview});
	    setListAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.articles, menu);
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
	
	
	private class ArticlesListViewAdapter extends SimpleAdapter {
		private Context context;
		ArrayList<HashMap<String, String>> data;
		int listid;
		String[] fieldnames;
		int[] textviewids;
		
		
		public ArticlesListViewAdapter(Context context, ArrayList<HashMap<String, String>> data, int listid, String[] fieldsnames, int[] textViewsIDs) {
			super(context, data, listid, fieldsnames, textViewsIDs);
			
			this.context = context;
			this.data = data;
			this.listid = listid;
			this.fieldnames = fieldsnames;
			this.textviewids = textViewsIDs;
		}
		
		@Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.article_layout, parent, false);
		    
		    ImageView iconimageview = (ImageView) rowView.findViewById(R.id.articleiconimageview);
		    //TextView nametextview = (TextView) rowView.findViewById(R.id.articlenametextview);
		    //TextView descriptiontextview = (TextView) rowView.findViewById(R.id.articledescriptiontextview);
		    
		    //iconwebview.loadUrl(articles[position].getPictureURL());
		    URL url;
			try {
				url = new URL("http://192.168.42.50:8080/img/avatar/woman-drawing-image.jpg");
				//Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
			    //iconimageview.setImageBitmap(bmp);
			    iconimageview.setTag(url.toString());
	            ImageLoader.getInstance().displayImage(url.toString(), iconimageview, DisplayImageOptions.createSimple());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for (int i = 0; i < fieldnames.length; i++) {
	            TextView tv = (TextView) rowView.findViewById(textviewids[i]);
	            tv.setText(data.get(position).get(fieldnames[i]));              
	        }
		    
		    return rowView;
		  }
	}
}
