package com.stilldre.favoritemodule;




import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.stilldre.favoritemodule.adapter.FavoriteAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.stilldre.favoritemodule.database.DatabaseContract.CONTENT_URI;

public class MainActivity extends AppCompatActivity {

    private FavoriteAdapter favoriteAdapter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView_favorite)
    RecyclerView recyclerView_favorite;
    @BindView(R.id.favorite_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onResume() {
        super.onResume();
        loadFavoriteMovies();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.favorite));
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadFavoriteMovies();
            }
        });

        favoriteAdapter = new FavoriteAdapter(this);
        recyclerView_favorite.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_favorite.setAdapter(favoriteAdapter);
    }

    private void loadFavoriteMovies(){
        new LoadFavoriteAsync().execute();
        swipeRefreshLayout.setRefreshing(false);
    }

    private class LoadFavoriteAsync extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            favoriteAdapter.setFavoriteMovies(cursor);
            favoriteAdapter.notifyDataSetChanged();
            recyclerView_favorite.setAdapter(favoriteAdapter);
        }
    }


}
