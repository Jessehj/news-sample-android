package jessehj.newssample.scene.source

import android.content.Context
import android.content.Intent
import android.os.Bundle
import jessehj.newssample.R
import jessehj.newssample.scene.BaseActivity
import kotlinx.android.synthetic.main.activity_source.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

fun Context.sourceIntent(): Intent {
    return intentFor<SourceActivity>().noAnimation()
}

interface SourceDisplayLogic {
    fun displaySourceData(viewModel: Source.SourceData.ViewModel)
}

class SourceActivity : BaseActivity(), SourceDisplayLogic {

    lateinit var interactor: SourceBusinessLogic
    lateinit var router: SourceRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SourceConfigurator.configure(this)
        setContentView(R.layout.activity_source)

        configViews()

        fetchSourceData()
    }

    override fun onStart() {
        super.onStart()
        updateBottomMenu(this@SourceActivity, bottomNavigation)
    }

    fun fetchSourceData() {
        Source.SourceData.Request().apply {
            context = this@SourceActivity
            interactor.fetchSourceData(this)
        }
    }

    private fun configViews() {
        // Header:
        toolbar.setTitle(R.string.title_sources)
        configToolbar(toolbar, false, true)
        // Footer:
        configBottomNavigation(this@SourceActivity, bottomNavigation)
    }

    override fun displaySourceData(viewModel: Source.SourceData.ViewModel) {

    }
}