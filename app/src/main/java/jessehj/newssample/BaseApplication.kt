package jessehj.newssample

import android.app.Application
import timber.log.Timber

/**
 * Created by jessehj on 01/12/2018.
 */


class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}