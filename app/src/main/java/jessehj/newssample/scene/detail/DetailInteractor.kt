package jessehj.newssample.scene.detail

import jessehj.newssample.entity.article.Article
import jessehj.newssample.util.ModelUtils


interface DetailBusinessLogic : DetailDataPassing, DetailDataStore {
    fun fetchDetailData(request: Detail.DetailData.Request)

}

interface DetailDataPassing {
    fun passArticleJson(articleJson: String)
}

interface DetailDataStore {
    fun articleUrl(): String?
    fun sourceId(): String?
}

class DetailInteractor : DetailBusinessLogic {

    lateinit var presenter: DetailPresentationLogic
    private var worker = DetailWorker()
    private var article: Article = Article()

    override fun passArticleJson(articleJson: String) {
        ModelUtils.parseJson<Article>(articleJson)?.let {
            this@DetailInteractor.article = it
        }
    }

    override fun articleUrl(): String? = article.url

    override fun sourceId(): String? = article.source?.id

    override fun fetchDetailData(request: Detail.DetailData.Request) {
        Detail.DetailData.Response().apply {
            context = request.context
            srcId = article.source?.id
            srcName = article.source?.name
            urlToImage = article.urlToImage
            author = article.author
            title = article.title
            content = article.description
            publishedAt = article.publishedAt
            presenter.presentDetailData(this)
        }
    }

}