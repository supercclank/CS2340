package com.example.financialplanner;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class DetailActivity extends Activity {
	private String json;
	private Register register;
	private Gson gson;
	private String jsonString;
	private Account account;
	private AlertDialog.Builder addTransaction;
	private EditText transactionName;
	private EditText transactionValue;
	private DatePicker addedDatePicker;
	private DatePicker processedDatePicker;
	private Spinner transactionType;
	private LinearLayout layout;
	private ScrollView scrollPane;
	private TextView dateAddedPrompt;
	private TextView dateProcessedPrompt;
	private TextView transactionTypePrompt;
	private AlertDialog.Builder transactionError;
	private LinearLayout transactionPane;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		transactionPane = (LinearLayout) findViewById(R.id.transactionList);
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
		json = getIntent().getStringExtra("account");
		account = gson.fromJson(json, Account.class);
		TextView t =  (TextView) findViewById(R.id.detailViewTextField);
		t.setText(account.toString());
		addTransaction = new AlertDialog.Builder(this);
		transactionError = new AlertDialog.Builder(this);
		addTransaction.setTitle("Add Transaction Creation Form");
		transactionName = new EditText(this);
		transactionName.setHint("Enter Descriptive Transaction Name Here");
		transactionValue = new EditText(this);
		transactionValue.setHint("Enter the Value of the Transaction Here");
		transactionValue.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
		dateAddedPrompt = new TextView(this);
		dateAddedPrompt.setText("Date Added: ");
		addedDatePicker = new DatePicker(this);
		dateProcessedPrompt = new TextView(this);
		dateProcessedPrompt.setText("Date Processed: ");
		processedDatePicker = new DatePicker(this);
		transactionType = new Spinner(this);
		transactionTypePrompt = new TextView(this);
		transactionTypePrompt.setText("Transaction Type");
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.transactiontype_array, android.R.layout.simple_spinner_item);
		transactionType.setAdapter(adapter);
		layout = new LinearLayout(this);
	    layout.setOrientation(LinearLayout.VERTICAL);
	    layout.addView(transactionName);
	    layout.addView(transactionValue);
	    layout.addView(dateAddedPrompt);
	    layout.addView(addedDatePicker);
	    layout.addView(dateProcessedPrompt);
	    layout.addView(processedDatePicker);
	    layout.addView(transactionTypePrompt);
	    layout.addView(transactionType);
	    layout.setVerticalScrollBarEnabled(true);
	    scrollPane = new ScrollView(this);
	    scrollPane.addView(layout);
	    addTransaction.setView(scrollPane);
	    for (Transaction transact: account.getTransactions()) {
	    	Button b = new Button(DetailActivity.this);
            b.setText(transact.getDescription());
            transactionPane.addView(b);
            LayoutParams params =  b.getLayoutParams();
            params.width = LayoutParams.MATCH_PARENT;
            b.setLayoutParams(params);
            b.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
//					 Button b = (Button) v;
//					 finish();
//					 //Intent intent = new Intent(DetailActivity.this, DetailActivity.class);
//					 jsonString = gson.toJson(register);
//					 System.out.println(jsonString);
//					 intent.putExtra("register", jsonString);
//					 //b.getTag().toString(),,0,0)
//					 intent.putExtra("account", gson.toJson(register.getUser().getAccounts().get(register.getUser().getAccounts().indexOf(new Account(b.getText().toString(), b.getTag().toString(), 0, 0)))));
//					 System.out.println("back button pressed");
//					 //register.resetUser();
//					 startActivity(intent);
				}
            	
            });
           // buttons.add(b);
	    }
	    addTransaction.setNegativeButton("OK", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	Calendar c = new GregorianCalendar().getInstance();
	        	int month = addedDatePicker.getMonth();
	        	int date = addedDatePicker.getDayOfMonth();
	        	int year = addedDatePicker.getYear();
	        	c.set(year, month, date);
	        	Date addedDate = c.getTime();
	        	month = processedDatePicker.getMonth();
	        	date = processedDatePicker.getDayOfMonth();
	        	year = processedDatePicker.getYear();
	        	c.set(year, month, date);
	        	Date processedDate = c.getTime();
	        	System.out.println(transactionValue.getText().toString());
	        	String valueString = (transactionValue.getText().toString());
	        	String transactionDisplayName = transactionName.getText().toString();
	        	if (!valueString.equals("")&&!transactionDisplayName.equals("")) {
	        		double value = Double.parseDouble(valueString);
	        		account.performTransaction(new Transaction(Transaction.TransactionType.valueOf(transactionType.getSelectedItem().toString().toUpperCase()), value, addedDate, processedDate, transactionDisplayName));
	            	TextView t =  (TextView) findViewById(R.id.detailViewTextField);
	            	t.setText(account.toString());
	            	transactionValue.setText("");
	            	transactionName.setText("");
	            	Button b = new Button(DetailActivity.this);
	                b.setText(transactionDisplayName);
	                transactionPane.addView(b);
	                LayoutParams params =  b.getLayoutParams();
	                params.width = LayoutParams.MATCH_PARENT;
	                b.setLayoutParams(params);
	                b.setOnClickListener(new OnClickListener(){

	    				@Override
	    				public void onClick(View v) {
//	    					 Button b = (Button) v;
//	    					 finish();
//	    					 //Intent intent = new Intent(DetailActivity.this, DetailActivity.class);
//	    					 jsonString = gson.toJson(register);
//	    					 System.out.println(jsonString);
//	    					 intent.putExtra("register", jsonString);
//	    					 //b.getTag().toString(),,0,0)
//	    					 intent.putExtra("account", gson.toJson(register.getUser().getAccounts().get(register.getUser().getAccounts().indexOf(new Account(b.getText().toString(), b.getTag().toString(), 0, 0)))));
//	    					 System.out.println("back button pressed");
//	    					 //register.resetUser();
//	    					 startActivity(intent);
	    				}
	                	
	                });
	        	}
	        	else {
				transactionError.show();
	        	}
	        }
	    });
	    addTransaction.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
	        }
	    });
	    transactionError.setMessage("Conflicting information entered");
	    transactionError.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				ViewGroup g = ((ViewGroup) scrollPane.getParent());
				g.removeView(scrollPane);
				addTransaction.show();
			}
	     });
	   int index = register.getUser().getAccounts().indexOf(account);
	   account = register.getUser().getAccounts().get(index);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
	public void addTransaction(View v){
		ViewGroup g = ((ViewGroup) scrollPane.getParent());
		if (g != null) {
			g.removeView(scrollPane);
		}
		
		addTransaction.setView(scrollPane);
		addTransaction.show();
	}
	public void onBackPressed(){
	    finish();
	    Intent intent = new Intent(this, AccountActivity.class);
	    jsonString = gson.toJson(register);
	    System.out.println(jsonString);
	    intent.putExtra("register", jsonString);
	    System.out.println("back button pressed");
	    //register.resetUser();
	    startActivity(intent);
	}
	
	@Override
	public void onPause(){
		  super.onPause();
		  //DatabaseInterface di = new DatabaseInterface(this);
		  RegisterDataSource ds = new RegisterDataSource(this);
		  jsonString = gson.toJson(register);
		  ds.updateRegister(jsonString);
	}

}
