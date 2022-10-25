package com.rojesh.splyzatestapp.networking;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public abstract class NetworkModule {

    private static final String BASE_URL = "https://example.com/ti/";

    @Provides
    @Singleton
    static Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static RepoService providesRetrofitService(Retrofit retrofit){
        return retrofit.create(RepoService.class);
    }
}
