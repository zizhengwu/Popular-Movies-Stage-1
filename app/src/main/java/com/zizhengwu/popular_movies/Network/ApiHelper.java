package com.zizhengwu.popular_movies.Network;

import com.zizhengwu.popular_movies.Model.MovieReview;
import com.zizhengwu.popular_movies.Model.MovieTrailer;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ApiHelper {
    public static Observable<MovieTrailer[]> fetchMovieTrailers(String id, String apiKey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(ApiJsonConverter.buildMovieTrailerGsonConverter())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        MovieDBService service = retrofit.create(MovieDBService.class);

        return service.findTrailerByID(id, apiKey);
    }

    public static Observable<MovieReview[]> fetchMovieReviews(String id, String apiKey) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(ApiJsonConverter.buildMovieReviewGsonConverter())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();

        MovieDBService service = retrofit.create(MovieDBService.class);

        return service.findReviewByID(id, apiKey);
    }
}
