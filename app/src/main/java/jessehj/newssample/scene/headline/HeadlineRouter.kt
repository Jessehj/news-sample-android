package jessehj.newssample.scene.headline

import android.content.Intent
import android.net.Uri
import java.lang.ref.WeakReference


interface HeadlineRoutingLogic {
    fun navigateToArticleDetail()
}

class HeadlineRouter : HeadlineRoutingLogic {

    lateinit var activity: WeakReference<HeadlineActivity>
    lateinit var dataStore: HeadlineDataStore

    override fun navigateToArticleDetail() {
        dataStore.detailUrl()?.apply {
            val uri = Uri.parse(this)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.get()?.startActivity(intent)
        }
    }
}