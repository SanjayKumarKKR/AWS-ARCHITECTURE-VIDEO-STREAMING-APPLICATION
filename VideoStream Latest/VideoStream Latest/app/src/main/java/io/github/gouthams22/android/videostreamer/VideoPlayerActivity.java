package io.github.gouthams22.android.videostreamer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.MILLIS;
import static java.time.temporal.ChronoUnit.MINUTES;

public class VideoPlayerActivity extends AppCompatActivity {
    ProgressDialog pDialog;
    VideoView videoview;
//    TextView difference;
    String videos;
    String movieName;
    int min_index;
    String min_url;
    LocalTime start;
    FirebaseFirestore db;
    String vid_url;
//    String vid_url = "https://d2cgbp3kfv1ttt.cloudfront.net/Clouds-1080p.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent intent = getIntent();
//        videos = intent.getStringExtra("video_url");
//        difference=findViewById(R.id.differ);
        movieName = intent.getStringExtra("movieName");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            start= (LocalTime) intent.getSerializableExtra("start_time");
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Movie").document(movieName)
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("sanjay", "DocumentSnapshot data: " + document.getData());
                        UserHelperClass info = document.toObject(UserHelperClass.class);
                        videos = info.getMp4FileUrl();
                        url_update();
                    } else {
                        Log.d("sanjay", "No such document");
                    }
                } else {
                    Log.d("sanjay", "get failed with ", task.getException());
                }
            }

        });
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.e("sanjay", document.getId() + " => " + document.getData());
//                                UserHelperClass info = document.toObject(UserHelperClass.class);
//                                Log.e("sanjay", String.valueOf(info.getMovieName()));
//                            }
//                        } else {
//                            Log.e("sanjay", "Error getting documents: ", task.getException());
//                        }
//                    }
//                });
    }

    private void url_update() {
        String [] mp4_array = videos.split(",");
        int min_number=10000;
        min_index=100000;
        min_url="";
        for (int i=0;i<mp4_array.length;i++){
            String[] number_string = mp4_array[i].split("#");
            int number = Integer.parseInt(number_string[0]);
            String url =number_string[1];
            if(number<min_number){
                min_number=number;
                min_index=i;
                min_url=url;
            }
        }
        vid_url = min_url;
        Log.d("sanjay",min_index+"");
        //update string words
        min_number++;
        String finalstring="";
        for(int i=0;i<mp4_array.length;i++){
            if (i!=min_index){
                finalstring=finalstring+mp4_array[i]+",";
            }else{
                finalstring=finalstring+min_number+"#"+min_url+",";
            }
        }
        finalstring = finalstring.substring(0,finalstring.length()-1);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Movie").document(movieName)
                .update("mp4FileUrl", finalstring);
        Log.d("sanjay","end db update");
        video();
    }
   public void video(){
       videoview = (VideoView) findViewById(R.id.videoView);
       pDialog = new ProgressDialog(this);
       pDialog.setTitle("Video Stream");
       pDialog.setMessage("Buffering...");
       pDialog.setIndeterminate(false);
       pDialog.setCancelable(true);
       pDialog.show();
       try {
           MediaController mediacontroller = new MediaController(this);
           mediacontroller.setAnchorView(videoview);
           Uri video = Uri.parse(vid_url);
           videoview.setMediaController(mediacontroller);
           videoview.setVideoURI(video);
       } catch (Exception e) {
           Log.e("Error", e.getMessage());
           e.printStackTrace();
       }
       videoview.requestFocus();
       videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           public void onPrepared(MediaPlayer mp) {
               VideoPlayerActivity.this.pDialog.dismiss();
               VideoPlayerActivity.this.videoview.start();
               LocalTime myObj = null;
               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                   myObj = LocalTime.now();
                   long diff = (MILLIS.between(start, myObj) );
                   Log.d("sanjay",diff+" end time");
//                   difference.setText(diff+"");
               }
           }
       });
       videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               finish();
           }
       });

   }
    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        db = FirebaseFirestore.getInstance();
//        db.collection("Movie").document(movieName)
//                .update("mp4FileUrl", videos);


        db.collection("Movie").document(movieName)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    String new_videos = (String) documentSnapshot.get("mp4FileUrl");
                                    String [] mp4_array = new_videos.split(",");
                                    String url_tobechanged =mp4_array[min_index];
                                    String new_number_string[]= url_tobechanged.split("#");
                                    int value =Integer.parseInt(new_number_string[0]);
                                    value--;
                                    mp4_array[min_index]=value+"#"+min_url;
                                    String new_answer="";
                                    for(int j=0;j<mp4_array.length;j++){
                                        new_answer=new_answer+mp4_array[j]+",";
                                    }
                                    new_answer=new_answer.substring(0,new_answer.length()-1);
                                    db.collection("Movie").document(movieName)
                                            .update("mp4FileUrl", new_answer);

                    }

    });

    }
}