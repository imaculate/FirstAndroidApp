package com.example.yourhealthyourrules;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;


public class Message extends Activity{
	
	TextView title;
	TextView message;
	String name;
	String filename;
	private static final String TAG = Message.class.getName();
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		
		Bundle bundle = getIntent().getExtras();
		if(bundle!=null){
			name = bundle.getString("NAME");
			filename = bundle.getString("FILE");
		}
		
		title = (TextView)findViewById(R.id.message_title);
		title.setText(name);
		message = (TextView)findViewById(R.id.just_message);
		
		message.setText(readFile(getApplicationContext(), filename));
		
		
	}
	
	public static String readFile(Context context, String filename) {
        
        String ret = "";
         
        try {
        
        	AssetManager am = context.getAssets();
            InputStream inputStream = am.open(filename);
            
           
          
            
            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                 
                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                 
                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e(TAG, "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e(TAG, "Can not read file: " + e.toString());
        }
 
        return ret;
    }
}
