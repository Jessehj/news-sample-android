package jessehj.newssample.network

import jessehj.newssample.base.AppConstants
import jessehj.newssample.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jessehj on 01/12/2018.
 */

object RetrofitClient {

    fun get(): Retrofit = retrofitClient()

    private fun retrofitClient(vararg interceptors: Interceptor): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.ROOT_URL)
        .client(okHttpClient(interceptors))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun okHttpClient(interceptors: Array<out Interceptor>) = OkHttpClient.Builder()
        .addInterceptor(headersInterceptor())
        .addInterceptor(loggingInterceptor())
        .apply { interceptors().addAll(interceptors) }
        .build()

    private fun headersInterceptor() = Interceptor {
        it.proceed(
            it.request().newBuilder()
                .addHeader(AppConstants.Headers.CONTENT_TYPE, AppConstants.Headers.APPLICATION_JSON)
                .addHeader(AppConstants.Headers.X_API_KEY, BuildConfig.API_KEY)
                .build()
        )
    }

    private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }
}