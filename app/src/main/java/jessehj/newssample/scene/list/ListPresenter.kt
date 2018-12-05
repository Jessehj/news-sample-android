package jessehj.newssample.scene.list

import jessehj.newssample.entity.article.ArticleViewModel
import java.lang.ref.WeakReference


interface ListPresentationLogic {
    fun presentListData(response: List.ListData.Response)
    fun setPagingEnabled(enabled: Boolean)
    fun refreshComplete()
    fun presentError(errMsg: String)
    fun routeToArticleDetail()
    fun dismissProgress()
}

class ListPresenter : ListPresentationLogic {

    lateinit var activity: WeakReference<ListDisplayLogic>
    private var articleViewModels = mutableListOf<ArticleViewModel>()

    override fun presentListData(response: List.ListData.Response) {
        val viewModels = mutableListOf<ArticleViewModel>()
        response.articles.forEach {
            viewModels.add(ArticleViewModel(response.context, it))
        }

        if (response.refresh) {
            articleViewModels = viewModels
        } else {
            articleViewModels.addAll(viewModels)
        }

        List.ListData.ViewModel().apply {
            this.viewModels = articleViewModels
            activity.get()?.displayListData(this)
        }
    }

    override fun setPagingEnabled(enabled: Boolean) {
        activity.get()?.setPagingEnabled(enabled)
    }

    override fun refreshComplete() {
        activity.get()?.refreshComplete()
    }

    override fun presentError(errMsg: String) {
        activity.get()?.displayError(errMsg)
    }

    override fun routeToArticleDetail() {
        activity.get()?.routeToArticleDetail()
    }

    override fun dismissProgress() {
        activity.get()?.dismissProgress()
    }
}