package jessehj.newssample.scene.list

import android.content.Context
import jessehj.newssample.entity.article.Article
import jessehj.newssample.entity.article.ArticleViewModel


class List {

    class ListData {

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
        }

        class Response {
            lateinit var context: Context
        }
    }

    class DetailData {
        class Request {
            lateinit var context: Context
            var position: Int = -1
        }
    }
}
