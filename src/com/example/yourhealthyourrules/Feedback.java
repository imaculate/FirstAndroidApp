package com.example.yourhealthyourrules;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Feedback extends Activity implements OnClickListener {
	String name, email , type, message;
	 EditText et_name;
	 EditText et_email;
	EditText et_body;
	Spinner feedbackSpinner ;
	
	public void onBackPressed(){
		finish();
	}
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); 
		 et_name = (EditText) findViewById(R.id.EditTextName);
		
		
		et_email = (EditText) findViewById(R.id.EditTextEmail);
		
		
		et_body = (EditText) findViewById(R.id.EditTextFeedbackBody);
		
		
		feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);

		
		
		
		Button b = (Button)findViewById(R.id.ButtonSendFeedback);
		b.setOnClickListener(this);
		
				
		
	}
	

	
	
	
	public void onClick(View v){
		name = et_name.getText().toString();
		
		email = et_email.getText().toString();
		message = et_body.getText().toString();
		type = feedbackSpinner.getSelectedItem().toString();
		
		message += "From " + name + " at " + email + " about \n" + type + "\n"+ message;
		
		
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"imaculatemosha@yahoo.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from app");
		i.putExtra(Intent.EXTRA_TEXT   , message);
		
		try {
			Toast.makeText(Feedback.this, "Thank you very much for the feedback", Toast.LENGTH_SHORT).show();
		    startActivity(Intent.createChooser(i, "Send mail..."));
		    
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(Feedback.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	
			finish();
		
		
	}
}
