package co.com.ceiba.mobile.pruebadeingreso.model;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.network.RetrofitClientInstance;
import co.com.ceiba.mobile.pruebadeingreso.network.UsersServiceInterface;
import io.realm.Realm;
import io.realm.RealmResults;
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

    public MutableLiveData<List<User>> getAllUsers(Preferences preferences) {
        if (!preferences.getUsersGetLocal()) {
            Call<List<User>> callUsers = userServiceInterface.getAllUsers();

            callUsers.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                    listUsers.setValue(response.body());
                    saveUsersRealm(response.body());
                    preferences.setUsersGetLocal(true);
                }

                @Override
                public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                    listUsers.postValue(null);
                }
            });
        } else {
            listUsers.setValue(getUsersLocal());
        }
        return listUsers;
    }

    public List<User> getUsersLocal() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<User> results = realm.where(User.class).findAll();
        return realm.copyFromRealm(results);
    }

    public void saveUsersRealm(List<User> list) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(realm1 -> {
            for (User user : list) {
                realm1.copyToRealmOrUpdate(user);
            }
        });
        realm.close();
    }
}
