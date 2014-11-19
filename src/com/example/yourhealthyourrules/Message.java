package com.example.yourhealthyourrules;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

public class Message extends Activity {

	TextView title;
	TextView message;
	String name;
	String filename;
	String article;
	private static final String TAG = Message.class.getName();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			name = bundle.getString("NAME");
			filename = bundle.getString("FILE");
		}

		title = (TextView) findViewById(R.id.message_title);
		title.setText(name);
		message = (TextView) findViewById(R.id.just_message);
		article = readFile(getApplicationContext(), filename);

		message.setText(article);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_menu, menu);
		return true;
	}

	public void writeToFile(String data, String data2) {
		try {
			String newLine = System.getProperty("line.separator");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					openFileOutput("assets/fav.txt", Context.MODE_APPEND));
			outputStreamWriter.write(data + " " + data2 + newLine);

			outputStreamWriter.close();
		} catch (IOException e) {
			Log.e(TAG, "File write failed: " + e.toString());
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.

		switch (item.getItemId()) {

		case R.id.Favourites:

		{
			SqliteHelper help = new SqliteHelper(this);
			if (!help.containsFile(filename)) {
				help.addFavourite(name, filename);
				Toast.makeText(this, "Added to favourites!", Toast.LENGTH_SHORT)
						.show();
			} else {
				Toast.makeText(this, "Already in favourites!",
						Toast.LENGTH_SHORT).show();
			}
			return true;
		}

		case R.id.ShareArticle:

		{

			Intent sharingIntent = new Intent(
					android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");

			sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, name);
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, article);

			startActivity(sharingIntent);
			return true;
		}

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public static String readFile(Context context, String filename) {

		String ret = "";

		try {

			AssetManager am = context.getAssets();
			InputStream inputStream = am.open(filename);

			if (inputStream != null) {
				InputStreamReader inputStreamReader = new InputStreamReader(
						inputStream);
				BufferedReader bufferedReader = new BufferedReader(
						inputStreamReader);
				String receiveString = "";
				StringBuilder stringBuilder = new StringBuilder();

				while ((receiveString = bufferedReader.readLine()) != null) {
					stringBuilder.append(receiveString);
				}

				inputStream.close();
				ret = stringBuilder.toString();
			}
		} catch (FileNotFoundException e) {
			Log.e(TAG, "File not found: " + e.toString());
		} catch (IOException e) {
			Log.e(TAG, "Can not read file: " + e.toString());
		}

		return ret;
	}

	public void onBackPressed() {
		finish();
	}
}
