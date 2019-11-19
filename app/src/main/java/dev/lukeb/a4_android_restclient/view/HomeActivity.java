package dev.lukeb.a4_android_restclient.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    public final static String BLOG_POST_INTENT_TAG="BlogPost";

    private HomePresenter presenter;
    private RecyclerView recyclerView;
    public PostAdapter adapter;
    public List<BlogPost> blogPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        presenter = new HomePresenter(this);

        initializeRecyclerView();
        presenter.getBlogPosts();
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
    }

    public void updateBlogPosts(List<BlogPost> posts) {
        this.blogPosts = posts;

        // Reassigns new adapter for the new data
        this.adapter = new PostAdapter(this, blogPosts, position -> {
            Intent intent = new Intent(this, PostActivity.class);
            intent.putExtra(BLOG_POST_INTENT_TAG, blogPosts.get(position));

            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        this.adapter.notifyDataSetChanged();
    }
}
