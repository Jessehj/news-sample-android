package jessehj.newssample.network.apis

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import jessehj.newssample.BuildConfig
import jessehj.newssample.base.AppConstants
import jessehj.newssample.entity.article.Article
import jessehj.newssample.entity.filter.ArticleFilter
import jessehj.newssample.network.ResError
import jessehj.newssample.network.RetrofitClient
import jessehj.newssample.network.RetrofitService
import jessehj.newssample.util.ModelUtils
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by jessehj on 01/12/2018.
 */

interface ArticleAPI {
    @GET("/${BuildConfig.API_VERSION}/${AppConstants.Headline.uri}")
    fun getTopHeadlines(@QueryMap params: MutableMap<String, String>): Call<JsonObject>

    @GET("/${BuildConfig.API_VERSION}/${AppConstants.Everything.uri}")
    fun getArticles(@QueryMap params: MutableMap<String, String>): Call<JsonObject>

    interface TopHeadlinesCompletion {
        fun onSuccess(articles: MutableList<Article>)
        fun onError(error: ResError)
    }

    interface ArticlesCompletion {
        fun onSuccess(articles: MutableList<Article>)
        fun onError(error: ResError)
    }

    companion object {
        private val api = RetrofitClient.get().create(ArticleAPI::class.java)

        fun loadTopHeadlines(
            page: Int,
            filter: ArticleFilter,
            q: String?,
            completion: TopHeadlinesCompletion
        ) {
            val params = mutableMapOf<String, String>()
            params[AppConstants.Commons.page] = page.toString()
            q?.let { params[AppConstants.Commons.q] = it }
            filter.apply {
                if (this.category != AppConstants.Category.All) {
                    this.category?.value?.let { params[AppConstants.Headline.category] = it }
                }
                params[AppConstants.Headline.country] = this.country.value
            }

            RetrofitService<JsonObject>().request(api.getTopHeadlines(params),
                object : RetrofitService.Completion {
                    override fun onSuccess(response: Any?) {
                        if (response is JsonObject) {
                            val articlesJson =
                                response.get(AppConstants.Article.articles) as JsonArray
                            val articles =
                                ModelUtils.parseJson<MutableList<Article>>(articlesJson.toString())?.let {
                                    it
                                } ?: mutableListOf()

                            completion.onSuccess(articles)
                        } else {
                            completion.onError(ResError("Response Error"))
                        }
                    }

                    override fun onError(error: ResError) {
                        completion.onError(error)
                    }
                })
        }

        fun loadArticles(
            page: Int,
            filter: ArticleFilter,
            q: String?,
            completion: ArticlesCompletion
        ) {
            val params = mutableMapOf<String, String>()
            params[AppConstants.Commons.page] = page.toString()
            q?.let { params[AppConstants.Commons.q] = it }
            filter.apply {
                this.sourceId?.let { params[AppConstants.Everything.sources] = it }
            }

            RetrofitService<JsonObject>().request(
                ArticleAPI.api.getArticles(params),
                object : RetrofitService.Completion {
                    override fun onSuccess(response: Any?) {
                        if (response is JsonObject) {
                            val articlesJson =
                                response.get(AppConstants.Article.articles) as JsonArray
                            val articles =
                                ModelUtils.parseJson<MutableList<Article>>(articlesJson.toString())?.let {
                                    it
                                } ?: mutableListOf()

                            completion.onSuccess(articles)
                        } else {
                            completion.onError(ResError("Response Error"))
                        }
                    }

                    override fun onError(error: ResError) {
                        completion.onError(error)
                    }
                })
        }
    }
}