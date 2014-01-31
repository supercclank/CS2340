package com.example.financialplanner;

import java.io.Serializable;
import java.util.HashMap;

import com.google.gson.Gson;

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
 * @author Cory
 */
public class LoginActivity extends Activity {
	private AlertDialog.Builder failedLogIn;
	private AlertDialog.Builder registerNewUser;
	private Register register;
	private Gson gson;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		gson = new Gson();
		register = new Register();
		failedLogIn = new AlertDialog.Builder(this)
			.setTitle("Invalid login attempt")
			.setMessage("An unknown user and password combination was entered")
			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) { 
					// continue with delete
					
				}
		     });

		register.addUser("admin", "pass123");
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
		String jsonString = gson.toJson(register);
		System.out.println(userName.getText().toString());
		if(register.checkInformation(username, password)) {
			Intent accountScreen = new Intent(this, AccountActivity.class);
			accountScreen.putExtra("register", jsonString);
			startActivity(accountScreen);

		} else {
			failedLogIn.show();

		}

	}

		
}
