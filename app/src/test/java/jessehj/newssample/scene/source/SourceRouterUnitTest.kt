package jessehj.newssample.scene.source

import android.content.Intent
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class SourceRouterUnitTest {

    @Test
    fun test_SourceRouter_navigateToNextScene_when_Input_Is() {
        // Given
        // Setup Data

        val router = SourceRouter()
        val activity = SourceActivity()
        activity.router = router
        router.activity = WeakReference(activity)

        // When
        val nextScene = router.navigateToSomewhere()

        // Then
        Assert.assertEquals(
            "When the some data passed to SourceRouter" +
                    " Then next scene should be ...",
            nextScene, Intent()
        )
    }
}