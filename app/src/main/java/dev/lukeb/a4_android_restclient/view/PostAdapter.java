package dev.lukeb.a4_android_restclient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.model.BlogPost;

public class PostAdapter extends RecyclerView.Adapter<PostHolder> {
    private static final String TAG = "PostAdapter";

    private Context context;
    private List<BlogPost> blogPosts;
    private OnItemListener onItemListener;

    public PostAdapter(
            Context context,
            List<BlogPost> blogPosts,
            OnItemListener onItemListener) {
        this.context = context;
        this.blogPosts = blogPosts;
        this.onItemListener = onItemListener;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder postHolder, int position) {
        BlogPost blogPost = blogPosts.get(position);
        postHolder.setItemDetails(blogPost);
    }

    @Override
    public int getItemCount() {
        return blogPosts.size();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.post_label, parent, false);

        return new PostHolder(view, onItemListener);
    }

    public interface OnItemListener {
        void onItemClick(int position);
    }
}
