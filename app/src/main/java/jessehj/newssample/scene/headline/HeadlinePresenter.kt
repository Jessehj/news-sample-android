package jessehj.newssample.scene.headline

import jessehj.newssample.entity.article.ArticleViewModel
import java.lang.ref.WeakReference


interface HeadlinePresentationLogic {
    fun presentHeadlineData(response: Headline.HeadlineData.Response)
    fun setPagingEnabled(enabled: Boolean)
    fun refreshComplete()
    fun presentError(errMsg: String)
    fun routeToArticleDetail()
    fun dismissProgress()
}

class HeadlinePresenter : HeadlinePresentationLogic {

    lateinit var activity: WeakReference<HeadlineDisplayLogic>
    private var articleViewModels = mutableListOf<ArticleViewModel>()

    override fun setPagingEnabled(enabled: Boolean) {
        activity.get()?.setPagingEnabled(enabled)
    }

    override fun refreshComplete() {
        activity.get()?.refreshComplete()
    }

    override fun presentError(errMsg: String) {
        activity.get()?.displayError(errMsg)
    }

    override fun presentHeadlineData(response: Headline.HeadlineData.Response) {
        val viewModels = mutableListOf<ArticleViewModel>()
        response.articles.forEach {
            viewModels.add(ArticleViewModel(response.context, it))
        }

        if (response.refresh) {
            articleViewModels = viewModels
        } else {
            articleViewModels.addAll(viewModels)
        }

        Headline.HeadlineData.ViewModel().apply {
            this.viewModels = articleViewModels
            activity.get()?.displayHeadlineData(this)
        }
    }

    override fun routeToArticleDetail() {
        activity.get()?.routeToArticleDetail()
    }

    override fun dismissProgress() {
        activity.get()?.dismissProgress()
    }
}