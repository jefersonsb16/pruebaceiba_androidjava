package co.com.ceiba.mobile.pruebadeingreso.network;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.User;
import retrofit2.Call;
import retrofit2.http.GET;

public interface UsersServiceInterface {
    @GET(Endpoints.GET_USERS)
    Call<List<User>> getAllUsers();
}
