package com.example.yourhealthyourrules;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Winners extends Activity implements OnClickListener {
	RadioGroup rg;
	EditText amount, lw,lm,ly, mw,mm,my, method, after, recom;
	CheckBox cb;
	String message;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.winners_form);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		amount = (EditText)findViewById(R.id.weight_lost);
		
		rg = (RadioGroup) findViewById(R.id.radiogroup1);
		ly= (EditText)findViewById(R.id.l_years);
		lm = (EditText)findViewById(R.id.l_months);
		lw = (EditText)findViewById(R.id.l_weeks);
		
		my= (EditText)findViewById(R.id.m_years);
		mm = (EditText)findViewById(R.id.m_months);
		mw = (EditText)findViewById(R.id.m_weeks);
		
		method = (EditText)findViewById(R.id.How_You_Did);
		after = (EditText)findViewById(R.id.After_Effects);
		recom  = (EditText)findViewById(R.id.Recommend);
		cb = (CheckBox)findViewById(R.id.CheckBoxShare);
		
		
		

		Button send = (Button) findViewById(R.id.WinnersSend);
		send.setOnClickListener(this);

	}
	
	public void onBackPressed(){
		finish();
	}

	@Override
	public void onClick(View v) {
		
		message += amount.getText() +  " "+((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
		message += "\nlost " + ly.getText() +" years " +  lm.getText()+ " months "+ lw.getText() + " weeks"; 
		message += "\nmaintained for " + my.getText() +" years " +  mm.getText()+ " months "+ mw.getText() + " weeks";
		message += "\n with the method " + method.getText();
		message += "\nfaced the effects " + after.getText();
		message += "\nRecommending "+ recom.getText();
		boolean use = cb.isChecked();
		message += use + " use/share ";
		Intent i = new Intent(Intent.ACTION_SEND);
		
		i.setType("message/rfc822");
		i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"imaculatemosha@yahoo.com"});
		i.putExtra(Intent.EXTRA_SUBJECT, "Advice");
		i.putExtra(Intent.EXTRA_TEXT   , message);
		
		try {
			Toast.makeText(this, "Thank you very much!", Toast.LENGTH_SHORT).show();
		    startActivity(Intent.createChooser(i, "Send suggestions"));
		    
		} catch (android.content.ActivityNotFoundException ex) {
		    Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
		}
	
			finish();
		
		
	}
		// TODO Auto-generated method stub
		
		
	}

