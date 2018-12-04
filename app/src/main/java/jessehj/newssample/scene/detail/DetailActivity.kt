package jessehj.newssample.scene.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.View
import com.squareup.picasso.Picasso
import jessehj.newssample.R
import jessehj.newssample.scene.BaseActivity
import jessehj.newssample.util.AnimationUtils
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.intentFor

const val EXTRA_ARTICLE_JSON = "extraArticleJson"

fun Context.detailIntent(articleJson: String): Intent {
    return intentFor<DetailActivity>(
        EXTRA_ARTICLE_JSON to articleJson
    )
}

interface DetailDisplayLogic {
    fun displayDetailData(viewModel: Detail.DetailData.ViewModel)
}

class DetailActivity : BaseActivity(), DetailDisplayLogic {

    lateinit var interactor: DetailBusinessLogic
    lateinit var router: DetailRouter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailConfigurator.configure(this)
        setContentView(R.layout.activity_detail)

        getIntentData()
        configViews()
        fetchDetailData()
    }

    fun getIntentData() {
        if (intent.hasExtra(EXTRA_ARTICLE_JSON)) {
            val articleJson: String = intent.getStringExtra(EXTRA_ARTICLE_JSON)
            interactor.passArticleJson(articleJson)
        }
    }

    fun configViews() {

        configToolbar(toolbar)
        toolbarAppBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) >= appBarLayout.totalScrollRange) {
                if (collapsedLayout.visibility == View.GONE) {
                    collapsedLayout.visibility = View.VISIBLE
                    collapsedLayout.startAnimation(AnimationUtils.fadeInAnimation(500))
                }
            } else {
                if (collapsedLayout.visibility == View.VISIBLE) {
                    collapsedLayout.visibility = View.GONE
                    collapsedLayout.startAnimation(AnimationUtils.fadeOutAnimation(500))
                }
            }
        })

        detailButton.setOnClickListener {
            router.navigateToOrigin()
        }

        sourceLayout.setOnClickListener {
            router.navigateToArticles()
        }

    }

    fun fetchDetailData() {
        Detail.DetailData.Request().apply {
            context = this@DetailActivity
            interactor.fetchDetailData(this)
        }
    }

    override fun displayDetailData(viewModel: Detail.DetailData.ViewModel) {
        if (viewModel.srcImgPath == R.drawable.news_placeholder) {
            sourceImageViewCollapsed.visibility = View.GONE
        } else {
            sourceImageViewCollapsed.visibility = View.VISIBLE
        }

        Picasso.get().load(viewModel.srcImgPath)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .into(sourceImageView)
        Picasso.get().load(viewModel.srcImgPath)
            .placeholder(R.drawable.news_placeholder)
            .error(R.drawable.news_placeholder)
            .into(sourceImageViewCollapsed)

        viewModel.srcName?.let {
            sourceTextView.text = it
            sourceTextViewCollapsed.text = it
        }
        viewModel.title?.let {
            titleTextView.text = it
        }
        viewModel.date?.let {
            dateTextView.text = it
        }
        viewModel.author?.let {
            authorTextView.visibility = View.VISIBLE
            authorTextView.text = it
        } ?: run { authorTextView.visibility = View.GONE }

        viewModel.imgUrl?.let {
            Picasso.get().load(it)
                .placeholder(R.drawable.news_placeholder)
                .error(R.drawable.news_placeholder)
                .into(articleImageView)
        }
        viewModel.content?.let {
            contentTextView.text = it
        }
    }
}