package co.com.ceiba.mobile.pruebadeingreso.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.R;
import co.com.ceiba.mobile.pruebadeingreso.model.Preferences;
import co.com.ceiba.mobile.pruebadeingreso.model.User;
import co.com.ceiba.mobile.pruebadeingreso.view.ui.PostActivity;
import co.com.ceiba.mobile.pruebadeingreso.viewmodel.UsersViewModel;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    List<User> listUsers = new ArrayList<>();
    Context context;
    private final UsersViewModel usersViewModel;

    public UsersAdapter(UsersViewModel usersViewModel) {
        this.usersViewModel = usersViewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = listUsers.get(position);

        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());

        holder.btnViewPosts.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostActivity.class);
            intent.putExtra(Preferences.TAG_USER, user);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public void updateData(List<User> users) {
        listUsers.clear();
        listUsers.addAll(users);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView phone;
        private TextView email;
        private Button btnViewPosts;

        public ViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
            email = itemView.findViewById(R.id.email);
            btnViewPosts = itemView.findViewById(R.id.btn_view_post);
        }
    }

}
