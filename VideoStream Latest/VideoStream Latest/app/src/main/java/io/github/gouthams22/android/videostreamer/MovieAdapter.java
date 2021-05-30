package io.github.gouthams22.android.videostreamer;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
//    private static final String imgLink = "https://www.bollywoodhungama.com/wp-content/uploads/2019/10/War-11.jpg";
    private List<Boolean> vals;
    private Context context;
    private List<UserHelperClass> movieList;

    public MovieAdapter(List<UserHelperClass> movieList) {
        this.movieList = movieList;
        this.vals = new ArrayList<>();
        for (int i = 0; i < movieList.size(); i++) {
            vals.add(false);
        }
    }

    @NonNull
    @Override
    public MovieAdapter.MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, null);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieAdapter.MovieHolder holder, final int position) {
        Picasso.get().load(movieList.get(position).getThumbnailUrl()).into(holder.imgMovie);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                String movieName = movieList.get(position).getMovieName();
                int flag = movieList.get(position).getFlag();
                LocalTime myObj = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    myObj = LocalTime.now();
                    Log.d("sanjay",myObj+" start time");
                }
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                String s1=movieList.get(position).getMp4FileUrl();
                String[] words=s1.split(",");
                intent.putExtra("video_url", s1);
                intent.putExtra("movieName", movieName);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    intent.putExtra("start_time", myObj);
                }
                Log.e("sanjay", String.valueOf(words)+"dsfddfsf");
                flag++;
                int copies =movieList.get(position).getCopies();
                if(flag==copies){
                    flag=0;
                }
                db.collection("Movie").document(movieName)
                        .update("flag", flag);
//                db.collection("Movie").document(movieName)
//                        .update("adsjkbdsa", flag);
                context.startActivity(intent);







//                String movieName = movieList.get(position).getMovieName();
//                int flag = movieList.get(position).getFlag();
//                Intent intent = new Intent(context, VideoPlayerActivity.class);
//                String s1=movieList.get(position).getMp4FileUrl();
//                String[] words=s1.split(",");
//                intent.putExtra("video_url", words[flag]);
//                Log.e("sanjay", String.valueOf(words[flag]));
//                Log.e("sanjay", String.valueOf(flag));
//                flag++;
//                Log.e("sanjay", String.valueOf(movieName));

            }
        });

        holder.arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vals.get(position)) {
                    holder.getExpandableLayout().setVisibility(View.GONE);
//                    holder.getArrowButton().setAnimation(animateExpand());
                } else {
                    holder.getExpandableLayout().setVisibility(View.VISIBLE);
//                    holder.getArrowButton().setAnimation(animateCollapse());
                }
                vals.set(position, !vals.get(position));
            }
        });

        holder.titleText.setText(movieList.get(position).getMovieName());
        holder.categoryText.setText("Movie Category: "+ movieList.get(position).getCatagory_movie());
        holder.directorText.setText("Director Name: "+movieList.get(position).getMovieDirector());
        holder.actressText.setText("Actress Name: "+movieList.get(position).getMovieActress());
        holder.actorText.setText("Actor Name: "+movieList.get(position).getMovieHero());
        holder.yearText.setText("Released Year: "+movieList.get(position).getMovieYear());
        holder.languageText.setText("Language: "+movieList.get(position).getMovieLanguage());
//        holder.imgMovie.setImageURI(Uri.parse(imgLink));
    }

    private RotateAnimation animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        return rotate;
    }

    private RotateAnimation animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        return rotate;
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        private LinearLayout expandableLayout;
        private CardView cardView;

        public LinearLayout getExpandableLayout() {
            return expandableLayout;
        }

        private ImageView imgMovie, arrowButton;

        public ImageView getArrowButton() {
            return arrowButton;
        }

        private TextView titleText, languageText, yearText, actorText, actressText, directorText, categoryText;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            arrowButton = itemView.findViewById(R.id.arrow);
            cardView = itemView.findViewById(R.id.card_view);
            imgMovie = itemView.findViewById(R.id.movie_img);
            titleText = itemView.findViewById(R.id.movie_title);
            languageText = itemView.findViewById(R.id.txt_language);
            yearText = itemView.findViewById(R.id.txt_year);
            actorText = itemView.findViewById(R.id.txt_actor);
            actressText = itemView.findViewById(R.id.txt_actress);
            directorText = itemView.findViewById(R.id.txt_director);
            categoryText = itemView.findViewById(R.id.txt_category);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
        }

    }
}
