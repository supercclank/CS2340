package com.example.financialplanner;

import java.util.Collection;
import com.google.gson.Gson;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * The entry point class for this program. Runs everything.
 *
 * @author Cory
 */
public class MainActivity extends Activity {
    /**
     * Register object used for record keeping.
     */
    protected static Register register;
    /**
     * Gson object used for serialization.
     */
    private Gson gson;
    /**
     * json representation of register used for loading.
     */
    private String json;
    /**
     * json representation of register used for export.
     */
    private String jsonString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        gson = new Gson();
        //CHECKSTYLE.OFF: String literal - String literal use of register needed
        json = getIntent().getStringExtra("register");
        //CHECKSTYLE.ON: String literal
        System.out.println("2" + json);
        if (json != null) {
            register = gson.fromJson(json, Register.class);
        }
//        if (register == null) {
//            register = new Register();
//            
//            System.out.println(register.addUser("admin", "pass123"));
//        }
        Collection<User> h = register.getUsers().values();
        for (User u : h) {
            System.out.println("qqq " + u.getUserName());
        }
        jsonString = gson.toJson(register);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;

    }
    
    /**
     * Registers a new User.
     *
     * @param    v    - the View that was interacted with to start this method
     */
    public void continueToLogIn(View v) {
        
        Intent logIn = new Intent(this, LoginActivity.class);
        logIn.putExtra("register", jsonString);
        startActivity(logIn);
    }

    /**
     * Registers a new User.
     *
     * @param    v    - the View that was interacted with to start this method
     */
    public void registerNewUser(View v) {
        Intent registerNew = new Intent(this, RegistryActivity.class);
        registerNew.putExtra("register", jsonString);
        startActivity(registerNew);
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
