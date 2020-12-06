package co.com.ceiba.mobile.pruebadeingreso.view.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding;
import co.com.ceiba.mobile.pruebadeingreso.model.Preferences;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.PostsViewModel;
import io.realm.Realm;

public class PostActivity extends AppCompatActivity {

    private User user;
    private PostsViewModel postsViewModel;

    // Progress
    ProgressDialog progressDialog;
    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        user = getUser(getIntent().getIntExtra(Preferences.TAG_USER, 1));

        // show progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getResources().getString(R.string.generic_message_progress));

        preferences = new Preferences(this, Preferences.POSTS_GET_LOCAL);
        if (!preferences.getPostsGetLocal()) {
            progressDialog.show();
        }

        setupBindings(savedInstanceState);
    }

    public User getUser(int userId) {
        Realm realm = Realm.getDefaultInstance();
        return realm.where(User.class).equalTo("id", userId).findFirst();
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityPostBinding activityPostBinding = DataBindingUtil.setContentView(this, R.layout.activity_post);
        postsViewModel = ViewModelProviders.of(this).get(PostsViewModel.class);

        activityPostBinding.setModel(postsViewModel);
        setupListUpdate();
    }

    private void setupListUpdate() {
        postsViewModel.setUser(user);
        postsViewModel.setPreferences(preferences);

        postsViewModel.getAllPosts().observe(this, posts -> {
            postsViewModel.setPostsInRecyclerAdapter(posts);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}
