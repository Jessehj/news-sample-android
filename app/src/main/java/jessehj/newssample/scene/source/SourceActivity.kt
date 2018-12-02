package jessehj.newssample.scene.source

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import jessehj.newssample.R
import jessehj.newssample.scene.BaseActivity
import jessehj.newssample.scene.adapter.SourceAdapter
import kotlinx.android.synthetic.main.activity_source.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

fun Context.sourceIntent(): Intent {
    return intentFor<SourceActivity>().noAnimation()
}

interface SourceDisplayLogic {
    fun displaySourceData(viewModel: Source.SourceData.ViewModel)
    fun displayError(errMsg: String)
    fun displayProress()
    fun dismissProgress()

}

class SourceActivity : BaseActivity(), SourceDisplayLogic {

    lateinit var interactor: SourceBusinessLogic
    lateinit var router: SourceRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SourceConfigurator.configure(this)
        setContentView(R.layout.activity_source)

        configViews()

        fetchFilterData()
        fetchSourceData()
    }

    override fun onStart() {
        super.onStart()
        updateBottomMenu(this@SourceActivity, bottomNavigation)
    }

    fun fetchFilterData() {
        Source.FilterData.Request().apply {
            context = this@SourceActivity
            interactor.fetchFilterData(this)
        }
    }

    fun fetchSourceData() {
        Source.SourceData.Request().apply {
            context = this@SourceActivity
            interactor.fetchSourceData(this)
        }
    }

    fun fetchDetailData(position: Int) {
        Source.DetailData.Request().apply {
            context = this@SourceActivity
            this.position = position
            interactor.fetchDetailData(this)
        }
    }

    private fun configViews() {
        val layoutManager = GridLayoutManager(this@SourceActivity, 2)
        sourceRecyclerView.apply {
            this.layoutManager = layoutManager
            this.adapter = SourceAdapter {
                // TODO Click action
            }
        }

        // Header:
        configToolbar(toolbar, false, true)
        // Footer:
        configBottomNavigation(this@SourceActivity, bottomNavigation)
    }

    override fun displaySourceData(viewModel: Source.SourceData.ViewModel) {
        if (viewModel.viewModels.isEmpty()) {
            sourceRecyclerView.visibility = View.GONE
            emptyTextView.visibility = View.VISIBLE
        } else {
            sourceRecyclerView.visibility = View.VISIBLE
            emptyTextView.visibility = View.GONE
            (sourceRecyclerView.adapter as SourceAdapter).updateData(viewModel.viewModels)
        }
    }

    override fun displayError(errMsg: String) {
        longSnackbar(rootLayout, errMsg)
    }

    override fun displayProress() {
        configLoadingProgress(loadingView, true)
    }

    override fun dismissProgress() {
        configLoadingProgress(loadingView, false)
    }
}