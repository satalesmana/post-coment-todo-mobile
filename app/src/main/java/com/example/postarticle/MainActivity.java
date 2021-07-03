package com.example.postarticle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.postarticle.Adapter.PostListAdapter;
import com.example.postarticle.Db.DbManager;
import com.example.postarticle.Model.PostModel;
import com.example.postarticle.Retrofit.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostListAdapter postListAdapter;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rcv_post_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getPostList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_action_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_action_add:
                Intent intent = new Intent(this, FormActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_action_profile:
                Toast.makeText(this, "ini menu edit", Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_logout:
                logOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /*
    * function yang di custome sendiri
    *
    * */

    private void logOut(){
        dbManager = new DbManager(this);
        dbManager.open();
        dbManager.deleteUser();
        dbManager.close();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void getPostList(){
        ApiService.endpoint().getListPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                List<PostModel> postModels = response.body();

                postListAdapter = new PostListAdapter(MainActivity.this, postModels);
                recyclerView.setAdapter(postListAdapter);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                Log.d("ERR getPostList", t.toString());
                Toast.makeText(getBaseContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}