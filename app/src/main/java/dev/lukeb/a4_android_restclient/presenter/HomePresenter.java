package dev.lukeb.a4_android_restclient.presenter;

import android.util.Log;

import java.util.List;

import dev.lukeb.a4_android_restclient.api.ApiService;
import dev.lukeb.a4_android_restclient.api.RetrofitClient;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.view.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {
    private static final String TAG = "HomePresenter";

    private HomeActivity view;
    private ApiService apiService;

    public HomePresenter(HomeActivity activity){
        this.view = activity;
        this.apiService = RetrofitClient.getApiService();

    }

    public void getBlogPosts(){
        apiService.getPosts().enqueue(new Callback<List<BlogPost>>() {
            @Override
            public void onResponse(Call<List<BlogPost>> call, Response<List<BlogPost>> response) {
                List<BlogPost> blogPosts = response.body();
                Log.d(TAG, "onResponse: blog posts: " + blogPosts.toString());

                view.runOnUiThread(() -> view.updateBlogPosts(blogPosts));
            }

            @Override
            public void onFailure(Call<List<BlogPost>> call, Throwable t) {
                Log.d(TAG, "onFailure: getBlogPosts FAILED");
                t.printStackTrace();
            }
        });
    }
}
