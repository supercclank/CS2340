package com.example.financialplanner;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class AccountActivity extends Activity {
	private String json;
	private Register register;
	private Gson gson;
	private String jsonString;
	private AlertDialog.Builder createAccount;
	private EditText accountDisplay;
	private EditText accountName;
	private EditText interestRate;
	private EditText startingBalance;
	private AlertDialog.Builder accountTaken;
	private List<Button> buttons;
	private LinearLayout accountPane;
	private LinearLayout layout;
	

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
		accountTaken = new AlertDialog.Builder(this);
		accountTaken.setTitle("Conflict with existing account, please resubmit the form");
		
		accountDisplay = new EditText(this);
		accountName = new EditText(this);
		interestRate = new EditText(this);
		interestRate.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		startingBalance = new EditText(this);
		startingBalance.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		accountDisplay.setHint("Display Name");
		accountName.setHint("Detailed Account Name");
		startingBalance.setHint("Starting Balance");
		interestRate.setHint("Interest Rate");
		
		layout = new LinearLayout(this);
	    layout.setOrientation(LinearLayout.VERTICAL);
	    layout.addView(accountDisplay);
	    layout.addView(accountName);
	    layout.addView(startingBalance);
	    layout.addView(interestRate);
	    layout.setVerticalScrollBarEnabled(true);
	    createAccount.setView(layout);
	    System.out.println(register.getUser().getAccounts());
	    for (Account a: register.getUser().getAccounts()) {
	    	Button b = new Button(AccountActivity.this);
            b.setText(a.getDisplayName());
            b.setTag(a.getName());
            accountPane.addView(b);
            LayoutParams params =  b.getLayoutParams();
            params.width = LayoutParams.MATCH_PARENT;
            b.setLayoutParams(params);
            b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					 Button b = (Button) v;
					 finish();
					 Intent intent = new Intent(AccountActivity.this, DetailActivity.class);
					 jsonString = gson.toJson(register);
					 System.out.println(jsonString);
					 intent.putExtra("register", jsonString);
					 //b.getTag().toString(),,0,0)
					 intent.putExtra("account", gson.toJson(register.getUser().getAccounts().get(register.getUser().getAccounts().indexOf(new Account(b.getTag().toString(), b.getText().toString(), 0, 0)))));
					 System.out.println("back button pressed");
					 //register.resetUser();
					 startActivity(intent);
				}
            	
            });
            buttons.add(b);
	    }
	    createAccount.setNegativeButton("OK", new DialogInterface.OnClickListener() { 
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            String displayName = accountDisplay.getText().toString();
	            String accountNameString = accountName.getText().toString();
	            String balanceString = startingBalance.getText().toString();
	            String interestString = interestRate.getText().toString();
	           // Account account = ;
	            if(!(displayName.equals(""))&&!(accountNameString.equals(""))&&!(balanceString.equals(""))&&!(interestString.equals(""))&&register.getUser().addAccount(new Account(accountNameString, displayName, Double.parseDouble(balanceString), Double.parseDouble(interestString)))){
		            Button b = new Button(AccountActivity.this);
		            b.setText(displayName);
		            b.setTag(accountName);
		            accountPane.addView(b);
		            LayoutParams params = b.getLayoutParams();
		            System.out.println("params is "+params);
		            params.width = LayoutParams.MATCH_PARENT;
		            b.setLayoutParams(params);
		            accountDisplay.setText("");
		            accountName.setText("");
		            startingBalance.setText("");
		            interestRate.setText("");
		            buttons.add(b);
		            b.setOnClickListener(new OnClickListener(){

						@Override
						public void onClick(View v) {
							 Button b = (Button) v;
							 finish();
							 Intent intent = new Intent(AccountActivity.this, DetailActivity.class);
							 jsonString = gson.toJson(register);
							 System.out.println(jsonString);
							 intent.putExtra("register", jsonString);
							 //b.getTag().toString(),,0,0)
							 System.out.println("the current user is: "+register.getUser());
							 System.out.println("the account list is: "+register.getUser().getAccounts());
							 intent.putExtra("account", gson.toJson(register.getUser().getAccounts().get(register.getUser().getAccounts().indexOf(new Account(b.getTag().toString(), b.getText().toString(), 0, 0)))));
							 System.out.println("back button pressed");
							 //register.resetUser();
							 startActivity(intent);
							
						}
		            	
		            });
	            }
	            else {
	            	accountTaken.show();
	            }
	        }
	    });
	    createAccount.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
	        }
	    });
	    accountTaken.setMessage("Conflicting information entered");
	    accountTaken.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				ViewGroup g = ((ViewGroup) layout.getParent());
				g.removeView(layout);
				createAccount.show();
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
	    register.resetUser();
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
	public void goToGenerate(View v){
		 finish();
		    Intent intent = new Intent(this, ReportActivity.class);
		    jsonString = gson.toJson(register);
		    System.out.println(jsonString);
		    intent.putExtra("register", jsonString);
		    System.out.println("back button pressed");
		    register.resetUser();
		    startActivity(intent);
	}
}
