package com.example.postarticle.Retrofit;


import com.example.postarticle.Model.CommentsModel;
import com.example.postarticle.Model.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @GET("/posts") Call<List<PostModel>> getListPosts();

    @GET("posts/{postid}/comments")
    Call<CommentsModel> getListComments(@Path("postid") String postid);
}
