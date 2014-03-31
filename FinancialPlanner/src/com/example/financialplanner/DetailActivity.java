package com.example.financialplanner;

import java.util.Date;
import java.util.Calendar;
import java.util.Locale;

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
/**
 * Class for viewing transactions belonging to an account.
 * @author Cory
 *
 */
//CHECKSTYLE.OFF: Class Fan-Out Complexity - use of many dependent classes needed android
public class DetailActivity extends Activity {
//CHECKSTYLE.ON: Class Fan-Out Complexity
    /**
     * The json representation of register used for loading.
     */
    private String json;
    /**
     * The register used for record keeping.
     */
    private Register register;
    /**
     * The gson object used for serialization.
     */
    private Gson gson;
    /**
     * The json representation of register used for export.
     */
    private String jsonString;
    /**
     * The account that this activituy is showing the details of.
     */
    private Account account;
    /**
     * The AlertDialog.Builder used for notifications while adding transactions.
     */
    private AlertDialog.Builder addTransaction;
    /**
     * The EditText where user enters the transactionName.
     */
    private EditText transactionName;
    /**
     * The EditText where user enters the transactionValue.
     */
    private EditText transactionValue;
    /**
     * The DatePicker where user selects the date added.
     */
    private DatePicker addedDatePicker;
    /**
     * The DatePicker where user selects the date processed.
     */
    private DatePicker processedDatePicker;
    /**
     * The Spinner where user selects the transaction type.
     */
    private Spinner transactionType;
    /**
     * The LineraLayout used in the scrollView.
     */
    private LinearLayout layout;
    /**
     * The scroll view where transactions are displayed.
     */
    private ScrollView scrollPane;
    /**
     * The TextView that asks for the dateAdded.
     */
    private TextView dateAddedPrompt;
    /**
     * The TextView that asks for the dateProcessed.
     */
    private TextView dateProcessedPrompt;
    /**
     * The TextView that asks for the transaction type.
     */
    private TextView transactionTypePrompt;
    /**
     * The AlertDialog.Builder used for notifications when a transaction error occurs.
     */
    private AlertDialog.Builder transactionError;
    /**
     * 
     */
    private LinearLayout transactionPane;
    //CHECKSTYLE.OFF: NCSS - android requires use of over 50 lines in method
    @Override
    //CHECKSTYLE.ON: NCSS
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        transactionPane = (LinearLayout) findViewById(R.id.transactionList);
        gson = new Gson();
        //CHECKSTYLE.OFF: String literal - String literal "register" needed
        json = getIntent().getStringExtra("register");
        //CHECKSTYLE.ON: String literal
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
            b.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                }
                
            });
        }
        addTransaction.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Calendar c = Calendar.getInstance();
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
                if (!valueString.equals("") && !transactionDisplayName.equals("")) {
                    double value = Double.parseDouble(valueString);
                    account.performTransaction(new Transaction(Transaction.TransactionType.valueOf(transactionType.getSelectedItem().toString().toUpperCase(Locale.US)), value, addedDate, processedDate, transactionDisplayName));
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
                    b.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
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
            }
        );
        int index = register.getUser().getAccounts().indexOf(account);
        account = register.getUser().getAccounts().get(index);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }
    /**
     * On click for adding a transaction.
     * @param v - the button that called this method
     */
    public void addTransaction(View v) {
        ViewGroup g = ((ViewGroup) scrollPane.getParent());
        if (g != null) {
            g.removeView(scrollPane);
        }
        
        addTransaction.setView(scrollPane);
        addTransaction.show();
    }
    @Override
    public void onBackPressed() {
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
    public void onPause() {
        super.onPause();
          //DatabaseInterface di = new DatabaseInterface(this);
        RegisterDataSource ds = new RegisterDataSource(this);
        jsonString = gson.toJson(register);
        ds.updateRegister(jsonString);
    }

}
