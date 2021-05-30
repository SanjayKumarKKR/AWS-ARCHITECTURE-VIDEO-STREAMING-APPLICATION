package io.github.gouthams22.android.videostreamer;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<UserHelperClass> movieList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        movieList = getMovieList();
        recyclerView.setAdapter(new MovieAdapter(movieList));
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private List<UserHelperClass> getMovieList() {
        //TODO implement the method to retrieve data from firebase and return list of movie;
        final ArrayList<UserHelperClass> list = new ArrayList<>();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Movie");
        Log.e("sanjay", String.valueOf(reference));
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Movie")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("sanjay", document.getId() + " => " + document.getData());
                                UserHelperClass info = document.toObject(UserHelperClass.class);
                                Log.e("sanjay", String.valueOf(info.getMovieName()));
                                list.add(info);
                            }
                            recyclerView.setAdapter(new MovieAdapter(list));
                            Log.e("sanjay", list.get(0).getMp4FileUrl());
                        } else {
                            Log.e("sanjay", "Error getting documents: ", task.getException());
                        }
                    }
                });


        return list;
    }


    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        movieList = new ArrayList<>();
    }
}