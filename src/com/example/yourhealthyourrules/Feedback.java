package com.example.yourhealthyourrules;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Feedback extends Activity implements OnClickListener {
	String name, email , type, message;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedback);
		
		
		final EditText et_name = (EditText) findViewById(R.id.EditTextName);
		name = et_name.getText().toString();
		
		final EditText et_email = (EditText) findViewById(R.id.EditTextEmail);
		email = et_email.getText().toString();
		
		final EditText et_body = (EditText) findViewById(R.id.EditTextFeedbackBody);
		message = et_body.getText().toString();
		
		final Spinner feedbackSpinner = (Spinner) findViewById(R.id.SpinnerFeedbackType);
		type = feedbackSpinner.getSelectedItem().toString();
		
		message += "From " + name + "at " + email + "about " + type + message;
		
		Button b = (Button)findViewById(R.id.ButtonSendFeedback);
		b.setOnClickListener(this);
		
				
		
	}
	

	
	
	
	public void onClick(View v){
		Intent i = new Intent(Intent.ACTION_SEND);
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"imaculatemosha@yahoo.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from app");
		i.putExtra(Intent.EXTRA_TEXT   , message);
		
		try {
		    startActivity(Intent.createChooser(i, "Send mail..."));
		    Toast.makeText(Feedback.this, "Thank you very much for the feedback", Toast.LENGTH_SHORT).show();
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(Feedback.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	
			finish();
		
		
	}
}
