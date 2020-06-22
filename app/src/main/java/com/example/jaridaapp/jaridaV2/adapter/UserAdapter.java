package com.example.jaridaapp.jaridaV2.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jaridaapp.R;
import com.example.jaridaapp.jaridaV2.model.ContentItem;
import com.example.jaridaapp.jaridaV2.model.User;

import java.util.List;

public class UserAdapter extends PagedListAdapter<ContentItem, UserAdapter.UserViewHolder> {
    private List<ContentItem> mCountryList;
    public UserAdapter() {
        super(USER_COMPARATOR);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jarida2, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;
        private TextView author;
        private TextView createdAt;
        private TextView updatedAt;
        //private ImageView userPic;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titles2);
            content = itemView.findViewById(R.id.content2);
            author = itemView.findViewById(R.id.author2);
            createdAt = itemView.findViewById(R.id.createdAt);
            updatedAt = itemView.findViewById(R.id.updatedAt);

        }
        void bind(ContentItem user) {
           /* if (user.getFirstName() != null && user.getLastName() != null) {
                userName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
            }*/
            if (user.getTitle() != null ) {
                title.setText(user.getTitle());
            }
            if (user.getContent() != null) {
                content.setText(user.getContent());
            }
            if (user.getAuthor() != null) {
                author.setText(user.getAuthor());
            }

            if (user.getCreatedAt() != null) {
                createdAt.setText(user.getCreatedAt());
            }
            if (user.getUpdatedAt() != null) {
                updatedAt.setText(user.getUpdatedAt());
            }

            //Glide.with(itemView.getContext()).load(user.getAvatar()).placeholder(R.drawable.ic_git_branch).into(userPic);
        }
    }

    private static final DiffUtil.ItemCallback<ContentItem> USER_COMPARATOR = new DiffUtil.ItemCallback<ContentItem>() {
        @Override public boolean areItemsTheSame(@NonNull ContentItem oldItem, @NonNull ContentItem newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @SuppressLint("DiffUtilEquals")
        @Override public boolean areContentsTheSame(@NonNull ContentItem oldItem, @NonNull ContentItem newItem) {
            return oldItem == newItem;
        }
    };
}
