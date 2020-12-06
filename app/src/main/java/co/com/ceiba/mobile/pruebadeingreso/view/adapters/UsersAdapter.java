package co.com.ceiba.mobile.pruebadeingreso.view.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
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

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder> implements Filterable {

    List<User> listUsers;
    List<User> filterListUsers = new ArrayList<>();
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
        User user = filterListUsers.get(position);

        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
        holder.email.setText(user.getEmail());

        holder.btnViewPosts.setOnClickListener(view -> {
            Intent intent = new Intent(context, PostActivity.class);
            intent.putExtra(Preferences.TAG_USER, user.getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (filterListUsers == null) {
            return 0;
        }
        return filterListUsers.size();
    }

    public void updateData(List<User> users) {
        if (listUsers == null) {
            listUsers = new ArrayList<>();
            listUsers.addAll(users);
        }
        filterListUsers.clear();
        filterListUsers.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                List<User> filteredList = new ArrayList<>();

                if (charString.isEmpty()) {
                    filteredList = listUsers;
                } else {
                    for (User user : listUsers) {
                        if (user.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(user);
                        }
                    }

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                updateData((ArrayList<User>) filterResults.values);
            }
        };
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
