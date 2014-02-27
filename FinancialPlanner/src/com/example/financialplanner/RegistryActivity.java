package com.example.financialplanner;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * The Activity handling User registration.
 *
 * @author Cory
 */
public class RegistryActivity extends Activity {
	private AlertDialog.Builder failedRegister;
	private AlertDialog.Builder successfulRegister;
	private AlertDialog.Builder registerNewUser;
	private Register register;
	private Gson gson;
	private String json;
	private String jsonString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registry);
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
		failedRegister = new AlertDialog.Builder(this)
			.setTitle("Invalid registration attempt")
			.setMessage("Username entered is already taken")
			.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) { 
					// continue with delete
					
				}
		     });
		successfulRegister = new AlertDialog.Builder(this)
		.setTitle("Valid registration attempt")
		.setMessage("A new user was successfully created!")
		.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				// continue with delete
				
			}
	     });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registry, menu);
		return true;

	}
	
	public void addNewUser(View v) {
		System.out.println("derp 1");
		EditText userName = (EditText) findViewById(R.id.newuseruser);
		EditText passWord = (EditText) findViewById(R.id.newuserpassword);
		String username = userName.getText().toString();
		String password = passWord.getText().toString();
		if (!(password.equals("") || username.equals(""))) {
			if (register.addUser(username, password)){
				successfulRegister.show();
			}
			else{
				failedRegister.show();
			}
		}
	}
	
	public void onBackPressed(){
	    finish();
	    Intent intent = new Intent(this, MainActivity.class);
	    jsonString = gson.toJson(register);
	    intent.putExtra("register", jsonString);
	    System.out.println("back button pressed");
	    startActivity(intent);
	}

}
