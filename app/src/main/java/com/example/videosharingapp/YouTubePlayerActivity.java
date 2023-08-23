package com.example.videosharingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouTubePlayerActivity extends AppCompatActivity {

    final String youTubeUrlRegEx = "^(https?)?(://)?(www.)?(m.)?((youtube.com)|(youtu.be))/";
    final String[] videoIdRegex = { "\\?vi?=([^&]*)","watch\\?.*v=([^&]*)", "(?:embed|vi?)/([^/?]*)", "^([A-Za-z0-9\\-]*)"};

    SignInCredential credential;
    YouTubePlayerView youTubePlayerView;
    EditText videoUrlEt;
    String videoId;

    FirebaseAuth auth;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_player);


        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        // Check if the user is already signed in
        FirebaseUser currentUser = auth.getCurrentUser();

        credential = getIntent().getParcelableExtra("CREDENTIAL");

        TextView nameTv = findViewById(R.id.userNameTV);
        ImageView avatarView = findViewById(R.id.avatarImage);
        nameTv.setText(currentUser.getDisplayName());
        Picasso.with(this).load(currentUser.getPhotoUrl().toString()).into(avatarView);

        // initialising the GUI widget for the Video Player and user's input for the video to play
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.setEnableAutomaticInitialization(false);
        videoUrlEt = findViewById(R.id.ytVideoUrlEt);
        Button playBtn = findViewById(R.id.ytPlayVideoBtn);
        // setting click event for button
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playVideoButtonClick();
            }
        });

        // YouTubePlayerView is a lifecycle aware widget and it will only play a video when player
        // is visible to user. Thus, we add it as a lifecycle observer for the Activity
        getLifecycle().addObserver(youTubePlayerView);

        //initialising the YouTubePlayerView and loading the default video to play
        youTubePlayerView.initialize(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer){
                super.onReady(youTubePlayer);
                videoId = "KAbJnGLDxnE";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });


//        Button youTubeApiBtn = findViewById(R.id.ytHttpRequestBtn);
//        youTubeApiBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), YouTubeApiRequestActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public String extractVideoIdFromUrl(String url){
        String youTubeLinkWithoutProtocolAndDomain = youTubeLinkWithoutProtocolAndDomain(url);
        // extract the VideoID and returns it
        for (String regex : videoIdRegex){
            Pattern compiledPattern = Pattern.compile(regex);
            Matcher matcher = compiledPattern.matcher(youTubeLinkWithoutProtocolAndDomain);
            if (matcher.find()){
                return matcher.group(1);
            }
        }
        return null;
    }

    private String youTubeLinkWithoutProtocolAndDomain(String url){
        Pattern compiledPattern = Pattern.compile(youTubeUrlRegEx);
        Matcher matcher = compiledPattern.matcher(url);
        if (matcher.find()){
            return url.replace(matcher.group(), "");
        }
        return "";
    }

    public void playVideoButtonClick(){
        // check if the user has given video URL or not
        String urlStr = videoUrlEt.getText().toString();
        if (urlStr.isEmpty()){
            videoId = "KAbJnGLDxnE"; // default Video ID
        } else {
            videoId = extractVideoIdFromUrl(urlStr);
        }
        if (videoId != null && !videoId.isEmpty()){
            youTubePlayerView.getYouTubePlayerWhenReady(this::playVideo);
        }else {
            Toast.makeText(this, "Please enter a valid Youtube URL to play a video", Toast.LENGTH_LONG).show();
        }
    }

    private void playVideo(YouTubePlayer player){
        player.loadVideo(videoId, 0);
    }
}