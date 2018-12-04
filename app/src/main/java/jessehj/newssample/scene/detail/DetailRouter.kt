package jessehj.newssample.scene.detail

import android.content.Intent
import android.net.Uri
import java.lang.ref.WeakReference


interface DetailRoutingLogic {
    fun navigateToOrigin()
    fun navigateToArticles()
}

class DetailRouter : DetailRoutingLogic {

    lateinit var activity: WeakReference<DetailActivity>
    lateinit var dataStore: DetailDataStore

    override fun navigateToOrigin() {
        dataStore.articleUrl()?.apply {
            val uri = Uri.parse(this)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            activity.get()?.startActivity(intent)
        }
    }

    override fun navigateToArticles() {
        dataStore.sourceId()?.apply {

        }
    }
}