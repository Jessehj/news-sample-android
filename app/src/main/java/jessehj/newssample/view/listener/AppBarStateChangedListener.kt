package jessehj.newssample.view.listener

import android.support.design.widget.AppBarLayout

/**
 * Created by jessehj on 05/12/2018.
 */

abstract class AppBarStateChangedListener : AppBarLayout.OnOffsetChangedListener {

    private var curState = State.Idle

    enum class State {
        Expanded,
        Collapsed,
        Idle
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, i: Int) {
        when {
            i == 0 -> {
                if (curState != State.Expanded) {
                    onStateChanged(appBarLayout,
                        State.Expanded
                    )
                }
                curState =
                        State.Expanded
            }
            Math.abs(i) >= appBarLayout.totalScrollRange -> {
                if (curState != State.Collapsed) {
                    onStateChanged(appBarLayout,
                        State.Collapsed
                    )
                }
                curState =
                        State.Collapsed
            }
            else -> {
                if (curState != State.Idle) {
                    onStateChanged(appBarLayout,
                        State.Idle
                    )
                }
                curState = State.Idle
            }
        }
    }

    abstract fun onStateChanged(appBarLayout: AppBarLayout, state: State)
}