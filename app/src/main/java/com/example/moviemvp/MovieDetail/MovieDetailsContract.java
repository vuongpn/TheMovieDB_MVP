package com.example.moviemvp.MovieDetail;

import com.example.moviemvp.Model.Movie;

public interface MovieDetailsContract {

    interface Model {

        interface OnFinishedListener {
            void onFinished(Movie movie);

            void onFailure(Throwable t);
        }

        void getMovieDetails(OnFinishedListener onFinishedListener, int movieId);
    }

    interface View {

        void onSuccess();

        void onFailure();

        void setDataToViews(Movie movie);

        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();

        void requestMovieData(int movieId);
    }
}