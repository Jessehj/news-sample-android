package jessehj.newssample.scene.headline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import jessehj.newssample.R
import jessehj.newssample.scene.BaseActivity
import kotlinx.android.synthetic.main.view_footer_layout.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

fun Context.headlineIntent(): Intent {
    return intentFor<HeadlineActivity>().noAnimation()
}

interface HeadlineDisplayLogic {
    fun displayHeadlineData(viewModel: Headline.HeadlineData.ViewModel)
}

class HeadlineActivity : BaseActivity(), HeadlineDisplayLogic {

    lateinit var interactor: HeadlineBusinessLogic
    lateinit var router: HeadlineRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HeadlineConfigurator.configure(this)
        setContentView(R.layout.activity_headline)

        configViews()

        fetchHeadlineData()
    }

    override fun onStart() {
        super.onStart()
        updateBottomMenu(this@HeadlineActivity, bottomNavigation)
    }

    fun fetchHeadlineData() {
        Headline.HeadlineData.Request().apply {
            context = this@HeadlineActivity
            interactor.fetchHeadlineData(this)
        }
    }

    private fun configViews() {
        // Header:
        toolbar.setTitle(R.string.title_headlines)
        configToolbar(toolbar, false, true)
        // Footer:
        configBottomNavigation(this@HeadlineActivity, bottomNavigation)
    }

    override fun displayHeadlineData(viewModel: Headline.HeadlineData.ViewModel) {

    }
}