package co.com.ceiba.mobile.pruebadeingreso.model;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static String TAG_USER = "usuario";
    public static String NAME_DB = "prueba_ceibadb";

    public static String USERS_GET_LOCAL = "users_get_local";
    public static String POSTS_GET_LOCAL = "posts_get_local";

    public Preferences(final Context context, final String tag) {
        sharedPreferences = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    public void setUsersGetLocal(Boolean isLocal) {
        editor.putBoolean(USERS_GET_LOCAL, isLocal).commit();
    }

    public boolean getUsersGetLocal() {
        return sharedPreferences.getBoolean(USERS_GET_LOCAL, false);
    }

    public void setPostsGetLocal(Boolean isLocal) {
        editor.putBoolean(POSTS_GET_LOCAL, isLocal).commit();
    }

    public boolean getPostsGetLocal() {
        return sharedPreferences.getBoolean(POSTS_GET_LOCAL, false);
    }
}
