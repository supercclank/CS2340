package com.example.financialplanner;

import java.io.Serializable;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * The Activity controlling login and new User registration.
 *
 * @author Chris
 */
public class LoginActivity extends Activity {
	private AlertDialog.Builder failedLogIn;
	private AlertDialog.Builder registerNewUser;
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

	/**
	 * Attempts to sign in to the application.
	 *
	 * @param	v	- the View on which to display the login screen
	 */
	public void attemptSignIn(View v) {
		EditText userName = (EditText) findViewById(R.id.username);
		EditText passWord = (EditText) findViewById(R.id.password);
		String username = userName.getText().toString();
		String password = passWord.getText().toString();

		System.out.println(userName.getText().toString());
		if(register.checkInformation(username, password)) {
			Intent accountScreen = new Intent(this, AccountActivity.class);

			startActivity(accountScreen);

		} else {
			failedLogIn.show();

		}

	}

	/**
	 * Registers a new User.
	 *
	 * @param	v	- the View with which to display registration info
	 */
	public void registerNewUser(View v) {
		Intent registerNew = new Intent(this, RegistryActivity.class);
//		registerNew.putExtra("users", register);
		startActivity(registerNew);
//		registerNewUser = new AlertDialog.Builder(this)
//		.setTitle("Invalid login attempt")
//		.setView(findViewById(R.layout.registry));
//		.setMessage("An unknown user and password combination was entered")
//	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
//	        public void onClick(DialogInterface dialog, int which) { 
//	            // continue with delete
//	        }
//	     });
//		LayoutInflater inflater = getLayoutInflater();
//		View dialoglayout = inflater.inflate(R.layout.activity_registry, (ViewGroup) getCurrentFocus());
//		AlertDialog.Builder builder = new AlertDialog.Builder(this);
//		builder.setView(dialoglayout);
//		builder.show();
//		registerNewUser.show();
	}

//	public void addNewUser(View v) {
//		System.out.println("It worked?");
//
//	}
		
}
