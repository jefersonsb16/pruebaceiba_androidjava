package co.com.ceiba.mobile.pruebadeingreso.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.network.PostsServiceInterface;
import co.com.ceiba.mobile.pruebadeingreso.network.RetrofitClientInstance;
import io.realm.Realm;
import io.realm.RealmResults;
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

    public MutableLiveData<List<Post>> getAllPosts(Preferences preferences, int userId) {
        if (!preferences.getPostsGetLocal()) {
            Call<List<Post>> callPosts = postsServiceInterface.getAllPosts();

            callPosts.enqueue(new Callback<List<Post>>() {
                @Override
                public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {
                    savePostsRealm(response.body());
                    preferences.setPostsGetLocal(true);
                }

                @Override
                public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                    listPosts.postValue(null);
                }
            });
        }

        listPosts.setValue(getPostsLocal(userId));

        return listPosts;
    }

    public List<Post> getPostsLocal(int userId) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Post> results = realm.where(Post.class).equalTo("userId", userId).findAll();
        return results;
    }

    public void savePostsRealm(List<Post> list) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> {
            for (Post post : list) {
                realm1.copyToRealmOrUpdate(post);
            }
        });
        realm.close();
    }
}
