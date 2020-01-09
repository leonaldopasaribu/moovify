package com.stilldre.moovify.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stilldre.moovify.R;
import com.stilldre.moovify.activity.DetailActivity;
import com.stilldre.moovify.adapter.MovieAdapter;
import com.stilldre.moovify.model.Movie;
import com.stilldre.moovify.repository.MovieRepository;
import com.stilldre.moovify.service.network.callback.OnGetMoviesCallback;
import com.stilldre.moovify.service.click.OnMovieClickCallback;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {

    @BindView(R.id.recyclerView_now_playing)
    RecyclerView rvMovie;
    @BindView(R.id.now_playing_swiper)
    SwipeRefreshLayout swipeRefreshLayout;


    private MovieAdapter movieAdapter;
    private MovieRepository movieRepository;

    public NowPlayingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        getMovies();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);

        ButterKnife.bind(this, view);
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));

        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(() -> getMovies());

        movieRepository = MovieRepository.getInstance();
        return view;
    }

    private void getMovies() {
        movieRepository.getNowPlayingMovie(new OnGetMoviesCallback() {
            @Override
            public void onSuccess(ArrayList<Movie> movies) {
                movieAdapter = new MovieAdapter(movies, callback);
                rvMovie.setAdapter(movieAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError() {
                showError();
            }
        });
    }

    private void showError(){
        Toast.makeText(getActivity(),R.string.no_internet_connection,Toast.LENGTH_SHORT).show();
    }

    OnMovieClickCallback callback = movie -> {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    };
}