package dev.lukeb.a4_android_restclient.view;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.api.ApiService;
import dev.lukeb.a4_android_restclient.api.RetrofitClient;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.Comment;
import dev.lukeb.a4_android_restclient.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "CommentHolder";


    private TextView commentEmail;
    private TextView commentName;
    private TextView commentBody;

    protected CommentHolder(View itemView) {
        super(itemView);


        this.commentEmail = itemView.findViewById(R.id.commentEmail);
        this.commentName = itemView.findViewById(R.id.commentName);
        this.commentBody = itemView.findViewById(R.id.commentBody);
    }

    // Method called by Adapter to set the UI
    protected void setItemDetails(Comment comment) {
        this.commentEmail.setText(comment.getEmail());
        this.commentName.setText(comment.getName());
        this.commentBody.setText(comment.getBody());
    }


}
