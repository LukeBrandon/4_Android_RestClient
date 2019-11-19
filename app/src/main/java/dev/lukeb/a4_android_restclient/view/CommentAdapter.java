package dev.lukeb.a4_android_restclient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.model.Comment;

public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {

    private Context context;
    private List<Comment> comments;

    public CommentAdapter(
            Context context,
            List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder commentHolder, int position) {
        Comment comment = comments.get(position);
        commentHolder.setItemDetails(comment);
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.comment_label, parent, false);

        return new CommentHolder(view);
    }

}
