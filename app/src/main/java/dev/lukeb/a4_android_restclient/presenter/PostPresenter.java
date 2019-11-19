package dev.lukeb.a4_android_restclient.presenter;

import android.util.Log;
import android.widget.Toast;

import java.util.List;

import dev.lukeb.a4_android_restclient.api.ApiService;
import dev.lukeb.a4_android_restclient.api.RetrofitClient;
import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.Comment;
import dev.lukeb.a4_android_restclient.model.User;
import dev.lukeb.a4_android_restclient.view.PostActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostPresenter {
    private static final String TAG = "PostPresenter";

    private PostActivity view;

    private ApiService apiService;


    public PostPresenter(PostActivity activity){
        this.view = activity;
        this.apiService = RetrofitClient.getApiService();

    }

    public void populateUserInfo(int id){
        apiService.getUser(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.d(TAG, "onResponse: getUser(id)" + user.getName());

                view.runOnUiThread(() -> view.updateUserInfo(user));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: getUser(id) FAILED");
                t.printStackTrace();
            }
        });

    }

    public void populateComments(int postId){
        apiService.getComments(postId).enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                List<Comment> comments = response.body();
                Log.d(TAG, "onResponse: getUser(id)" + comments.toString());

                view.runOnUiThread(() -> view.updateCommentsInfo(comments));
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                Log.d(TAG, "onFailure: getComments FAILED");
                t.printStackTrace();
            }
        });
    }

    public void postComment(Comment comment){
        apiService.postCommentOnBlogPost(comment.getPostId(), comment.getId(), comment.getName(), comment.getBody(), comment.getEmail()).enqueue(new Callback<BlogPost>() {
            @Override
            public void onResponse(Call<BlogPost> call, Response<BlogPost> response) {
                Toast.makeText(view, "Posted Comment Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BlogPost> call, Throwable t) {
                Log.d(TAG, "onFailure: postComment FAILED");
                t.printStackTrace();
            }
        });
    }
}
