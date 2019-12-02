package dev.lukeb.a4_android_restclient.view;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import dev.lukeb.a4_android_restclient.R;
import dev.lukeb.a4_android_restclient.api.ApiService;
import dev.lukeb.a4_android_restclient.api.RetrofitClient;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostHolder extends RecyclerView.ViewHolder {
    private static final String TAG = "ItemHolder";

    private ApiService apiService;

    private TextView postTitle;
    private TextView postUser;
    private TextView postId;

    protected PostHolder(View itemView, PostAdapter.OnItemListener onItemListener) {
        super(itemView);

        this.apiService = RetrofitClient.getApiService();

        this.postTitle = itemView.findViewById(R.id.postTitle);
        this.postUser = itemView.findViewById(R.id.postUser);
        this.postId = itemView.findViewById(R.id.postId);

        itemView.setOnClickListener(v -> onItemListener.onItemClick(getAdapterPosition()));
    }

    // Method called by Adapter to set the UI
    protected void setItemDetails(BlogPost blogPost) {
        this.postTitle.setText(blogPost.getTitle());
        getUserData(blogPost.getUserId());
        this.postId.setText(Integer.toString(blogPost.getId()));
    }

    // Updates the UI to show the username
    private void updateUserData(String name){
        this.postUser.setText(name);
    }

    // Makes get request for user name
    private void getUserData(int userId){
        apiService.getUser(userId).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User userResponse = response.body();
                updateUserData(userResponse.getUsername());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: getUser(id) FAILED");
                t.printStackTrace();
            }
        });
    }
}
