package jessehj.newssample.entity.article

import android.content.Context
import jessehj.newssample.util.ModelUtils

/**
 * Created by jessehj on 02/12/2018.
 */

class ArticleViewModel(context: Context, article: Article) {

    var imgUrl = ModelUtils.validUrl(article.urlToImage)
    var author = ModelUtils.authorComb(article.source?.name, article.author)
    var title = article.title
    var content = article.description
    var date = ModelUtils.articleDate(context, article.publishedAt)
    var srcImgPath = ModelUtils.sourceImageMapper(article.source?.id)
    var srcName = article.source?.name

}