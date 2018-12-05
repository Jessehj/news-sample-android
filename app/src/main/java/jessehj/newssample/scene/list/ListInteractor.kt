package jessehj.newssample.scene.list

import com.google.gson.Gson
import jessehj.newssample.base.AppManager
import jessehj.newssample.entity.article.Article
import jessehj.newssample.entity.filter.ArticleFilter
import jessehj.newssample.network.apis.ArticleAPI
import jessehj.newssample.util.ModelUtils


interface ListBusinessLogic : ListDataPassing, ListDataStore {
    fun fetchListData(request: List.ListData.Request)
    fun fetchFilterData(request: List.FilterData.Request)
    fun fetchDetailData(request: List.DetailData.Request)
}

interface ListDataPassing {
    fun passSourceId(sourceId: String)
}

interface ListDataStore {
    fun articleJson(): String
}

class ListInteractor : ListBusinessLogic {

    lateinit var presenter: ListPresentationLogic
    private var worker = ListWorker()
    private var articleFilter = ArticleFilter()
    private var articles = mutableListOf<Article>()
    private var sourceId: String? = null
    private var curPage = 1
    private lateinit var selectedArticle: Article

    override fun passSourceId(sourceId: String) {
        this@ListInteractor.sourceId = sourceId
    }

    override fun articleJson(): String {
        return if (::selectedArticle.isInitialized) {
            Gson().toJson(selectedArticle)
        } else {
            ""
        }
    }

    override fun fetchListData(request: List.ListData.Request) {
        if (request.refresh) {
            curPage = 1
        }
        presenter.setPagingEnabled(false)
        ArticleAPI.loadArticles(
            curPage,
            articleFilter,
            request.keyword,
            object : ArticleAPI.ArticlesCompletion {
                override fun onSuccess(articles: MutableList<Article>) {
                    if (request.refresh) {
                        this@ListInteractor.articles = articles
                        presenter.refreshComplete()
                    } else {
                        this@ListInteractor.articles.addAll(articles)
                    }
                    curPage++
                    presenter.setPagingEnabled(articles.isNotEmpty())

                    List.ListData.Response().apply {
                        context = request.context
                        this.articles = articles
                        refresh = request.refresh
                        presenter.presentListData(this)
                    }

                    presenter.dismissProgress()
                }

                override fun onError(error: Error) {
                    presenter.refreshComplete()
                    presenter.presentError(error.localizedMessage)
                    presenter.dismissProgress()
                }
            })
    }

    override fun fetchFilterData(request: List.FilterData.Request) {
        val filterJson = AppManager.getArticleFilter(request.context)
        articleFilter = ModelUtils.parseJson<ArticleFilter>(filterJson)?.let { it } ?:
                ArticleFilter()

        articleFilter.sourceId = sourceId
    }

    override fun fetchDetailData(request: List.DetailData.Request) {
        selectedArticle = articles[request.position]
        presenter.routeToArticleDetail()
    }
}