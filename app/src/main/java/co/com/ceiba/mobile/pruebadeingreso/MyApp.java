package co.com.ceiba.mobile.pruebadeingreso;

import android.app.Application;

import co.com.ceiba.mobile.pruebadeingreso.model.Preferences;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(Preferences.NAME_DB)
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
