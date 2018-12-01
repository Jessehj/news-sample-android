package jessehj.newssample.network.apis

import jessehj.newssample.AppConstants
import jessehj.newssample.entity.article.Article
import jessehj.newssample.entity.filter.ArticleFilter
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by jessehj on 01/12/2018.
 */

@Config(
    manifest = "src/main/AndroidManifest.xml",
    packageName = "jessehj.newssample"
)
@RunWith(RobolectricTestRunner::class)
class ArticleAPIUnitTest {

    @Test
    fun articleAPI_loadTopHeadlines_with_Korean() {
        val filter = ArticleFilter(
            null,
            AppConstants.Country.Korea,
            null
        )
        ArticleAPI.loadTopHeadlines(1, filter, object : ArticleAPI.TopHeadlinesCompletion {
            override fun onSuccess(articles: MutableList<Article>) {
                Assert.assertNotNull(articles)
            }

            override fun onError(error: Error) {

            }
        })
    }

    @Test
    fun articleAPI_loadTopHeadlines_dataNotFound_shouldReturn_emptyList() {
        val filter = ArticleFilter(
            null,
            AppConstants.Country.Korea,
            "a#s!d@f"
        )
        ArticleAPI.loadTopHeadlines(1, filter, object : ArticleAPI.TopHeadlinesCompletion {
            override fun onSuccess(articles: MutableList<Article>) {
                Assert.assertTrue(articles.isEmpty())
            }

            override fun onError(error: Error) {

            }
        })
    }
}