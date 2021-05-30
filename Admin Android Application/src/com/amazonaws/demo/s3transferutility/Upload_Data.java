package com.amazonaws.demo.s3transferutility;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class Upload_Data extends AppCompatActivity {
    TextInputLayout movie_name, movie_language, catagory, movie_year, movie_director,movie_hero,movie_actress,mp4_file_url,mp4_thumbnail_url;

    Button regBtn, regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        setContentView(R.layout.activity_upload__data);
        movie_name = findViewById(R.id.movie_name);
        movie_language = findViewById(R.id.movie_language);
        catagory = findViewById(R.id.catagory);
        movie_year = findViewById(R.id.movie_year);
        movie_director = findViewById(R.id.movie_director);
        movie_hero = findViewById(R.id.movie_hero);
        movie_actress = findViewById(R.id.movie_actress);
        mp4_file_url = findViewById(R.id.mp4_file_url);
        mp4_thumbnail_url=findViewById(R.id.mp4_thumbnail_url);
        regBtn = findViewById(R.id.reg_btn);
//        regToLoginBtn = findViewById(R.id.reg_login_btn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("Movie");
                //Get all the values
                String movieName = movie_name.getEditText().getText().toString();
                String movieLanguage = movie_language.getEditText().getText().toString();
                String catagory_movie = catagory.getEditText().getText().toString();
                String movieYear = movie_year.getEditText().getText().toString();
                String movieDirector = movie_director.getEditText().getText().toString();
                String movieHero = movie_hero.getEditText().getText().toString();
                String movieActress = movie_actress.getEditText().getText().toString();
                String mp4FileUrl = mp4_file_url.getEditText().getText().toString();
                String finalUrl="";
                String[] arrOfStr = mp4FileUrl.split(",");
                for (int i=0;i<arrOfStr.length;i++){
                    arrOfStr[i]=0+"#"+"https://d8xbelx53y1n8.cloudfront.net/"+arrOfStr[i]+"-720p.mp4,";
                }
                for (int i=0;i<arrOfStr.length;i++){
                    finalUrl=finalUrl+arrOfStr[i];
                }
                finalUrl = finalUrl.substring(0, finalUrl.length() - 1);
                mp4FileUrl=finalUrl;
                String mp4_Thumbnail_url = mp4_thumbnail_url.getEditText().getText().toString();
                int flag=0;
                int copies= arrOfStr.length;
                UserHelperClass helperClass = new UserHelperClass(movieName, movieLanguage, catagory_movie, movieYear, movieDirector,movieHero,movieActress,mp4FileUrl,mp4_Thumbnail_url,flag,copies);
                db.collection("Movie").document(movieName).set(helperClass);

            }
        });

    }
}