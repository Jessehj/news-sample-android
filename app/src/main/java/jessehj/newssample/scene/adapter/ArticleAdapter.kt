package jessehj.newssample.scene.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jessehj.newssample.R
import jessehj.newssample.entity.article.ArticleViewModel
import kotlinx.android.synthetic.main.list_item_article.view.*

/**
 * Created by jessehj on 02/12/2018.
 */

class ArticleAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    private var itemViewModels = mutableListOf<ArticleViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_article, parent, false)
        )
    }

    override fun getItemCount(): Int = itemViewModels.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = itemViewModels[position]
        holder.itemView.apply {
            viewModel.imgUrl?.let {
                articleImageView.visibility = View.VISIBLE

                RequestOptions().apply {
                    placeholder(R.drawable.news_placeholder)
                        .error(R.drawable.news_placeholder)

                    Glide.with(context).setDefaultRequestOptions(this)
                        .load(it)
                        .into(articleImageView)
                }

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

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
}