package com.example.moviemvp.MovieList;

import com.example.moviemvp.Model.Movie;

import java.util.List;

public interface MovieListContract {
    interface Model {
        interface OnFinishedListener {
            void onSuccess(List<Movie> movieArrayList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pageNo);

    }

    interface View {

        void onSuccess(List<Movie> movieList);

        void onFailure(Throwable t);
    }

    interface Presenter {
        void onDestroy();

        void requestDataFromServer();
    }
}
