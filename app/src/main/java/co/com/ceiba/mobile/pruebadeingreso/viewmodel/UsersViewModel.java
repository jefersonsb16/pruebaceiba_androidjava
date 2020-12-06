package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.Preferences;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.model.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UsersAdapter;

public class UsersViewModel extends ViewModel {

    private final UserRepository userRepository;
    private UsersAdapter usersAdapter;
    private Preferences preferences;

    public UsersViewModel() {
        userRepository = UserRepository.getInstance(getPreferences());
    }

    public MutableLiveData<List<User>> getAllUsers() {
        return userRepository.getAllUsers(getPreferences());
    }

    public void setUsersInRecyclerAdapter(List<User> users) {
        new android.os.Handler().postDelayed(
                () -> usersAdapter.updateData(users),
                300);

    }

    public UsersAdapter getUsersAdapter() {
        usersAdapter = new UsersAdapter(this);
        return usersAdapter;
    }

    public void setPreferences(Preferences preferences1) {
        preferences = preferences1;
    }

    public Preferences getPreferences() {
        return preferences;
    }
}
