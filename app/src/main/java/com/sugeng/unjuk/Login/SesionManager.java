package com.sugeng.unjuk.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.sugeng.unjuk.Model.usermodel;

import java.util.HashMap;

public class SesionManager {

    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String ID_AKUN = "id_akun";

   public SesionManager (Context context) {
       this._context = context;
       sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
       editor = sharedPreferences.edit();
   }

    public void createLoginSession(usermodel user) {
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(ID_AKUN, user.getIdakun());
        editor.commit();
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(ID_AKUN, sharedPreferences.getString(ID_AKUN, null));
        return user;
    }

    public void logoutSession() {
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }
}
