package jessehj.newssample.scene.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import jessehj.newssample.R
import jessehj.newssample.base.AppConstants
import jessehj.newssample.scene.BaseActivity
import jessehj.newssample.view.adapter.ArticleAdapter
import jessehj.newssample.view.custom.RecyclerViewSpaceDeco
import jessehj.newssample.view.listener.PaginationListener
import jessehj.newssample.view.listener.SimpleTextWatcher
import kotlinx.android.synthetic.main.activity_list.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.intentFor

const val EXTRA_SOURCE_ID = "extraSourceId"

fun Context.listIntent(sourceId: String?): Intent {
    return intentFor<ListActivity>(
        EXTRA_SOURCE_ID to sourceId
    )
}

interface ListDisplayLogic {
    fun displayListData(viewModel: List.ListData.ViewModel)
    fun setPagingEnabled(enabled: Boolean)
    fun refreshComplete()
    fun displayError(errMsg: String)
    fun routeToArticleDetail()
    fun displayProgress()
    fun dismissProgress()
}

class ListActivity : BaseActivity(), ListDisplayLogic {

    lateinit var interactor: ListBusinessLogic
    lateinit var router: ListRouter
    private lateinit var paginationListener: PaginationListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ListConfigurator.configure(this)
        setContentView(R.layout.activity_list)

        getIntentData()
        configViews()
        fetchFilterData()
        fetchListData(true)
    }

    fun getIntentData() {
        if (intent.hasExtra(EXTRA_SOURCE_ID)) {
            val sourceId: String = intent.getStringExtra(EXTRA_SOURCE_ID)
            interactor.passSourceId(sourceId)
        }
    }

    fun configViews() {
        displayProgress()

        val layoutManager = LinearLayoutManager(this@ListActivity)
        paginationListener = object : PaginationListener(layoutManager) {
            override fun onLoadMore() {
                fetchListData(false)
            }
        }

        refreshLayout.setOnRefreshListener {
            fetchListData(true)
        }

        articleRecyclerView.apply {
            this.layoutManager = layoutManager
            this.addOnScrollListener(paginationListener)
            this.addItemDecoration(RecyclerViewSpaceDeco(this@ListActivity, 2))
            this.adapter = ArticleAdapter(AppConstants.ArticleType.Simple) {
                fetchDetailData(it)
            }
        }

        searchEditText.addTextChangedListener(object : SimpleTextWatcher() {
            override fun onTextChanged(inputted: String) {
                fetchListData(true)
            }
        })
        configToolbar(toolbar, true, true)
    }

    fun fetchFilterData() {
        List.FilterData.Request().apply {
            context = this@ListActivity
            interactor.fetchFilterData(this)
        }
    }

    fun fetchListData(refresh: Boolean) {
        List.ListData.Request().apply {
            context = this@ListActivity
            this.refresh = refresh
            this.keyword = searchEditText.text.toString()
            interactor.fetchListData(this)
        }
    }

    fun fetchDetailData(position: Int) {
        List.DetailData.Request().apply {
            context = this@ListActivity
            this.position = position
            interactor.fetchDetailData(this)
        }
    }

    override fun displayListData(viewModel: List.ListData.ViewModel) {
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
}