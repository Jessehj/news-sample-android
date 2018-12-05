package jessehj.newssample.scene.headline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import jessehj.newssample.R
import jessehj.newssample.base.AppConstants
import jessehj.newssample.scene.BaseActivity
import jessehj.newssample.scene.dialog.FilterDialogFragment
import jessehj.newssample.view.adapter.ArticleAdapter
import jessehj.newssample.view.listener.PaginationListener
import jessehj.newssample.view.listener.SimpleTextWatcher
import kotlinx.android.synthetic.main.activity_headline.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.noAnimation

fun Context.headlineIntent(): Intent {
    return intentFor<HeadlineActivity>().noAnimation()
}

interface HeadlineDisplayLogic {
    fun displayHeadlineData(viewModel: Headline.HeadlineData.ViewModel)
    fun displayProgress()
    fun dismissProgress()
    fun setPagingEnabled(enabled: Boolean)
    fun refreshComplete()
    fun displayError(errMsg: String)
    fun routeToArticleDetail()
    fun displayFilterDialog(viewModel: Headline.OpenFilter.ViewModel)
}

class HeadlineActivity : BaseActivity(), HeadlineDisplayLogic,
    FilterDialogFragment.OnFragmentInteractionListener {

    companion object {
        const val TAG_FILTER_DIALOG = "tagHeadlineDialog"
    }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.category_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_category -> {
                fetchFilterDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun fetchFilterData(
        category: AppConstants.Category? = null,
        country: AppConstants.Country? = null
    ) {
        Headline.FilterData.Request().apply {
            context = this@HeadlineActivity
            category?.let { this.category = it }
            country?.let { this.country = it }
            interactor.fetchFilterData(this)
        }
    }

    fun fetchHeadlineData(refresh: Boolean) {
        Headline.HeadlineData.Request().apply {
            context = this@HeadlineActivity
            this.refresh = refresh
            this.keyword = searchEditText.text.toString()
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

    fun fetchFilterDialog() {
        Headline.OpenFilter.Request().apply {
            context = this@HeadlineActivity
            interactor.fetchFilterDialog(this)
        }
    }

    private fun configViews() {
        displayProgress()

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
            this.adapter = ArticleAdapter(AppConstants.ArticleType.Headline) {
                fetchDetailData(it)
            }
        }

        searchEditText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(inputted: String) {
                fetchHeadlineData(true)
            }
        })

        // Header:
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

    override fun displayProgress() {
        configLoadingProgress(loadingView, true)
    }

    override fun dismissProgress() {
        configLoadingProgress(loadingView, false)
    }

    override fun displayFilterDialog(viewModel: Headline.OpenFilter.ViewModel) {
        FilterDialogFragment.newInstance(viewModel.category, viewModel.country).apply {
            if (!this.isAdded) {
                supportFragmentManager.beginTransaction()
                    .add(this, TAG_FILTER_DIALOG)
                    .commitAllowingStateLoss()
            }
        }
    }

    // FilterDialogFragment.OnFragmentInteractionListener:
    override fun onDismissDialog(category: AppConstants.Category?, country: AppConstants.Country) {
        fetchFilterData(category, country)
        fetchHeadlineData(true)
    }
}