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
	private AlertDialog.Builder failedLogIn;
	private Register register;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		register = new Register();
		failedLogIn = new AlertDialog.Builder(this)
		    .setTitle("Invalid login attempt")
		    .setMessage("An unknown user and password combination was entered")
		    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            // continue with delete
		        }
		     });
		 register.addUser("derp", "derp");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	public void attemptSignIn(View v){
		EditText userName = (EditText) findViewById(R.id.username);
		EditText passWord = (EditText) findViewById(R.id.password);
		String username = userName.getText().toString();
		String password = passWord.getText().toString();
		System.out.println(userName.getText().toString());
		if(register.checkInformation(username, password)){
			Intent accountScreen = new Intent(this, AccountActivity.class);
			startActivity(accountScreen);
			return;
		}
		else{
			failedLogIn.show();
			return;
		}
	}
		
}
