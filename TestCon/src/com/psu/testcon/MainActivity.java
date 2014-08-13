package com.psu.testcon;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String result = "";
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://192.168.0.128/TestConnect/index.aspx?rnd"+Math.random());

        try {
        	ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();
        	data.add(new BasicNameValuePair("username", "NCMA"));
        	data.add(new BasicNameValuePair("password", "admin"));
        	try {
				post.setEntity(new UrlEncodedFormEntity(data,HTTP.UTF_8));
	        	HttpResponse response = client.execute(post);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Log.i("TEST", "5");
        	try {
				JSONObject obj = new JSONObject(result);
				Log.i("RESULT",obj.getString("COUNT"));
				if (obj.getString("COUNT").equals("0")) {
					Toast.makeText(getApplicationContext(), "LOGIN SUCCESS", Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(getApplicationContext(), "LOGIN FALLED!!", Toast.LENGTH_LONG).show();
				}
				
			} catch (JSONException e) {
				// TODO: handle exception
				e.printStackTrace();
				Log.i("ERROR",e.getMessage());
			}
        }
        catch(Exception ex)
        {
     			ex.printStackTrace();
     			Log.i("ERROR",ex.getMessage());
        }
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
