package jessehj.newssample.network.apis

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import jessehj.newssample.base.AppConstants
import jessehj.newssample.BuildConfig
import jessehj.newssample.entity.filter.SourceFilter
import jessehj.newssample.entity.source.Source
import jessehj.newssample.network.RetrofitClient
import jessehj.newssample.network.RetrofitService
import jessehj.newssample.util.ModelUtils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by jessehj on 01/12/2018.
 */

interface SourceAPI {
    @GET("/${BuildConfig.API_VERSION}/${AppConstants.Source.uri}")
    fun getSources(@QueryMap params: MutableMap<String, String>): Call<JsonObject>

    interface SourcesCompletion {
        fun onSuccess(sources: MutableList<Source>)
        fun onError(error: Error)
    }

    companion object {
        private val api = RetrofitClient.get().create(SourceAPI::class.java)

        fun loadSources(filter: SourceFilter, completion: SourcesCompletion) {
            val params = mutableMapOf<String, String>()
            filter.apply {
                this.category?.value?.let { params[AppConstants.Source.category] = it }
                this.country?.value?.let { params[AppConstants.Source.country] = it }
            }

            RetrofitService<JsonObject>().request(api.getSources(params),
                    object : RetrofitService.Completion {
                        override fun onSuccess(response: Any?) {
                            if (response is JsonObject) {
                                val sourcesJson = response.get(AppConstants.Source.sources) as JsonArray
                                val sources = ModelUtils.parseJson<MutableList<Source>>(sourcesJson.toString())?.let {
                                    it
                                } ?: mutableListOf()

                                completion.onSuccess(sources)
                            } else {
                                completion.onError(Error("Response Error"))
                            }
                        }

                        override fun onError(error: Error) {
                            completion.onError(error)
                        }
                    })
        }
    }

}