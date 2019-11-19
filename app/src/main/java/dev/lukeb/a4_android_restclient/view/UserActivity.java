package dev.lukeb.a4_android_restclient.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.api.ApiService;
import dev.lukeb.a4_android_restclient.api.RetrofitClient;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.User;
import dev.lukeb.a4_android_restclient.presenter.UserPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private static final String TAG = "UserActivity";

    UserPresenter presenter;

    User user;

    TextView userNameTextView;
    TextView realNameTextView;
    TextView emailTextView;
    TextView phoneTextView;
    TextView websiteTextView;


    PostAdapter adapter;
    RecyclerView recyclerView;
    List<BlogPost> blogPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        this.presenter = new UserPresenter(this);

        this.userNameTextView = findViewById(R.id.userName);
        this.realNameTextView = findViewById(R.id.realName);
        this.emailTextView = findViewById(R.id.email);
        this.phoneTextView = findViewById(R.id.phoneNumber);
        this.websiteTextView = findViewById(R.id.website);

        initializeRecyclerView();

        getDataFromIntent();
        presenter.getBlogPostsForUser(user);

    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        this.user = intent.getParcelableExtra(PostActivity.USER_INTENT_TAG);

        Log.d(TAG, "getDataFromIntent: Got user inside of UserActivity as: " + this.user);

        this.realNameTextView.setText(user.getName());
        this.userNameTextView.setText("Username:  " + user.getUsername());
        this.emailTextView.setText("Email:  "+ user.getEmail());
        this.phoneTextView.setText("Phone:  "+ user.getPhone());
        this.websiteTextView.setText("Website:  " + user.getWebsite());
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.postsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    public void updateUserPosts(List<BlogPost> posts) {
        this.blogPosts = posts;

        // Reassigns new adapter for the new data
        this.adapter = new PostAdapter(this, blogPosts, position -> {
            Intent intent = new Intent(this, PostActivity.class);
            intent.putExtra(HomeActivity.BLOG_POST_INTENT_TAG, blogPosts.get(position));

            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        this.adapter.notifyDataSetChanged();
    }
}
