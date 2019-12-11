package dev.lukeb.a4_android_restclient.view;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.Comment;
import dev.lukeb.a4_android_restclient.model.User;
import dev.lukeb.a4_android_restclient.presenter.PostPresenter;

public class PostActivity extends AppCompatActivity {
    private static final String TAG = "PostActivity";

    public static final String USER_INTENT_TAG="User";

    PostPresenter presenter;

    TextView titleTextView;
    TextView bodyTextView;
    TextView userTextView;
    Button addCommentButton;

    User user;
    RecyclerView commentsRecyclerView;
    CommentAdapter adapter;
    List<Comment> comments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        this.presenter = new PostPresenter(this);

        this.titleTextView = findViewById(R.id.postTitle);
        this.bodyTextView = findViewById(R.id.postBody);
        this.userTextView = findViewById(R.id.postUser);
        this.userTextView.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserActivity.class);
            intent.putExtra(USER_INTENT_TAG, this.user);

            startActivity(intent);
        });

        this.addCommentButton = findViewById(R.id.addCommentButton);
        this.addCommentButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, CreateCommentActivity.class);

            startActivityForResult(intent, 0);
        });

        this.commentsRecyclerView = findViewById(R.id.commentsRecyclerView);
        this.commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.commentsRecyclerView.setHasFixedSize(true);

        getDataFromIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Comment resultFromActivity = data.getParcelableExtra(CreateCommentActivity.COMMENT_INTENT_TAG);

        this.presenter.postComment(resultFromActivity);
        this.comments.add(0, resultFromActivity);
        this.adapter.notifyDataSetChanged();
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        BlogPost post = intent.getParcelableExtra(HomeActivity.BLOG_POST_INTENT_TAG);

        // Updates the UI
        this.titleTextView.setText(post.getTitle());
        this.bodyTextView.setText(post.getBody());

        // Get data from presenter
        presenter.populateUserInfo(post.getUserId());
        presenter.populateComments(post.getId());
    }

    public void updateUserInfo(User user){
        this.user = user;
        this.userTextView.setText(user.getName());
    }

    public void updateCommentsInfo(List<Comment> comments){
        this.comments = comments;
        this.adapter = new CommentAdapter(this, this.comments);

        Log.d(TAG, "updateCommentsInfo: setting adapter with comments: " + this.comments.toString());

        this.commentsRecyclerView.setAdapter(adapter);

        this.adapter.notifyDataSetChanged();
    }

}
