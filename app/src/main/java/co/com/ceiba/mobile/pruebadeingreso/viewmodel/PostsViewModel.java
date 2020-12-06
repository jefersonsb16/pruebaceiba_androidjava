package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import co.com.ceiba.mobile.pruebadeingreso.model.PostsRepository;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.PostsAdapter;

public class PostsViewModel extends ViewModel {

    private final PostsRepository postsRepository;
    private PostsAdapter postsAdapter;
    private User user;

    public PostsViewModel() {
        postsRepository = PostsRepository.getInstance();
    }

    public MutableLiveData<List<Post>> getAllPosts() {
        return postsRepository.getAllPosts();
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
}
