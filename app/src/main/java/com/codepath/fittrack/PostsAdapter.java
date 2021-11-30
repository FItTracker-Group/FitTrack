package com.codepath.fittrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> allPosts) {
        this.context = context;
        posts = allPosts;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    //Clean all elements of recycler
    public void clear(){
        posts.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Post> list){
        posts.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvDate;
        DateFormat df = new SimpleDateFormat("MMM dd, yyyy 'at' hh:mm aaa");

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvDate = itemView.findViewById(R.id.tvDate);
        }

        public void bind(Post post) {
            //Bind the post data to the view element
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if(image != null){
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            Date date = post.getCreatedAt();
            String reportDate = df.format(date);
            tvDate.setText("Posted: " + reportDate);
        }
    }
}
