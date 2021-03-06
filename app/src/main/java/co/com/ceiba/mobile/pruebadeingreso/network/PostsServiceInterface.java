package co.com.ceiba.mobile.pruebadeingreso.network;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsServiceInterface {
    @GET(Endpoints.GET_POSTS)
    Call<List<Post>> getAllPosts();
}
