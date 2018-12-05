package jessehj.newssample.scene.source

import jessehj.newssample.scene.list.listIntent
import java.lang.ref.WeakReference


interface SourceRoutingLogic {
    fun navigateToArticleList()
}

class SourceRouter : SourceRoutingLogic {

    lateinit var activity: WeakReference<SourceActivity>
    lateinit var dataStore: SourceDataStore

    override fun navigateToArticleList() {
        activity.get()?.apply {
            startActivity(listIntent(dataStore.sourceId()))
        }
    }
}