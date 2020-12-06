package co.com.ceiba.mobile.pruebadeingreso.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;

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
        userRepository = UserRepository.getInstance();
    }

    public MutableLiveData<List<User>> getAllUsers() {
        return userRepository.getAllUsers(getPreferences());
    }

    public void setUsersInRecyclerAdapter(List<User> users) {
        new android.os.Handler().postDelayed(
                () -> usersAdapter.updateData(users),
                500);

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

    public TextWatcher searchTextWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do nothing.
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                usersAdapter.getFilter().filter(editable.toString());
            }
        };
    }

    public Boolean isListEmpty() {
        final Boolean[] isEmpty = {false};

        new android.os.Handler().postDelayed(
                () -> {
                    isEmpty[0] = usersAdapter.getItemCount() == 0 ? true : false;
                },
                300);

        return isEmpty[0];
    }
}
