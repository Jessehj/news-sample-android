package jessehj.newssample.scene.list

import jessehj.newssample.scene.detail.detailIntent
import java.lang.ref.WeakReference


interface ListRoutingLogic {
    fun navigateToArticleDetail()
}

class ListRouter : ListRoutingLogic {

    lateinit var activity: WeakReference<ListActivity>
    lateinit var dataStore: ListDataStore

    override fun navigateToArticleDetail() {
        activity.get()?.apply {
            startActivity(detailIntent(dataStore.articleJson()))
        }
    }
}