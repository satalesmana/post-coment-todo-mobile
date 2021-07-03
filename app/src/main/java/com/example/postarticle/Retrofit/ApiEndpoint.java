package com.example.postarticle.Retrofit;


import com.example.postarticle.Model.CommentsModel;
import com.example.postarticle.Model.LoginModel;
import com.example.postarticle.Model.PostModel;
import com.example.postarticle.Model.PostSaveModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @POST("/auth/login") Call<LoginModel> setLoginUser(
           @Body Map<String, String> body
    );

    @POST("/api/post") Call<PostSaveModel> savePostData(
            @Body Map<String, String> body
    );

    @GET("/posts") Call<List<PostModel>> getListPosts();

    @GET("posts/{postid}/comments")
    Call<CommentsModel> getListComments(@Path("postid") String postid);
}
