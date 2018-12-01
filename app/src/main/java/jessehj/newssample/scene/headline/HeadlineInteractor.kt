package jessehj.newssample.scene.headline

import jessehj.newssample.base.AppManager
import jessehj.newssample.entity.article.Article
import jessehj.newssample.entity.filter.ArticleFilter
import jessehj.newssample.network.apis.ArticleAPI
import jessehj.newssample.util.ModelUtils


interface HeadlineBusinessLogic : HeadlineDataPassing, HeadlineDataStore {
    fun fetchHeadlineData(request: Headline.HeadlineData.Request)
    fun fetchFilterData(request: Headline.FilterData.Request)
    fun fetchDetailData(request: Headline.DetailData.Request)
}

interface HeadlineDataPassing {
    // fun setPassedData(Obj: Any)
}

interface HeadlineDataStore {
    fun detailUrl(): String?
}

class HeadlineInteractor : HeadlineBusinessLogic {

    lateinit var presenter: HeadlinePresentationLogic
    private var worker = HeadlineWorker()
    private var articleFilter = ArticleFilter()
    private var articles = mutableListOf<Article>()
    private var curPage = 1
    private var url: String? = null

    override fun detailUrl(): String? = url

    override fun fetchHeadlineData(request: Headline.HeadlineData.Request) {
        if (request.refresh) {
            curPage = 1
        }
        presenter.setPagingEnabled(false)
        ArticleAPI.loadTopHeadlines(
            curPage,
            articleFilter,
            object : ArticleAPI.TopHeadlinesCompletion {
                override fun onSuccess(articles: MutableList<Article>) {
                    if (request.refresh) {
                        this@HeadlineInteractor.articles = articles
                        presenter.refreshComplete()
                    } else {
                        this@HeadlineInteractor.articles.addAll(articles)
                    }
                    curPage++
                    presenter.setPagingEnabled(articles.isNotEmpty())

                    Headline.HeadlineData.Response().apply {
                        context = request.context
                        this.articles = articles
                        refresh = request.refresh
                        presenter.presentHeadlineData(this)
                    }
                }

                override fun onError(error: Error) {
                    presenter.refreshComplete()
                    presenter.presentError(error.localizedMessage)
                }
            })
    }

    override fun fetchFilterData(request: Headline.FilterData.Request) {
        val filterJson = AppManager.getArticleFilter(request.context)
        articleFilter = ModelUtils.parseJson<ArticleFilter>(filterJson)?.let { it } ?:
                ArticleFilter()
    }

    override fun fetchDetailData(request: Headline.DetailData.Request) {
        val article = articles[request.position]
        article.url?.let {
            this@HeadlineInteractor.url = it
        }
        presenter.routeToArticleDetail()
    }
}