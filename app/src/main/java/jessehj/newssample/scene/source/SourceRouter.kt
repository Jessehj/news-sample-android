package jessehj.newssample.scene.source

import android.content.Intent
import java.lang.ref.WeakReference


interface SourceRoutingLogic {
    fun navigateToSomewhere(): Intent
}

class SourceRouter : SourceRoutingLogic {

    lateinit var activity: WeakReference<SourceActivity>
    lateinit var dataStore: SourceDataStore

    override fun navigateToSomewhere(): Intent {
        val intent = Intent()
        return intent
    }
}