package jessehj.newssample.scene.headline

import android.content.Intent
import java.lang.ref.WeakReference


interface HeadlineRoutingLogic {
    fun navigateToSomewhere(): Intent
}

class HeadlineRouter : HeadlineRoutingLogic {

    lateinit var activity: WeakReference<HeadlineActivity>
    lateinit var dataStore: HeadlineDataStore

    override fun navigateToSomewhere(): Intent {
        val intent = Intent()
        return intent
    }
}