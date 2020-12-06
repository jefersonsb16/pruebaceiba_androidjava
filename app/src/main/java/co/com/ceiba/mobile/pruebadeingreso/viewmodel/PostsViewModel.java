package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.PostsRepository;
import co.com.ceiba.mobile.pruebadeingreso.model.Preferences;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostsAdapter;

public class PostsViewModel extends ViewModel {

    private final PostsRepository postsRepository;
    private PostsAdapter postsAdapter;
    private Preferences preferences;
    private User user;

    public PostsViewModel() {
        postsRepository = PostsRepository.getInstance();
    }

    public MutableLiveData<List<Post>> getAllPosts() {
        return postsRepository.getAllPosts(preferences, getUser().getId());
    }

    public void setPostsInRecyclerAdapter(List<Post> posts) {
        new android.os.Handler().postDelayed(
                () -> postsAdapter.updateData(posts),
                300);
    }

    public PostsAdapter getPostsAdapter() {
        postsAdapter = new PostsAdapter(this);
        return postsAdapter;
    }

    public void setUser(User user1) {
        user = user1;
    }

    public User getUser() {
        return user;
    }

    public void setPreferences(Preferences preferences1) {
        preferences = preferences1;
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
