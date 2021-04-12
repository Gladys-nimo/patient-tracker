package com.moringaschool.patienttracker.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringaschool.patienttracker.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = CreateAccountActivity.class.getSimpleName();
    @BindView(R.id.nameEditText) EditText nameEdit;
    @BindView(R.id.emailEditText) EditText emailEdit;
    @BindView(R.id.passwordEditText) EditText passwordEdit;
    @BindView(R.id.confirmPasswordEditText) EditText confirmPasswordEdit;
    @BindView(R.id.createUserButton) Button createUserButton;
    @BindView(R.id.loginTextView) TextView loginTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);


        createUserButton.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        createAuthStateListener();
    }
    @Override
    public void onClick(
            View view) {
        if (view == loginTextView) {
            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        if (view == createUserButton) {
            createUser();
        }
    }
    private boolean confirmEmail(String email){
        boolean isValidEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isValidEmail) {
            emailEdit.setError("Please Enter a Valid Email Address");
            return false;
        }
        return true;
    }
    private boolean confirmName(String name) {
        if (name.equals("")) {
            nameEdit.setError("Please Enter a Valid Name");
            return false;
        }
        return true;
    }
    private boolean confirmPassword(String password, String confirmPassword){
        if (password.length() < 6) {
            passwordEdit.setError("Please Set Up a Password Containing At Least 6 Characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            passwordEdit.setError("Passwords Do Not Match");
            return false;
        }
        return true;
    }
    private void showLoadingState() {

        nameEdit.setVisibility(View.GONE);
        emailEdit.setVisibility(View.GONE);
        passwordEdit.setVisibility(View.GONE);
        confirmPasswordEdit.setVisibility(View.GONE);
        createUserButton.setVisibility(View.GONE);
        loginTextView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
//        createAccountImageView.setVisibility(View.GONE);
    }
    private void hideLoadingState() {
        nameEdit.setVisibility(View.VISIBLE);
        emailEdit.setVisibility(View.VISIBLE);
        passwordEdit.setVisibility(View.VISIBLE);
        confirmPasswordEdit.setVisibility(View.VISIBLE);
        createUserButton.setVisibility(View.VISIBLE);
        loginTextView.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }
    private void createUser() {
        final String userName = nameEdit.getText().toString().trim();
        final String userEmail = emailEdit.getText().toString().trim();
        String password = passwordEdit.getText().toString().trim();
        String confirmPassword = confirmPasswordEdit.getText().toString().trim();
        name = nameEdit.getText().toString().trim();
        boolean validEmail = confirmEmail(userEmail);
        boolean validName = confirmName(userName);
        boolean validPassword = confirmPassword(password, confirmPassword);
        boolean validUserName = confirmName(name);
        if (!validEmail || !validName || !validPassword) return;
        showLoadingState();
        firebaseAuth.createUserWithEmailAndPassword(userEmail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        hideLoadingState();
                        if (task.isSuccessful()) {
                            Toast.makeText(CreateAccountActivity.this, "Account Creation Successful", Toast.LENGTH_LONG).show();
                            createFireBaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void createAuthStateListener() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
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
    private void createFireBaseUserProfile(final FirebaseUser firebaseUser) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
        firebaseUser.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, Objects.requireNonNull(firebaseUser.getDisplayName()));
                            Toast.makeText(CreateAccountActivity.this, "Display View Updated", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}