package jessehj.newssample.entity.article

import android.content.Context
import android.webkit.URLUtil
import jessehj.newssample.base.AppConstants
import jessehj.newssample.util.ModelUtils

/**
 * Created by jessehj on 02/12/2018.
 */

class ArticleViewModel(context: Context, article: Article) {

    var imgUrl = imgUrl(article.urlToImage)
    var author = author(article)
    var title = article.title
    var content = article.description
    var date = date(context, article.publishedAt)

    private fun imgUrl(url: String?): String? {
        url?.let {
            if (URLUtil.isValidUrl(it)) {
                return it
            }
        }
        return null
    }

    private fun author(article: Article): String {
        var author = ""
        article.source?.name?.let {
            author = it
        }
        article.author?.let {
            if (author.isEmpty()) {
                author = it
            } else {
                author += " - $it"
            }
        }
        return author
    }

    private fun date(context: Context, dateString: String?): String {
        dateString?.let {
            val date = ModelUtils.convertStringToDate(AppConstants.Date.FORMAT_DEFAULT, it)
            date?.let { date ->
                val dateSecs: Long = date.time / 1000
                val curSecs: Long = System.currentTimeMillis() / 1000
                val pastSecs = (curSecs - dateSecs)

                return ModelUtils.pastTime(context, pastSecs.toInt())?.let { result ->
                    result
                } ?: run {
                    ModelUtils.convertDateToString(AppConstants.Date.FORMAT_SIMPLE, date)
                }
            }
        }
        return ""
    }
}