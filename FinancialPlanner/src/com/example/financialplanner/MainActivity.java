package com.example.financialplanner;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
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
		startActivity(logIn);
	}

	/**
	 * Registers a new User.
	 *
	 * @param	v	- the View that was interacted with to start this method
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
