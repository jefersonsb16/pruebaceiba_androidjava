package co.com.ceiba.mobile.pruebadeingreso.view.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding;
import co.com.ceiba.mobile.pruebadeingreso.model.Preferences;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.UsersViewModel;

public class MainActivity extends AppCompatActivity {

    private UsersViewModel usersViewModel;

    // Progress
    ProgressDialog progressDialog;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // show progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.generic_message_progress));

        preferences = new Preferences(this, Preferences.USERS_GET_LOCAL);
        if (!preferences.getUsersGetLocal()) {
            progressDialog.show();
        }

        setupBindings(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        usersViewModel = ViewModelProviders.of(this).get(UsersViewModel.class);

        activityMainBinding.setViewmodel(usersViewModel);
        setupListUpdate();
    }

    private void setupListUpdate() {
        usersViewModel.setPreferences(preferences);

        usersViewModel.getAllUsers().observe(this, users -> {
            usersViewModel.setUsersInRecyclerAdapter(users);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        });
    }
}