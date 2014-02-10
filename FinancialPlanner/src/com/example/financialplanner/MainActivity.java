package com.example.financialplanner;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * The entry point class for this program. Runs everything.
 *
 * @author Cory
 */
public class MainActivity extends Activity {
	private Register register;
	private Gson gson;
	private String json;
	private String jsonString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
		if (register==null){
		register = new Register();
		}
		jsonString = gson.toJson(register);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}
	
	/**
	 * Registers a new User.
	 *
	 * @param	v	- the View that was interacted with to start this method
	 */
	public void continueToLogIn(View v) {
		
		Intent logIn = new Intent(this, LoginActivity.class);
		logIn.putExtra("register", jsonString);
		startActivity(logIn);
	}

	/**
	 * Registers a new User.
	 *
	 * @param	v	- the View that was interacted with to start this method
	 */
	public void registerNewUser(View v) {
		Intent registerNew = new Intent(this, RegistryActivity.class);
		registerNew.putExtra("register", jsonString);
		startActivity(registerNew);
	}
}
