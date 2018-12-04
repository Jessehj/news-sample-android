package jessehj.newssample.scene

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.airbnb.lottie.LottieAnimationView
import jessehj.newssample.R
import jessehj.newssample.scene.headline.HeadlineActivity
import jessehj.newssample.scene.headline.headlineIntent
import jessehj.newssample.scene.source.SourceActivity
import jessehj.newssample.scene.source.sourceIntent

abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val INDEX_HEADLINES = 0
        const val INDEX_SOURCES = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        overridePendingTransition(0, 0)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(0, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun configToolbar(toolbar: Toolbar) {
        configToolbar(toolbar, true, false)
    }

    fun configToolbar(toolbar: Toolbar, homeButtonEnabled: Boolean, titleEnabled: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setHomeButtonEnabled(homeButtonEnabled)
            setDisplayHomeAsUpEnabled(homeButtonEnabled)
            setDisplayShowTitleEnabled(titleEnabled)
        }
    }

    fun configBottomNavigation(context: Context, view: BottomNavigationView) {
        val index = contextIndex(context)
        view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_headlines -> {
                    if (index != INDEX_HEADLINES) {
                        headlineIntent()
                            .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                            .apply { startActivity(this) }
                    }
                    true
                }
                R.id.navigation_sources -> {
                    if (index != INDEX_SOURCES) {
                        sourceIntent()
                            .addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                            .apply { startActivity(this) }
                    }
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun updateBottomMenu(context: Context, view: BottomNavigationView) {
        when (context) {
            is HeadlineActivity -> view.menu.getItem(INDEX_HEADLINES).isChecked = true
            is SourceActivity -> view.menu.getItem(INDEX_SOURCES).isChecked = true
        }
    }

    fun configLoadingProgress(view: LottieAnimationView, enabled: Boolean) {
        view.apply {
            if (enabled) {
                visibility = View.VISIBLE
                if (!isAnimating) {
                    playAnimation()
                }
            } else {
                visibility = View.GONE
                if (isAnimating) {
                    cancelAnimation()
                }
            }
        }
    }

    private fun contextIndex(context: Context): Int {
        return when (context) {
            is HeadlineActivity -> INDEX_HEADLINES
            is SourceActivity -> INDEX_SOURCES
            else -> INDEX_HEADLINES
        }
    }
}
