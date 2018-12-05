package jessehj.newssample.view.listener

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by jessehj on 02/12/2018.
 */

abstract class PaginationListener(private val layoutManager: RecyclerView.LayoutManager) :
    RecyclerView.OnScrollListener() {

    private var isScrollToTheEnd: Boolean = false
    var pagingEnabled = true
    var direction = PagingDirection.Bottom

    enum class PagingDirection { Top, Bottom }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        checkScrollToTheEnd(layoutManager, direction) {
            isScrollToTheEnd = it
        }

        if (isScrollToTheEnd && pagingEnabled && recyclerView.layoutManager!!.canScrollVertically()) {
            onLoadMore()
        }
    }

    private fun checkScrollToTheEnd(
        layoutManager: RecyclerView.LayoutManager,
        direction: PagingDirection,
        onScrolledLast: (Boolean) -> Unit
    ) {
        val firstVisibleItemPosition = when (layoutManager) {
            is LinearLayoutManager -> layoutManager.findFirstVisibleItemPosition()
            is GridLayoutManager -> layoutManager.findFirstVisibleItemPosition()
            else -> 0
        }

        val isScrolledLast = when (direction) {
            PagingDirection.Bottom -> {
                isReachBottom(
                    layoutManager.itemCount,
                    layoutManager.childCount,
                    firstVisibleItemPosition
                )
            }
            PagingDirection.Top -> {
                isReachTop((layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition())
            }
        }

        onScrolledLast(isScrolledLast)
    }

    private fun isReachBottom(
        itemCount: Int,
        childCount: Int,
        firstVisibleItemPosition: Int
    ): Boolean = itemCount > 0 && firstVisibleItemPosition + childCount >= itemCount

    private fun isReachTop(firstCompletelyVisibleItemPosition: Int): Boolean =
        firstCompletelyVisibleItemPosition == 0

    abstract fun onLoadMore()
}