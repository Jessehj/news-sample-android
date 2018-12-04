package jessehj.newssample.scene.headline

import jessehj.newssample.scene.detail.detailIntent
import java.lang.ref.WeakReference


interface HeadlineRoutingLogic {
    fun navigateToArticleDetail()
}

class HeadlineRouter : HeadlineRoutingLogic {

    lateinit var activity: WeakReference<HeadlineActivity>
    lateinit var dataStore: HeadlineDataStore

    override fun navigateToArticleDetail() {
        activity.get()?.apply {
            startActivity(detailIntent(dataStore.articleJson()))
        }
    }
}