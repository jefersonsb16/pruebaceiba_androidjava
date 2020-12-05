package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.model.UserRepository;
import co.com.ceiba.mobile.pruebadeingreso.view.adapters.UsersAdapter;

public class UsersViewModel extends ViewModel {

    private final UserRepository userRepository;
    private UsersAdapter usersAdapter;

    public UsersViewModel() {
        userRepository = UserRepository.getInstance();
    }

    public MutableLiveData<List<User>> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void setUsersInRecyclerAdapter(List<User> users) {
        usersAdapter.updateData(users);
    }

    public UsersAdapter getUsersAdapter() {
        usersAdapter = new UsersAdapter(this);
        return usersAdapter;
    }
}
