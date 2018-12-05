package jessehj.newssample.view.custom

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import jessehj.newssample.util.ModelUtils

/**
 * Created by jessehj on 05/12/2018.
 */


class RecyclerViewSpaceDeco : RecyclerView.ItemDecoration {

    private var context: Context
    private var top = -1
    private var bottom = -1
    private var left = -1
    private var right = -1

    constructor(context: Context, top: Int, bottom: Int, left: Int, right: Int) : super() {
        this.context = context
        this.top = ModelUtils.dp2px(context, top)
        this.bottom = ModelUtils.dp2px(context, bottom)
        this.left = ModelUtils.dp2px(context, left)
        this.right = ModelUtils.dp2px(context, right)
    }

    constructor(context: Context, bottom: Int) : super() {
        this.context = context
        this.bottom = ModelUtils.dp2px(context, bottom)
    }

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (top != -1 && position != 0) {
            outRect.top = top
        }
        if (bottom != -1 && position != state.itemCount - 1) {
            outRect.bottom = bottom
        }
        if (left > 0) {
            outRect.left = left
        }
        if (right > 0) {
            outRect.right = right
        }
    }
}
