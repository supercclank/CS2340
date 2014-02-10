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
	private AlertDialog.Builder failedLogIn;
	private AlertDialog.Builder registerNewUser;
	private Register register;
	private Gson gson;
	private String json;
	private String jsonString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registry);
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
		failedLogIn = new AlertDialog.Builder(this)
			.setTitle("Invalid login attempt")
			.setMessage("An unknown user and password combination was entered")
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
		System.out.println(register);
		register.addUser(username, password);
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
