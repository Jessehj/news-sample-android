package jessehj.newssample.scene.headline

import android.content.Intent
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class HeadlineRouterUnitTest {

    @Test
    fun test_HeadlineRouter_navigateToNextScene_when_Input_Is() {
        // Given
        // Setup Data

        val router = HeadlineRouter()
        val activity = HeadlineActivity()
        activity.router = router
        router.activity = WeakReference(activity)

        // When
        val nextScene = router.navigateToSomewhere()

        // Then
        Assert.assertEquals(
            "When the some data passed to HeadlineRouter" +
                    " Then next scene should be ...",
            nextScene, Intent()
        )
    }
}