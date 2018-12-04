package jessehj.newssample.scene.headline

import com.google.gson.Gson
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
    fun articleJson(): String
}

class HeadlineInteractor : HeadlineBusinessLogic {

    lateinit var presenter: HeadlinePresentationLogic
    private var worker = HeadlineWorker()
    private var articleFilter = ArticleFilter()
    private var articles = mutableListOf<Article>()
    private var curPage = 1
    private lateinit var selectedArticle: Article

    override fun articleJson(): String {
        return if (::selectedArticle.isInitialized) {
            Gson().toJson(selectedArticle)
        } else {
            ""
        }
    }

    override fun fetchHeadlineData(request: Headline.HeadlineData.Request) {
        if (request.refresh) {
            curPage = 1
        }
        presenter.setPagingEnabled(false)
        ArticleAPI.loadTopHeadlines(
            curPage,
            request.keyword,
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

                    presenter.dismissProgress()
                }

                override fun onError(error: Error) {
                    presenter.refreshComplete()
                    presenter.presentError(error.localizedMessage)
                    presenter.dismissProgress()
                }
            })
    }

    override fun fetchFilterData(request: Headline.FilterData.Request) {
        val filterJson = AppManager.getArticleFilter(request.context)
        articleFilter = ModelUtils.parseJson<ArticleFilter>(filterJson)?.let { it } ?:
                ArticleFilter()
    }

    override fun fetchDetailData(request: Headline.DetailData.Request) {
        selectedArticle = articles[request.position]
        presenter.routeToArticleDetail()
    }
}