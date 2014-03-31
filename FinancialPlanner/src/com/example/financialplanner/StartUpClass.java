package com.example.financialplanner;

import com.google.gson.Gson;

import android.app.Application;
/**
 * Class that is responsible for loading the database on startup.
 * @author Cory
 *
 */
public class StartUpClass extends Application {
    /**
     * The gson object used for serialization.
     */
    private Gson gson;
    /**
     * The json representation of the register.
     */
    private String json;
    @Override
    public void onCreate() {
        super.onCreate();
        // Your methods here...
        //DatabaseInterface di = new DatabaseInterface(this);
        RegisterDataSource ds = new RegisterDataSource(this);
        json = ds.getRegister("register");
        if (json != null) {
            gson = new Gson();
            System.out.println(json);
            MainActivity.register = gson.fromJson(json, Register.class);
        } else {
            MainActivity.register  = new Register();
            
            System.out.println(MainActivity.register .addUser("admin", "pass123"));
        }
        System.out.println("huurr it worked??");
    }
}
