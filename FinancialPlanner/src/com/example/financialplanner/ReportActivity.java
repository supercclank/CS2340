package com.example.financialplanner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

public class ReportActivity extends Activity {
	private String json;
	private Register register;
	private Gson gson;
	private String jsonString;
	private LinearLayout reportPane;
	private TextView listedReport;
	private AlertDialog.Builder generateReportBuilder;
	private AlertDialog.Builder generateError;
	private TextView dateProcessedPrompt1;
	private DatePicker processedDatePicker1;
	private TextView dateProcessedPrompt2;
	private DatePicker processedDatePicker2;
	private LinearLayout layout;
	private ScrollView scrollPane;
	private Spinner transactionType;
	private TextView transactionTypePrompt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		gson = new Gson();
		json = getIntent().getStringExtra("register");
		register = gson.fromJson(json, Register.class);
		json = getIntent().getStringExtra("account");
		reportPane = (LinearLayout) findViewById(R.id.reportList);
		listedReport = new TextView(this);
		//List<String> list = new ReportFormatter(register).getWithdrawList();
		reportPane.addView(listedReport);
		generateReportBuilder = new AlertDialog.Builder(this);
		generateError = new AlertDialog.Builder(this);
		generateReportBuilder.setTitle("Generate Report");
		dateProcessedPrompt1 = new TextView(this);
		dateProcessedPrompt1.setText("Date Processed Lower Bound: ");
		processedDatePicker1 = new DatePicker(this);
		dateProcessedPrompt2 = new TextView(this);
		dateProcessedPrompt2.setText("Date Processed Upper Bound: ");
		processedDatePicker2 = new DatePicker(this);
		transactionType = new Spinner(this);
		transactionTypePrompt = new TextView(this);
		transactionTypePrompt.setText("Transaction Type");
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.generatereport_array, android.R.layout.simple_spinner_item);
		transactionType.setAdapter(adapter);
		layout = new LinearLayout(this);
	    layout.setOrientation(LinearLayout.VERTICAL);
	    layout.addView(dateProcessedPrompt1);
	    layout.addView(processedDatePicker1);
	    layout.addView(dateProcessedPrompt2);
	    layout.addView(processedDatePicker2);
	    layout.addView(transactionTypePrompt);
	    layout.addView(transactionType);
		scrollPane = new ScrollView(this);
	    scrollPane.addView(layout);
	    generateReportBuilder.setView(scrollPane);
	    generateReportBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	        	Calendar c = new GregorianCalendar().getInstance();
	        	int month1 = processedDatePicker1.getMonth();
	        	int date1 = processedDatePicker1.getDayOfMonth();
	        	int year1 = processedDatePicker1.getYear();
	        	int month2 = processedDatePicker2.getMonth();
	        	int date2 = processedDatePicker2.getDayOfMonth();
	        	int year2 = processedDatePicker2.getYear();
	        	c.set(year1, month1, date1);
	        	Date leftBound = c.getTime();
	        	c.set(year2, month2, date2);
	        	Date rightBound = c.getTime();
	        	if (leftBound.compareTo(rightBound)<=0) {
	        		List<String> list = new ArrayList<String>();
	        		System.out.println("aldsfhashdfasufioausdiofuiaosdufioa "+transactionType.getSelectedItem().toString());
	        		if (transactionType.getSelectedItem().toString().equals("Withdrawls")) {
	        			System.out.println("withdraw here");
	        			list = new ReportFormatter(register).getWithdrawList(leftBound,rightBound);
	        		} else if (transactionType.getSelectedItem().toString().equals("Deposits")) {
	        			System.out.println("deposits here");
	        			list = new ReportFormatter(register).getDepositList(leftBound,rightBound);
	        		} else {
	        			System.out.println("transactions here");
	        			list = new ReportFormatter(register).getTransactionList(leftBound,rightBound);
	        		}
	        		String textString ="";
	        		for (int x = 0; x <list.size(); x++) {
	        		textString+= list.get(x);
	        		}
	        		listedReport.setText(textString);
	        	}
	        	else {
	        		generateError.show();
	        	}
	        }
	    });
	    generateReportBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
	        }
	    });
	    generateError.setMessage("Invalid date range");
	    generateError.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) { 
				ViewGroup g = ((ViewGroup) scrollPane.getParent());
				g.removeView(scrollPane);
				generateReportBuilder.show();
			}
	     });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.report, menu);
		return true;
	}
	
	public void generateReport(View v) {
		ViewGroup g = ((ViewGroup) scrollPane.getParent());
		if (g != null) {
			g.removeView(scrollPane);
		}
		generateReportBuilder.setView(scrollPane);
		generateReportBuilder.show();
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

}
