package jessehj.newssample.scene.headline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import jessehj.newssample.R
import jessehj.newssample.scene.BaseActivity
import jessehj.newssample.scene.adapter.ArticleAdapter
import jessehj.newssample.util.PaginationListener
import kotlinx.android.synthetic.main.activity_headline.*
import kotlinx.android.synthetic.main.view_toolbar_layout.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

fun Context.headlineIntent(): Intent {
    return intentFor<HeadlineActivity>().noAnimation()
}

interface HeadlineDisplayLogic {
    fun displayHeadlineData(viewModel: Headline.HeadlineData.ViewModel)
    fun setPagingEnabled(enabled: Boolean)
    fun refreshComplete()
    fun displayError(errMsg: String)
    fun routeToArticleDetail()
}

class HeadlineActivity : BaseActivity(), HeadlineDisplayLogic {

    lateinit var interactor: HeadlineBusinessLogic
    lateinit var router: HeadlineRouter
    private lateinit var paginationListener: PaginationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HeadlineConfigurator.configure(this)
        setContentView(R.layout.activity_headline)

        configViews()

        fetchFilterData()
        fetchHeadlineData(true)
    }

    override fun onStart() {
        super.onStart()
        updateBottomMenu(this@HeadlineActivity, bottomNavigation)
    }

    fun fetchFilterData() {
        Headline.FilterData.Request().apply {
            context = this@HeadlineActivity
            interactor.fetchFilterData(this)
        }
    }

    fun fetchHeadlineData(refresh: Boolean) {
        Headline.HeadlineData.Request().apply {
            context = this@HeadlineActivity
            this.refresh = refresh
            interactor.fetchHeadlineData(this)
        }
    }

    fun fetchDetailData(position: Int) {
        Headline.DetailData.Request().apply {
            context = this@HeadlineActivity
            this.position = position
            interactor.fetchDetailData(this)
        }
    }

    private fun configViews() {
        val layoutManager = LinearLayoutManager(this@HeadlineActivity)
        paginationListener = object : PaginationListener(layoutManager) {
            override fun onLoadMore() {
                fetchHeadlineData(false)
            }
        }

        refreshLayout.setOnRefreshListener {
            fetchHeadlineData(true)
        }

        articleRecyclerView.apply {
            this.layoutManager = layoutManager
            this.addOnScrollListener(paginationListener)
            this.adapter = ArticleAdapter {
                fetchDetailData(it)
            }
        }

        // Header:
        toolbar.setTitle(R.string.title_headlines)
        configToolbar(toolbar, false, true)
        // Footer:
        configBottomNavigation(this@HeadlineActivity, bottomNavigation)
    }

    override fun displayHeadlineData(viewModel: Headline.HeadlineData.ViewModel) {
        if (viewModel.viewModels.isEmpty()) {
            articleRecyclerView.visibility = View.GONE
            emptyTextView.visibility = View.VISIBLE
        } else {
            articleRecyclerView.visibility = View.VISIBLE
            emptyTextView.visibility = View.GONE
            (articleRecyclerView.adapter as ArticleAdapter).updateData(viewModel.viewModels)
        }
    }

    override fun setPagingEnabled(enabled: Boolean) {
        paginationListener.pagingEnabled = enabled
    }

    override fun refreshComplete() {
        refreshLayout.isRefreshing = false
    }

    override fun displayError(errMsg: String) {
        longSnackbar(rootLayout, errMsg)
    }

    override fun routeToArticleDetail() {
        router.navigateToArticleDetail()
    }
}