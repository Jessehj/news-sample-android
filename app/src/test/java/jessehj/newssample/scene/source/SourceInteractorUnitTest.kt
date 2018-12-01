package jessehj.newssample.scene.source

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class SourceInteractorUnitTest {

    @Test
    fun fetchSourceData_with_validInput_shouldCall_presentSourceData() {
        // Given
        val interactor = SourceInteractor()
        val presenterSpy = SourcePresenterSpy()
        interactor.presenter = presenterSpy

        // When
        Source.SourceData.Request().apply {
            // set request mock data...
            interactor.fetchSourceData(this)
        }

        // Then
        Assert.assertTrue(
            "When the valid input is passed to SourceInteractor "
                    + "Then presentSourceData should be called",
            presenterSpy.presentSourceDataIsCalled
        )
    }

    private inner class SourcePresenterSpy : SourcePresentationLogic {

        internal var presentSourceDataIsCalled = false
        internal lateinit var responseCopy: Source.SourceData.Response

        override fun presentSourceData(response: Source.SourceData.Response) {
            presentSourceDataIsCalled = true
            responseCopy = response
        }
    }
}