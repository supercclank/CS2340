package com.example.financialplanner;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
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
	protected LinearLayout accountPane;
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
		LinearLayout layout = new LinearLayout(this);
	    layout.setOrientation(LinearLayout.VERTICAL);
	    layout.addView(accountDisplay);
	    layout.addView(accountName);
	    createAccount.setView(layout);
	    createAccount.setNegativeButton("OK", new DialogInterface.OnClickListener() { 
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            String displayName = accountDisplay.getText().toString();
	            String accountName = accountDisplay.getText().toString();
	            Account account = new Account(accountName, accountName, 0, 0);
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
	
	public void addAccount(View v){
		createAccount.show();
		//user.addAccount(account);
	}
}
