package co.com.ceiba.mobile.pruebadeingreso.view.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.Preferences;
import co.com.ceiba.mobile.pruebadeingreso.model.User;

public class PostActivity extends AppCompatActivity {

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        user = (User) getIntent().getSerializableExtra(Preferences.TAG_USER);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
