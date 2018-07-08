package mlg.warkop.com.mypsychologist.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "MyPsychologist";


    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";

    public AppPreferences(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /* location */
    public void setSession(String id, String username) {
        editor.putString(USER_ID, id);
        editor.putString(USER_NAME, username);
        editor.apply();
    }

    public void clearSession() {
        editor.putString(USER_ID, "");
        editor.putString(USER_NAME, "");
        editor.apply();
    }

    public String getUserName(){
        return pref.getString(USER_NAME, "");
    }
}
