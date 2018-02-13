package com.example.macbookpro.MovieBook.Model.NetworkHelper;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RetrofitModule {
    private String base_url = "https://api.themoviedb.org/3/movie/";

    @Provides
    public MyMovieApiClient getMovieApiClient(Retrofit retrofit) {
        return retrofit.create(MyMovieApiClient.class);
    }

    @Provides
    public Retrofit getRetrofit(OkHttpClient client, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();
    }

    @Provides
    public OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
    }

    @Provides
    public GsonConverterFactory getGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public HttpLoggingInterceptor getHttpLogginInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


}
