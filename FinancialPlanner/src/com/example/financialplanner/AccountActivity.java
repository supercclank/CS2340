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
/**
 * Activity Reponsible for Account Creation and Viewing.
 * @author Cory Brzycki
 * @version 1.0
 */
public class AccountActivity extends Activity {
    /**
     * The json representation of the register used to load.
     */
    private String json;
    /**
     * The register responsible for record keeping.
     */
    private Register register;
    /**
     * The gson object used for serialization.
     */
    private Gson gson;
    /**
     * The json representation of the register used to export.
     */
    private String jsonString;
    /**
     * AlertDialog.Builder used for notifications during account creation.
     */
    private AlertDialog.Builder createAccount;
    /**
     * The EditText field where the user enters in their accountDisplayName.
     */
    private EditText accountDisplay;
    /**
     * The EditText field where the user enters in their accountName.
     */
    private EditText accountName;
    /**
     * The EditText field where the user enters in the interest rate.
     */
    private EditText interestRate;
    /**
     * The EditText field where the user enters in the starting balance.
     */
    private EditText startingBalance;
    /**
     * The AlertDialog.Builder used for notifications while trying to register
     * and existign account.
     */
    private AlertDialog.Builder accountTaken;
    /**
     * Arraylist of buttons, each button representing an account.
     */
    private List<Button> buttons;
    /**
     * The pane that displays accounts.
     */
    private LinearLayout accountPane;
    /**
     * The layout used in the scroll view.
     */
    private LinearLayout layout;
    //CHECKSTYLE.OFF: NCSS - method needs to be longer than 50 cause android
    @Override
    //CHECKSTYLE.ON: NCSS
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        accountPane = (LinearLayout) findViewById(R.id.accountList);
        buttons = new ArrayList<Button>();
        gson = new Gson();
        //CHECKSTYLE.OFF: String literal - needed use of String literal "register"
        json = getIntent().getStringExtra("register");
        //CHECKSTYLE.ON: String literal
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
            b.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Button b = (Button) v;
                    finish();
                    Intent intent = new Intent(AccountActivity.this, DetailActivity.class);
                    jsonString = gson.toJson(register);
                    System.out.println(jsonString);
                    intent.putExtra("register", jsonString);
                    //CHECKSTYLE.OFF: String literal - needed use of account as String literal
                    intent.putExtra("account", gson.toJson(register.getUser().getAccounts().get(register.getUser().getAccounts().indexOf(new Account(b.getTag().toString(), b.getText().toString(), 0, 0)))));
                    //CHECKSTYLE.ON String literal
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
                if (!(displayName.equals("")) && !(accountNameString.equals("")) && !(balanceString.equals("")) && !(interestString.equals("")) && register.getUser().addAccount(new Account(accountNameString, displayName, Double.parseDouble(balanceString), Double.parseDouble(interestString)))) {
                    Button b = new Button(AccountActivity.this);
                    b.setText(displayName);
                    b.setTag(accountName);
                    accountPane.addView(b);
                    LayoutParams params = b.getLayoutParams();
                    params.width = LayoutParams.MATCH_PARENT;
                    b.setLayoutParams(params);
                    accountDisplay.setText("");
                    accountName.setText("");
                    startingBalance.setText("");
                    interestRate.setText("");
                    buttons.add(b);
                    b.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Button b = (Button) v;
                            finish();
                            Intent intent = new Intent(AccountActivity.this, DetailActivity.class);
                            jsonString = gson.toJson(register);
                            System.out.println(jsonString);
                            intent.putExtra("register", jsonString);
                            System.out.println("the current user is: " + register.getUser());
                            System.out.println("the account list is: " + register.getUser().getAccounts());
                            intent.putExtra("account", gson.toJson(register.getUser().getAccounts().get(register.getUser().getAccounts().indexOf(new Account(b.getTag().toString(), b.getText().toString(), 0, 0)))));
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
        getMenuInflater().inflate(R.menu.account, menu);
        return true;
    }
    /**
     * Overwrite for what happens when back button is pressed
     * returns to the previous activity with register serialized.
     */
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, LoginActivity.class);
        jsonString = gson.toJson(register);
        System.out.println(jsonString);
        intent.putExtra("register", jsonString);
        register.resetUser();
        startActivity(intent);
    }
    /**
     * Method that shows the createAccount dialogue.
     * @param v - the view that called this method
     */
    public void addAccount(View v) {
        ViewGroup g = ((ViewGroup) layout.getParent());
        if (g != null) {
            g.removeView(layout);
        }
        createAccount.setView(layout);
        createAccount.show();
    }
    /**
     * OnClick method for transition to ReportActivity.
     * @param v - the view that called this method
     */
    public void goToGenerate(View v) {
        finish();
        Intent intent = new Intent(this, ReportActivity.class);
        jsonString = gson.toJson(register);
        System.out.println(jsonString);
        intent.putExtra("register", jsonString);
        System.out.println("back button pressed");
        register.resetUser();
        startActivity(intent);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        RegisterDataSource ds = new RegisterDataSource(this);
        jsonString = gson.toJson(register);
        ds.updateRegister(jsonString);
    }
}
