package com.example.postarticle;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postarticle.Model.PostSaveModel;
import com.example.postarticle.Retrofit.ApiService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormActivity extends AppCompatActivity {
    EditText etTitle, etBody;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitle = findViewById(R.id.et_title);
        etBody =  findViewById(R.id.et_body);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }


    private void  saveData(){
        String title = etTitle.getText().toString();
        String body = etBody.getText().toString();
        String id = "1";

        Map<String, String> params = new HashMap<>();
        params.put("title",title);
        params.put("body",body);
        params.put("userId",id);

        ApiService.endpoint().savePostData(params).enqueue(new Callback<PostSaveModel>() {
            @Override
            public void onResponse(Call<PostSaveModel> call, Response<PostSaveModel> response) {

                if(response.isSuccessful()){
                    onHadleSuccess(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<PostSaveModel> call, Throwable t) {
                Toast.makeText(FormActivity.this,t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void onHadleSuccess(String pesan){
        AlertDialog.Builder builder = new AlertDialog.Builder(FormActivity.this);
        builder.setTitle("Info");
        builder.setMessage(pesan)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(FormActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        builder.create();
        builder.show();

    }
}