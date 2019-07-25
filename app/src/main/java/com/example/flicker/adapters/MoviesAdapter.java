package com.example.flicker.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.flicker.R;
import com.example.flicker.models.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("smile", "onCreateViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("smile", "onBindViewHolder" + position);
    Movie movie = movies.get(position);
    //Bind he movie data into the view holder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView Title1;
        TextView Overview1;
        ImageView Poster1;

        public ViewHolder(View itemView){
            super(itemView);
            Title1=itemView.findViewById(R.id.Title1); 
            Overview1=itemView.findViewById(R.id.Overview1);
            Poster1=itemView.findViewById(R.id.Poster1);
        }

        public void bind(final Movie movie) {
            Title1.setText(movie.getTitle());
            Overview1.setText(movie.getOverview());
            //Reference to backdrop path when phone is in landscape mode
            String imageUrl = movie.getPosterPath();

            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            }
            Glide.with(context).load(imageUrl).into(Poster1);
            Title1.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                }

            });

        }
    }
}
