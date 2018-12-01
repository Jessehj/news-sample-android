package jessehj.newssample.scene.headline

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class HeadlinePresenterUnitTest {

    @Test
    fun presentHeadlineData_with_validInput_shouldCall_displayHeadlineData() {
        // Given
        val presenter = HeadlinePresenter()
        val activitySpy = HeadlineActivitySpy()
        presenter.activity = WeakReference(activitySpy)

        // When
        Headline.HeadlineData.Response().apply {
            // set response mock data...
            presenter.presentHeadlineData(this)
        }

        // Then
        Assert.assertTrue(
            "When the valid input is passed to HeadlinePresenter "
                    + "Then displayHeadlineData should be called",
            activitySpy.displayHeadlineDataIsCalled
        )
    }

    private inner class HeadlineActivitySpy : HeadlineDisplayLogic {

        internal var displayHeadlineDataIsCalled = false
        internal lateinit var viewModelCopy: Headline.HeadlineData.ViewModel

        override fun displayHeadlineData(viewModel: Headline.HeadlineData.ViewModel) {
            displayHeadlineDataIsCalled = true
            viewModelCopy = viewModel
        }
    }
}