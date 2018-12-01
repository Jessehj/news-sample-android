package jessehj.newssample.scene.source

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.lang.ref.WeakReference


@RunWith(RobolectricTestRunner::class)
class SourcePresenterUnitTest {

    @Test
    fun presentSourceData_with_validInput_shouldCall_displaySourceData() {
        // Given
        val presenter = SourcePresenter()
        val activitySpy = SourceActivitySpy()
        presenter.activity = WeakReference(activitySpy)

        // When
        Source.SourceData.Response().apply {
            // set response mock data...
            presenter.presentSourceData(this)
        }

        // Then
        Assert.assertTrue(
            "When the valid input is passed to SourcePresenter "
                    + "Then displaySourceData should be called",
            activitySpy.displaySourceDataIsCalled
        )
    }

    private inner class SourceActivitySpy : SourceDisplayLogic {

        internal var displaySourceDataIsCalled = false
        internal lateinit var viewModelCopy: Source.SourceData.ViewModel

        override fun displaySourceData(viewModel: Source.SourceData.ViewModel) {
            displaySourceDataIsCalled = true
            viewModelCopy = viewModel
        }
    }
}