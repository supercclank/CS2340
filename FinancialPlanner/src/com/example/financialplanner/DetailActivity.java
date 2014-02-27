package com.example.financialplanner;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class DetailActivity extends Activity {
	private String json;
	private Register register;
	private Gson gson;
	private String jsonString;
	private Account account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
		json = getIntent().getStringExtra("account");
		account = gson.fromJson(json, Account.class);
		TextView t =  (TextView) findViewById(R.id.detailViewTextField);
		t.setText(account.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	

	public void onBackPressed(){
	    finish();
	    Intent intent = new Intent(this, AccountActivity.class);
	    jsonString = gson.toJson(register);
	    System.out.println(jsonString);
	    intent.putExtra("register", jsonString);
	    System.out.println("back button pressed");
	    register.resetUser();
	    startActivity(intent);
	}

}
