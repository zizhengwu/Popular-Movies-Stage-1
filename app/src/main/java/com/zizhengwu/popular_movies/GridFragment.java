package com.zizhengwu.popular_movies;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.zizhengwu.popular_movies.Adapter.GridMovieAdapter;
import com.zizhengwu.popular_movies.Model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridFragment extends Fragment {

    private GridMovieAdapter gridMovieAdapter;
    private GridView gridView;
    private OnItemSelectedListener listener;

    public interface  OnItemSelectedListener {
        void onMovieSelected(Movie movie);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemSelectedListener) {
            listener = (OnItemSelectedListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement MyListFragment.OnItemSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_main_grid, container, false);
        gridView = (GridView) view.findViewById(R.id.movie_grid);

        gridMovieAdapter = new GridMovieAdapter(view.getContext());
        gridView.setAdapter(gridMovieAdapter);

        setUpGridFragment(savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("movie", new ArrayList<>(Arrays.asList(gridMovieAdapter.getMovies())));
    }

    public void loadMovies(Movie[] movies) {
        gridMovieAdapter.loadData(movies);
    }

    private void setUpGridFragment(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            List<Movie> items = savedInstanceState.getParcelableArrayList("movie");
            Movie[] movies = items.toArray(new Movie[items.size()]);
            gridMovieAdapter.loadData(movies);
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                listener.onMovieSelected(gridMovieAdapter.getMovies()[position]);
            }
        });
    }
}
