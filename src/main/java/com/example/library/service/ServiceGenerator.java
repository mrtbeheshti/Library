package com.example.library.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashSet;
import java.util.Set;


public class ServiceGenerator {
    private static Set<Retrofit> retrofits =new HashSet<>();

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        Retrofit retrofit = retrofits.stream().filter(r -> r.baseUrl().toString().equals(baseUrl)).findFirst().orElseGet(() -> {
            Retrofit newRetrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            retrofits.add(newRetrofit);
            return newRetrofit;
        });

        return retrofit.create(serviceClass);

    }
}
