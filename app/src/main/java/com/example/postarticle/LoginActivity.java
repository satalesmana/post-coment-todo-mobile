package com.example.postarticle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postarticle.Db.DbManager;
import com.example.postarticle.Db.DbUsersHelper;
import com.example.postarticle.Model.LoginModel;
import com.example.postarticle.Model.PostModel;
import com.example.postarticle.Retrofit.ApiService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    DbManager dbManager;
    Button btnLogin;
    EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.username);
        etPassword = findViewById(R.id.password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSignIn();
            }
        });
    }

    private  void onSignIn(){
        String emial = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        Map<String, String> params = new HashMap<>();
        params.put("email",emial);
        params.put("password",password);


        ApiService.endpoint().setLoginUser(params).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if(response.isSuccessful()){
                    LoginModel loginModels = response.body();
                    saveData(
                        loginModels.getUser().getName(),
                        loginModels.getUser().getEmail(),
                        "",
                        loginModels.getAccess_token()
                    );

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());

                        Log.d("Tos", jObjError.toString());
                    } catch (Exception e) {
                        Log.d("E",e.getMessage());
                    }
                }

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Log.d("ERR onSignIn", t.toString());
                Toast.makeText(getBaseContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });

    }

    private void saveData(
            String name,
            String email,
            String password,
            String token
    ){
        dbManager = new DbManager(this);
        dbManager.open();
        dbManager.insertUser(name,email,password,token);
        dbManager.close();
    }
}