package co.com.ceiba.mobile.pruebadeingreso.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.network.PostsServiceInterface;
import co.com.ceiba.mobile.pruebadeingreso.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {
    private static PostsServiceInterface postsServiceInterface;
    private final MutableLiveData<List<Post>> listPosts = new MutableLiveData<>();

    private static PostsRepository postsRepository;

    public static PostsRepository getInstance() {
        if (postsRepository == null) {
            postsRepository = new PostsRepository();
        }
        return postsRepository;
    }

    public PostsRepository() {
        postsServiceInterface = RetrofitClientInstance.getRetrofitInstance().create(PostsServiceInterface.class);
    }

    public MutableLiveData<List<Post>> getAllPosts() {
        Call<List<Post>> callPosts = postsServiceInterface.getAllPosts();

        callPosts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                listPosts.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                listPosts.postValue(null);
            }
        });
        return listPosts;
    }
}
