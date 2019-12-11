package dev.lukeb.a4_android_restclient.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.User;
import dev.lukeb.a4_android_restclient.presenter.UserPresenter;

public class UserActivity extends AppCompatActivity implements OnMapReadyCallback {
    private static final String TAG = "UserActivity";

    private UserPresenter presenter;

    private User user;
    private List<BlogPost> blogPosts;
    private GoogleMap mMap;
    private LatLng location;

    private RecyclerView recyclerView;
    private PostAdapter adapter;

    private TextView userNameTextView;
    private TextView realNameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private TextView websiteTextView;

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

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.userMap);
        mapFragment.getMapAsync(this);

        initializeRecyclerView();
    }

     // Callback for when the map is ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Once the map is ready, get the data off of the intent, need to wait for map ready
        //    because we need the location of the user to move the map there
        getDataFromIntent();

        mMap.setMinZoomPreference(2);
        mMap.setMaxZoomPreference(30);
        mMap.addMarker(new MarkerOptions().position(this.location));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(this.location, 3));
    }

    private void getDataFromIntent(){
        Intent intent = getIntent();
        this.user = intent.getParcelableExtra(PostActivity.USER_INTENT_TAG);

        // Once gets the user, ask the presenter for the data
        presenter.getBlogPostsForUser(user);

        // Update the UI elements
        this.realNameTextView.setText(user.getName());
        this.userNameTextView.setText("Username:  " + user.getUsername());
        this.emailTextView.setText("Email:  "+ user.getEmail());
        this.phoneTextView.setText("Phone:  "+ user.getPhone());
        this.websiteTextView.setText("Website:  " + user.getWebsite());

        this.location = new LatLng(Double.parseDouble(user.getAddress().getGeo().getLat()),Double.parseDouble(user.getAddress().getGeo().getLng()));

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
