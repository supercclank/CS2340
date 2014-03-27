package com.example.financialplanner;

import com.google.gson.Gson;
import android.app.Application;

public class StartUpClass extends Application {
	private Gson gson;
	private String json;
	private String jsonString;
    public void onCreate() {
        super.onCreate();
        // Your methods here...
        //DatabaseInterface di = new DatabaseInterface(this);
        RegisterDataSource ds = new RegisterDataSource(this);
        json = ds.getRegister("register");
        if (json != null) {
        	//MainActivity.register = new Register();
        	gson = new Gson();
        	System.out.println(json);
        	MainActivity.register = gson.fromJson(json, Register.class);
        }
        System.out.println("huurr it worked??");
    }
}
