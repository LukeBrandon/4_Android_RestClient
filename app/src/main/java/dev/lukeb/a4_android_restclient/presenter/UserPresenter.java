package dev.lukeb.a4_android_restclient.presenter;

import android.util.Log;

import java.util.List;

import dev.lukeb.a4_android_restclient.api.ApiService;
import dev.lukeb.a4_android_restclient.api.RetrofitClient;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.User;
import dev.lukeb.a4_android_restclient.view.UserActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {

    UserActivity view;
    ApiService apiService;

    public UserPresenter(UserActivity activity){
        this.view = activity;
        this.apiService = RetrofitClient.getApiService();
    }

    public void getBlogPostsForUser(User user){
        apiService.getPostsForUser(user.getId()).enqueue(new Callback<List<BlogPost>>() {
            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {
                List<BlogPost> blogPosts = response.body();

                view.runOnUiThread(() -> view.updateUserPosts(blogPosts));
            }

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
