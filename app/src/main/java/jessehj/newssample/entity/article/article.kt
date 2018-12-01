package jessehj.newssample.entity.article

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import jessehj.newssample.AppConstants
import jessehj.newssample.entity.source.Source

/**
 * Created by jessehj on 01/12/2018.
 */

class Article {
    @SerializedName(AppConstants.Source.sources)
    @Expose
    private val source: Source? = null
    @SerializedName(AppConstants.Article.author)
    @Expose
    private val author: String? = null
    @SerializedName(AppConstants.Article.title)
    @Expose
    private val title: String? = null
    @SerializedName(AppConstants.Article.description)
    @Expose
    private val description: String? = null
    @SerializedName(AppConstants.Article.url)
    @Expose
    private val url: String? = null
    @SerializedName(AppConstants.Article.urlToImage)
    @Expose
    private val urlToImage: String? = null
    @SerializedName(AppConstants.Article.publishedAt)
    @Expose
    private val publishedAt: String? = null
    @SerializedName(AppConstants.Article.content)
    @Expose
    private val content: String? = null
}