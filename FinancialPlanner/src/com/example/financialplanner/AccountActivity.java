package com.example.financialplanner;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AccountActivity extends Activity {
	private String json;
	private Register register;
	private Gson gson;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;

	}

}
