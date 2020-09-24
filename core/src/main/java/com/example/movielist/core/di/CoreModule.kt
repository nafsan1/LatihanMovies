package com.example.movielist.core.di

import androidx.room.Room
import com.example.movielist.core.data.MoviesRepository
import com.example.movielist.core.data.local.LocalDataSource
import com.example.movielist.core.data.local.room.MoviesDatabase
import com.example.movielist.core.data.remote.RemoteDataSource
import com.example.movielist.core.data.remote.network.ApiService
import com.example.movielist.core.domain.repository.IMoviesRepository
import com.example.movielist.core.utils.APIMOVIE
import com.example.movielist.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.core.definition.Kind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MoviesDatabase>().moviesDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("movies".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MoviesDatabase::class.java, "Movies.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.themoviedb.org"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/ODKjQ6+i+TVJ97JdbHWBJQ2gfxWgiphouCnTv8QypNc=")
            .add(hostname, "sha256/YZPgTZ+woNCCCIW3LH2CxQeLzB/1m42QcCTBSdgayjs=")
            .add(hostname, "sha256/iie1VXtL7HzAMF+/PVPR9xzT80kQxdZeJ+zduCB3uj0=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(APIMOVIE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single {
        LocalDataSource(get())
    }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMoviesRepository> {
        MoviesRepository(
            get(),
            get(),
            get()
        )
    }
}