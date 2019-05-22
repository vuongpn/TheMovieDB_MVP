package com.example.moviemvp.MovieList;

import com.example.moviemvp.Model.Movie;

import java.util.List;

public interface MovieListContract {
    interface Model {
        interface OnFinishedListener {
            void onFinished(List<Movie> movieArrayList);

            void onFailure(Throwable t);
        }

        void getMovieList(OnFinishedListener onFinishedListener, int pageNo);

    }

    interface View {
        void onSuccess();

        void onFailure();

        void setDataToRecyclerView(List<Movie> movieList);

        void onResponseFailure(Throwable t);
    }

    interface Presenter {
        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();
    }
}
