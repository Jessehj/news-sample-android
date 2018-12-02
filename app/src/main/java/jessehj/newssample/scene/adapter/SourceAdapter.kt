package jessehj.newssample.scene.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jessehj.newssample.R
import jessehj.newssample.entity.source.SourceViewModel
import kotlinx.android.synthetic.main.list_item_source.view.*

/**
 * Created by jessehj on 02/12/2018.
 */

class SourceAdapter(private val onItemClick: (Int) -> Unit) :
    RecyclerView.Adapter<SourceAdapter.ViewHolder>() {

    private var itemViewModels = mutableListOf<SourceViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_source, parent, false)
        )
    }

    override fun getItemCount(): Int = itemViewModels.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val viewModel = itemViewModels[position]
        holder.itemView.apply {
            RequestOptions().apply {
                placeholder(R.drawable.news_placeholder)
                    .error(R.drawable.news_placeholder)

                Glide.with(context).setDefaultRequestOptions(this)
                    .load(viewModel.imgPath).into(sourceImageView)
            }

            nameTextView.text = viewModel.name
            contentTextView.text = viewModel.content
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                onItemClick(adapterPosition)
            }
        }
    }

    fun updateData(viewModels: MutableList<SourceViewModel>) {
        this.itemViewModels = viewModels
        this.notifyDataSetChanged()
    }
}