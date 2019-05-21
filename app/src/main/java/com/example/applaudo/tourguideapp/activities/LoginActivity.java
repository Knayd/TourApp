package com.example.applaudo.tourguideapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.model.User;
import com.example.applaudo.tourguideapp.network.TourApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtUserPassword;
    Button btnLogin;
    CheckBox keepMeLoggedCheckBox;
    ProgressBar progressBar;
    Boolean keepUserLogged = false;
    TourApi tourApi = TourApp.getTourApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUserName = findViewById(R.id.input_user);
        edtUserPassword = findViewById(R.id.input_password);
        progressBar = findViewById(R.id.login_progress_bar);
        btnLogin = findViewById(R.id.btn_login);
        keepMeLoggedCheckBox = findViewById(R.id.keep_me_logged);

        if (shouldOpenHome()) {
            openHomeActivity();
        }

        prepareCheckBox();
        init();

    }

    private Boolean shouldOpenHome() {
        return TourApp.getPreferences().shouldKeepUserLogged();
    }

    private void openHomeActivity() {
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

    private void init() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUserName.getText().toString().trim();
                String pass = edtUserPassword.getText().toString().trim();

                doLogin(user, pass);
            }
        });
    }

    private void doLogin(String user, String pass) {

        progressBar.setVisibility(View.VISIBLE);
        tourApi.getUser(user, pass).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();

                    if (user.getRole() != null && user.getUserName() != null) {
                        openHomeActivity();
                        TourApp.getPreferences().setShouldKeepUserLogged(keepUserLogged);
                    } else {
                        Snackbar.make(findViewById(android.R.id.content), getString(R.string.wrong_credentials), Snackbar.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                } else {
                    Snackbar.make(findViewById(android.R.id.content), getString(R.string.something_went_wrong), Snackbar.LENGTH_LONG).show();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Snackbar.make(findViewById(android.R.id.content), getString(R.string.something_went_wrong), Snackbar.LENGTH_LONG).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void prepareCheckBox() {
        keepMeLoggedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                keepUserLogged = isChecked;
            }
        });
    }


}
