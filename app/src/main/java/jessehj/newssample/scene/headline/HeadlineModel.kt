package jessehj.newssample.scene.headline

import android.content.Context
import jessehj.newssample.base.AppConstants
import jessehj.newssample.entity.article.Article
import jessehj.newssample.entity.article.ArticleViewModel


class Headline {

    class HeadlineData {

        class Request {
            lateinit var context: Context
            var refresh = false
            var keyword: String? = null
        }

        class Response {
            lateinit var context: Context
            var refresh = false
            var articles = mutableListOf<Article>()
        }

        class ViewModel {
            var viewModels = mutableListOf<ArticleViewModel>()
        }

    }

    class FilterData {
        class Request {
            lateinit var context: Context
            var category: AppConstants.Category? = null
            var country: AppConstants.Country? = null
        }

        class Response {
            lateinit var context: Context
        }

        class ViewModel {

        }
    }

    class DetailData {
        class Request {
            lateinit var context: Context
            var position: Int = -1
        }
    }

    class OpenFilter {
        class Request {
            lateinit var context: Context
        }

        class Response {
            lateinit var context: Context
            var category: AppConstants.Category? = null
            var country: AppConstants.Country = AppConstants.Country.Korea
        }

        class ViewModel {
            var category: AppConstants.Category? = null
            var country: AppConstants.Country = AppConstants.Country.Korea
        }
    }
}
