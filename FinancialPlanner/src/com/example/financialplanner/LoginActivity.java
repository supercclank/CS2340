package com.example.financialplanner;

import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class LoginActivity extends Activity {
	private HashMap<String,User> loginList;
	private AlertDialog.Builder failedLogIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		loginList = new HashMap<String,User>();
		failedLogIn = new AlertDialog.Builder(this)
		    .setTitle("Invalid login attempt")
		    .setMessage("An unknown user and password combination was entered")
		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        }
		     });
		 loginList.put("derp", new User("derp", "derp"));
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	public void attemptSignIn(View v){
		EditText userName = (EditText) findViewById(R.id.username);
		EditText passWord = (EditText) findViewById(R.id.password);
		System.out.println(userName.getText().toString());
		User desiredUser = loginList.get(userName.getText().toString());
		System.out.println(desiredUser);
		if(desiredUser==null){
			//display messageDialogue
			failedLogIn.show();
			return;
		}
		if(desiredUser.checkPass(passWord.getText().toString())){
			Intent accountScreen = new Intent(this, AccountActivity.class);
			startActivity(accountScreen);
			return;
		}
		else{
			failedLogIn.show();
		}
		
	}
		
}
