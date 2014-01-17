package com.example.financialplanner;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

/**
 * The entry point class for this program. Runs everything.
 *
 * @author Cory
 */
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent logInScreenIntent = new Intent(this, LoginActivity.class);

		startActivity(logInScreenIntent);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

}
