package com.example.videosharingapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class LoginActivity extends AppCompatActivity {

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private ActivityResultLauncher<IntentSenderRequest> signInRequestLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Check if the user is already logged in
        if (isUserLoggedIn()) {
            startMainActivity();
            return; // No need to proceed with the rest of the code
        }

        oneTapClient = Identity.getSignInClient(this);
        signInRequest = BeginSignInRequest.builder()
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        // Your server's client ID, not your Android client ID.
                        .setServerClientId(getString(R.string.web_client_id))
                        // Only show accounts previously used to sign in
                        .setFilterByAuthorizedAccounts(false)
                        .build()
                )
                .setAutoSelectEnabled(true)
                .build();

        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInOnClick(view);
            }
        });

        signInRequestLauncher = registerForActivityResult(new ActivityResultContracts.StartIntentSenderForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            // Sign in successful
                            try {
                                SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(result.getData());
                                String idToken = credential.getGoogleIdToken();
                                if (idToken != null){
                                    // Got an ID token from Google to use for authentication
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.putExtra("CREDENTIAL", credential);
                                    startActivity(intent);
                                }
                            } catch (ApiException e){
                                Log.d("API Exception", "issue with sign in");
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public void signInOnClick(View view){
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult beginSignInResult) {
                        IntentSenderRequest intentSenderRequest =
                                new IntentSenderRequest.Builder(beginSignInResult.getPendingIntent().getIntentSender())
                                        .build();
                        signInRequestLauncher.launch(intentSenderRequest);
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("FAILURE", "sign in failed!");
                        e.printStackTrace();
                    }
                });
    }

    // Todo create this method
    private boolean isUserLoggedIn() {
        // Implement your logic to check if the user is logged in
        // This could involve checking for a saved token or any other relevant info
        // Return true if the user is logged in, false otherwise
        // For example:
        // return userToken != null;
        return true;
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish the LoginActivity so the user can't go back to it after login
    }
}

