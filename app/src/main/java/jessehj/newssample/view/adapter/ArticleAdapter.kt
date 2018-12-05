package jessehj.newssample.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import jessehj.newssample.R
import jessehj.newssample.base.AppConstants
import jessehj.newssample.entity.article.ArticleViewModel
import kotlinx.android.synthetic.main.list_item_article.view.*
import kotlinx.android.synthetic.main.list_item_article_simple.view.*

/**
 * Created by jessehj on 02/12/2018.
 */

class ArticleAdapter(
    private val listType: AppConstants.ArticleType,
    private val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var itemViewModels = mutableListOf<ArticleViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (listType) {
            AppConstants.ArticleType.Headline -> {
                HeadlineViewHolder(inflater.inflate(R.layout.list_item_article, parent, false))
            }
            else -> { // AppConstants.ArticleType.Simple
                SimpleViewHolder(inflater.inflate(R.layout.list_item_article_simple, parent, false))
            }
        }
    }

    override fun getItemCount(): Int = itemViewModels.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewModel = itemViewModels[position]

        when (holder) {
            is HeadlineViewHolder -> fillHeadlineInfo(holder.itemView, viewModel)
            is SimpleViewHolder -> fillSimpleInfo(holder.itemView, viewModel)
        }
    }

    inner class HeadlineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    inner class SimpleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    fun updateData(viewModels: MutableList<ArticleViewModel>) {
        this.itemViewModels = viewModels
        this.notifyDataSetChanged()
    }

    private fun fillHeadlineInfo(view: View, viewModel: ArticleViewModel) {
        view.apply {
            viewModel.imgUrl?.let {
                articleImageView.visibility = View.VISIBLE

                Picasso.get().load(it)
                    .placeholder(R.drawable.news_placeholder)
                    .error(R.drawable.news_placeholder)
                    .into(articleImageView)


            } ?: run {
                articleImageView.visibility = View.GONE
            }

            authorTextView.text = viewModel.author
            dateTextView.text = viewModel.date
            titleTextView.text = viewModel.title

            viewModel.content?.let {
                contentTextView.visibility = View.VISIBLE
                contentTextView.text = it
            } ?: run {
                contentTextView.visibility = View.GONE
            }
        }
    }

    private fun fillSimpleInfo(view: View, viewModel: ArticleViewModel) {
        view.apply {
            viewModel.imgUrl?.let {
                Picasso.get().load(it)
                    .placeholder(R.drawable.news_placeholder)
                    .error(R.drawable.news_placeholder)
                    .into(articleImageViewSmpl)
            }

            Picasso.get().load(viewModel.srcImgPath)
                .placeholder(R.drawable.news_placeholder)
                .error(R.drawable.news_placeholder)
                .into(sourceImageViewSmpl)

            authorTextViewSmpl.text = viewModel.srcName
            dateTextViewSmpl.text = viewModel.date
            titleTextViewSmpl.text = viewModel.title
        }
    }

}