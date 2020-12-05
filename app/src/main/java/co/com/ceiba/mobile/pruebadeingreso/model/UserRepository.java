package co.com.ceiba.mobile.pruebadeingreso.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.network.RetrofitClientInstance;
import co.com.ceiba.mobile.pruebadeingreso.network.UsersServiceInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private static UsersServiceInterface userServiceInterface;
    private final MutableLiveData<List<User>> listUsers = new MutableLiveData<>();

    private static UserRepository userRepository;

    public static UserRepository getInstance() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public UserRepository() {
        userServiceInterface = RetrofitClientInstance.getRetrofitInstance().create(UsersServiceInterface.class);
    }

    public MutableLiveData<List<User>> getAllUsers() {
        Call<List<User>> callUsers = userServiceInterface.getAllUsers();

        callUsers.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                listUsers.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                listUsers.postValue(null);
            }
        });
        return listUsers;
    }
}
