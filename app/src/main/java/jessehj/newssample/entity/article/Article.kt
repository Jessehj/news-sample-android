package jessehj.newssample.entity.article

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import jessehj.newssample.base.AppConstants
import jessehj.newssample.entity.source.Source

/**
 * Created by jessehj on 01/12/2018.
 */

class Article {
    @SerializedName(AppConstants.Source.source)
    @Expose
    var source: Source? = null
    @SerializedName(AppConstants.Article.author)
    @Expose
    var author: String? = null
    @SerializedName(AppConstants.Article.title)
    @Expose
    var title: String? = null
    @SerializedName(AppConstants.Article.description)
    @Expose
    var description: String? = null
    @SerializedName(AppConstants.Article.url)
    @Expose
    var url: String? = null
    @SerializedName(AppConstants.Article.urlToImage)
    @Expose
    var urlToImage: String? = null
    @SerializedName(AppConstants.Article.publishedAt)
    @Expose
    var publishedAt: String? = null
    @SerializedName(AppConstants.Article.content)
    @Expose
    var content: String? = null
}