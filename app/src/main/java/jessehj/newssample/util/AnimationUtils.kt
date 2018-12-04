package jessehj.newssample.util

import android.view.animation.AlphaAnimation
import android.view.animation.Animation

/**
 * Created by jessehj on 04/12/2018.
 */

object AnimationUtils {

    fun fadeInAnimation(duration: Int): Animation {
        return AlphaAnimation(0f, 1f).apply {
            this.duration = duration.toLong()
        }
    }

    fun fadeOutAnimation(duration: Int): Animation {
        return AlphaAnimation(1f, 0f).apply {
            this.duration = duration.toLong()
        }
    }
}