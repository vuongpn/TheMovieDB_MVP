package com.example.moviemvp.movielist;

import com.example.moviemvp.model.Movie;

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
