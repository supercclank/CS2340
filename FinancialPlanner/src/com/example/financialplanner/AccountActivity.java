package com.example.financialplanner;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AccountActivity extends Activity {
	private String json;
	private Register register;
	private Gson gson;
	private AlertDialog.Builder createAccount;
	private EditText accountDisplay;
	private EditText accountName;
	private View v;
	private List<Button> buttons;
	private LinearLayout accountPane;
	private LinearLayout layout;
	private String jsonString;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account);
		accountPane = (LinearLayout) findViewById(R.id.accountList);
		buttons = new ArrayList<Button>();
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
		createAccount = new AlertDialog.Builder(this);
		createAccount.setTitle("Account Creation Form");
		accountDisplay = new EditText(this);
		accountName = new EditText(this);
		accountDisplay.setHint("Display Name");
		accountName.setHint("Detailed Account Name");
		layout = new LinearLayout(this);
	    layout.setOrientation(LinearLayout.VERTICAL);
	    layout.addView(accountDisplay);
	    layout.addView(accountName);
	    createAccount.setView(layout);
	    System.out.println(register.getUser().getAccounts());
	    for (Account a: register.getUser().getAccounts()) {
	    	Button b = new Button(AccountActivity.this);
            b.setText(a.getDisplayName());
            b.setTag(a.getName());
            accountPane.addView(b);
	    }
	    createAccount.setNegativeButton("OK", new DialogInterface.OnClickListener() { 
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            String displayName = accountDisplay.getText().toString();
	            String accountNameString = accountName.getText().toString();
	            Account account = new Account(accountNameString, displayName, 0, 0);
	            //System.out.println(register.getUser());
	            //boolean bb = register.checkInformation("admin", "pass123");
	            //System.out.println(bb);
	            register.getUser().addAccount(account);
	            Button b = new Button(AccountActivity.this);
	            b.setText(displayName);
	            b.setTag(accountName);
	            accountPane.addView(b);
	        }
	    });
	    createAccount.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
	        }
	    });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.account, menu);
		return true;
	}
	
	public void onBackPressed(){
	    finish();
	    Intent intent = new Intent(this, LoginActivity.class);
	    jsonString = gson.toJson(register);
	    System.out.println(jsonString);
	    intent.putExtra("register", jsonString);
	    System.out.println("back button pressed");
	    startActivity(intent);
	}
	
	public void addAccount(View v){
		//createAccount.removeView();
		//createAccount.removeView();
		ViewGroup g = ((ViewGroup) layout.getParent());
		if (g != null) {
			g.removeView(layout);
		}
		
		createAccount.setView(layout);
		createAccount.show();
		//user.addAccount(account);
	}
}
