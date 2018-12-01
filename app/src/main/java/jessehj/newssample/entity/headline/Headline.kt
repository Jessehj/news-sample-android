package jessehj.newssample.entity.headline

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import jessehj.newssample.AppConstants

/**
 * Created by jessehj on 01/12/2018.
 */

class Headline {
    @SerializedName("source")
    @Expose
    private val source: Source? = null
    @SerializedName("author")
    @Expose
    private val author: Any? = null
    @SerializedName("title")
    @Expose
    private val title: String? = null
    @SerializedName("description")
    @Expose
    private val description: String? = null
    @SerializedName("url")
    @Expose
    private val url: String? = null
    @SerializedName("urlToImage")
    @Expose
    private val urlToImage: String? = null
    @SerializedName("publishedAt")
    @Expose
    private val publishedAt: String? = null
    @SerializedName("content")
    @Expose
    private val content: String? = null

    class Source {
        @SerializedName("id")
        @Expose
        private val id: String? = null
        @SerializedName("name")
        @Expose
        private val name: String? = null
    }
}