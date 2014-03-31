package com.example.financialplanner;

import com.google.gson.Gson;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

/**
 * The Activity handling User registration.
 *
 * @author Cory
 */
public class RegistryActivity extends Activity {
    /**
     * The  AlertDialog.Builder used for notification of failed registration.
     */
    private AlertDialog.Builder failedRegister;
    /**
     * The  AlertDialog.Builder used for notification of successful registration.
     */
    private AlertDialog.Builder successfulRegister;
    /**
     * The register used for record keeping.
     */
    private Register register;
    /**
     * The gson object used for serialization.
     */
    private Gson gson;
    /**
     * The json representation of register used for loading.
     */
    private String json;
    /**
     * The json representation of register used for export.
     */
    private String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        gson = new Gson();
        //CHECKSTYLE.OFF: String literal - use of String literal "register" needed
        json = getIntent().getStringExtra("register");
        //CHECKSTYLE.ON: String literal
        register = gson.fromJson(json, Register.class);
        failedRegister = new AlertDialog.Builder(this)
            .setTitle("Invalid registration attempt")
            .setMessage("Username entered is already taken")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { 
                    // continue with delete
                    
                }
            }
            );
        successfulRegister = new AlertDialog.Builder(this)
            .setTitle("Valid registration attempt")
            .setMessage("A new user was successfully created!")
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) { 
                // continue with delete
                
                }
            }
            );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registry, menu);
        return true;

    }
    /**
     * On click method used to add new user.
     * @param v - the button that called this method
     */
    public void addNewUser(View v) {
        System.out.println("derp 1");
        EditText userName = (EditText) findViewById(R.id.newuseruser);
        EditText passWord = (EditText) findViewById(R.id.newuserpassword);
        String username = userName.getText().toString();
        String password = passWord.getText().toString();
        if (!(password.equals("") || username.equals(""))) {
            if (register.addUser(username, password)) {
                successfulRegister.show();
            }
            else {
                failedRegister.show();
            }
        }
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        jsonString = gson.toJson(register);
        intent.putExtra("register", jsonString);
        System.out.println("back button pressed");
        startActivity(intent);
    }
    
    @Override
    public void onPause() {
        super.onPause();
        System.out.println("pause worked?");
         //DatabaseInterface di = new DatabaseInterface(this);
        RegisterDataSource ds = new RegisterDataSource(this);
        jsonString = gson.toJson(register);
        ds.updateRegister(jsonString);
    }

}
