package dev.lukeb.a4_android_restclient.api;

import java.util.List;

import dev.lukeb.a4_android_restclient.model.BlogPost;
import dev.lukeb.a4_android_restclient.model.Comment;
import dev.lukeb.a4_android_restclient.model.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

        // Get all posts
        @GET("/posts")
        Call<List<BlogPost>> getPosts();

        // Get user by id
        @GET("/users/{id}")
        Call<User> getUser(@Path("id") int id);

        // Get comments by post id
        @GET("/comments")
        Call<List<Comment>> getComments(@Query("postId") int postId);

        // Get posts by user id
        @GET("/posts")
        Call<List<BlogPost>> getPostsForUser(@Query("userId") int postId);

        // Post a comment
        @POST("/comments")
        @FormUrlEncoded
        Call<BlogPost> postCommentOnBlogPost(@Field("postId") int postId,
                                   @Field("id") int id,
                                   @Field("name") String name,
                                   @Field("body") String body,
                                   @Field("email") String email);
        // Post a comment for post id




}
