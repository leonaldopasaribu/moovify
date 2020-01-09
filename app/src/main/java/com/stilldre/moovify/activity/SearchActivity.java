package com.stilldre.moovify.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import android.view.Menu;
import android.widget.Toast;

import com.stilldre.moovify.R;
import com.stilldre.moovify.adapter.MovieAdapter;
import com.stilldre.moovify.model.Movie;
import com.stilldre.moovify.repository.MovieRepository;
import com.stilldre.moovify.service.click.OnMovieClickCallback;
import com.stilldre.moovify.service.network.callback.OnGetMoviesCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.activity_search)
    SearchView searchView;
    @BindView(R.id.recyclerView_movie_list)
    RecyclerView rvMovie;

    private MovieAdapter movieAdapter;
    private MovieRepository movieRepository;
    private String movieTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        searchView.onActionViewExpanded();
        searchView.setIconified(false);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                movieTitle = s;
                getMovies();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        rvMovie.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
        movieRepository = MovieRepository.getInstance();
    }

    private void getMovies() {
        movieRepository.searchMovie(movieTitle, new OnGetMoviesCallback() {
            @Override
            public void onSuccess(ArrayList<Movie> movies) {
                movieAdapter = new MovieAdapter(movies, callback);
                rvMovie.setAdapter(movieAdapter);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    OnMovieClickCallback callback = movie -> {
        Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    };

    private void showError() {
        Toast.makeText(SearchActivity.this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
    }
}
