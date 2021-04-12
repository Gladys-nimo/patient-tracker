package com.moringaschool.patienttracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringaschool.patienttracker.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.registerTextView)
    TextView mRegisterTextView;
    @BindView(R.id.passwordLoginButton)
    Button mPasswordLoginButton;

    @BindView(R.id.emailEditText) TextView emailEdit;
    @BindView(R.id.passwordEditText)
    TextView passwordEdit;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);


        mRegisterTextView.setOnClickListener(this);
        mPasswordLoginButton.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == createAccountTextView) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if (view == loginButton) {
            logInWithPassWord();
            showLoadingState();
        }
    }

    private void logInWithPassWord() {
        String email = emailEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        if (email.equals("")) {
            emailEdit.setError("Please Enter Your Email");
            return;
        }
        if (password.equals("")) {
            passwordEdit.setError("Password Cannot be blank");
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideLoadingState();
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showLoadingState() {

        passwordEdit.setVisibility(View.GONE);
        emailEdit.setVisibility(View.GONE);
        createAccountTextView.setVisibility(View.GONE);
        loginButton.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideLoadingState() {
        mProgressBar.setVisibility(View.GONE);
        passwordEdit.setVisibility(View.VISIBLE);
        emailEdit.setVisibility(View.VISIBLE);
        createAccountTextView.setVisibility(View.VISIBLE);
        loginButton.setVisibility(View.VISIBLE);
    }
}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//
//        ButterKnife.bind(this);
//        mRegisterTextView.setOnClickListener(this);
//        mPasswordLoginButton.setOnClickListener(this);
//
//    }
//
//    @Override
//    public void onClick(View view) {
//        if (view == mRegisterTextView) {
//            Intent intent = new Intent(LoginActivity.this, CreateAccountActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        if (view == mPasswordLoginButton) {
//            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
//}
