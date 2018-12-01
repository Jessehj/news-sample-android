package jessehj.newssample.entity.headline

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import jessehj.newssample.AppConstants
import jessehj.newssample.network.RetrofitClient
import jessehj.newssample.network.RetrofitService
import jessehj.newssample.utils.ModelUtils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by jessehj on 01/12/2018.
 */

interface HeadlineApi {
    @GET(AppConstants.Headline.ENDPOINT)
    fun getTopHeadlines(@QueryMap params: MutableMap<String, String>): Call<JsonObject>

    interface findAllCompletion {
        fun onSuccess(headlines: MutableList<Headline>)
        fun onError(error: Error)
    }

    companion object {
        private val api = RetrofitClient.get().create(HeadlineApi::class.java)

        fun findAll(page: Int, filter: HeadlineFilter, completion: findAllCompletion) {
            val params = mutableMapOf<String, String>()
            params[AppConstants.Commons.page] = page.toString()
            filter.apply {
                this.category?.value?.let { params[AppConstants.Headline.category] = it }
//                this.country?.value?.let { params[AppConstants.Headline.country] = it }
                params[AppConstants.Headline.country] = "en"
                this.q?.let { params[AppConstants.Commons.q] = it }
            }

            RetrofitService<JsonObject>().request(api.getTopHeadlines(params), object : RetrofitService.Completion {
                override fun onSuccess(response: Any?) {
                    if (response is JsonObject) {
                        val articles = response.get(AppConstants.Headline.articles) as JsonArray

                        if (articles.size() == 0) {
                            val results = ModelUtils.parseJson<MutableList<Headline>>(articles.toString())
                            completion.onSuccess(results as MutableList<Headline>)
                        } else {
                            completion.onSuccess(mutableListOf())
                        }
                    } else {
                        completion.onError(Error("Response error"))
                    }
                }

                override fun onError(error: Error) {
                    completion.onError(error)
                }
            })
        }
    }
}
 