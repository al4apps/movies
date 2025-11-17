package com.al4apps.movies.di

import com.al4apps.movies.data.datasource.MoviesDataSourceImpl
import com.al4apps.movies.data.network.SequeniaApi
import com.al4apps.movies.data.repository.MoviesRepositoryImpl
import com.al4apps.movies.domain.GetMovieUseCase
import com.al4apps.movies.domain.GetMoviesUseCase
import com.al4apps.movies.presentation.movie.MovieViewModel
import com.al4apps.movies.presentation.movies.MoviesViewModel
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<SequeniaApi> {

        val baseUrl = "https://s3-eu-west-1.amazonaws.com/sequeniatesttask/"

        val okHttpClient = OkHttpClient.Builder().build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

        retrofit.create(SequeniaApi::class.java)
    }

    single<MoviesDataSourceImpl> {
        MoviesDataSourceImpl(sequeniaApi = get())
    }

    single<MoviesRepositoryImpl> {
        MoviesRepositoryImpl(moviesDataSource = get())
    }

}

val domainModule = module {

    factory<GetMoviesUseCase> {
        GetMoviesUseCase(moviesRepository = get())
    }

    factory<GetMovieUseCase> {
        GetMovieUseCase(moviesRepository = get())
    }

}

val uiModule = module {

    viewModel<MoviesViewModel> {
        MoviesViewModel(getMoviesUseCase = get())
    }

    viewModel<MovieViewModel> {
        MovieViewModel(getMovieUseCase = get())
    }
}