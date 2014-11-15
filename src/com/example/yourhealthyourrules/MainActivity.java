package com.example.yourhealthyourrules;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ShareActionProvider;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		Button before = (Button) findViewById(R.id.beforeid);
		before.setOnClickListener(this);

		Button during = (Button) findViewById(R.id.duringid);
		during.setOnClickListener(this);

		Button after = (Button) findViewById(R.id.afterid);
		after.setOnClickListener(this);

		Button winner = (Button) findViewById(R.id.winnerid);
		winner.setOnClickListener(this);
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

		switch (item.getItemId()) {
		case R.id.AboutMe: {
			showMyDialog();
			return true;
		}

		case R.id.shareApp: {
			ShareActionProvider myShareActionProvider = (ShareActionProvider) item
					.getActionProvider();
			Intent sharingIntent = new Intent(
					android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			String shareBody = "the link to the app";
			sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
					"Check out my App");
			sharingIntent
					.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

			myShareActionProvider.setShareIntent(sharingIntent);
			return true;
		}

		case R.id.favs: {
			Intent myIntent = new Intent(this, List.class);
			myIntent.putExtra("typeList", 1111);

			startActivity(myIntent);
			

			return true;
		}

		case R.id.rateApp: {
			final Uri uri = Uri.parse("market://details?id="
					+ getApplicationContext().getPackageName());
			final Intent rateAppIntent = new Intent(Intent.ACTION_VIEW, uri);

			if (getPackageManager().queryIntentActivities(rateAppIntent, 0)
					.size() > 0) {
				startActivity(rateAppIntent);
			} else {
				Toast.makeText(getApplicationContext(), R.string.rateappno,
						Toast.LENGTH_SHORT).show();
				/*
				 * handle your error case: the device has no way to handle
				 * market urls
				 */
			}
			return true;
		}

		case R.id.sendfeedback: {
			startActivity(new Intent(MainActivity.this, Feedback.class));
			return true;

		}
		// start activity for feedback form

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (v.getId() == R.id.winnerid) {
			Intent myIntent = new Intent(this, Winners.class);
			startActivity(myIntent);
			

		} else {
			Intent myIntent = new Intent(this, List.class);
			myIntent.putExtra("typeList", ((Button) v).getId());

			startActivity(myIntent);
			
		}

	}

	
	public void showMyDialog() {
		AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);

		dlgAlert.setMessage(Message.readFile(getApplicationContext(),
				"aboutme.txt"));
		dlgAlert.setTitle("My Story");

		dlgAlert.setCancelable(true);
		dlgAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// dismiss the dialog
				dialog.dismiss();
			}
		});

		dlgAlert.create().show();
	}
}
