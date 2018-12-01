package jessehj.newssample.scene.headline

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class HeadlineInteractorUnitTest {

    @Test
    fun fetchHeadlineData_with_validInput_shouldCall_presentHeadlineData() {
        // Given
        val interactor = HeadlineInteractor()
        val presenterSpy = HeadlinePresenterSpy()
        interactor.presenter = presenterSpy

        // When
        Headline.HeadlineData.Request().apply {
            // set request mock data...
            interactor.fetchHeadlineData(this)
        }

        // Then
        Assert.assertTrue(
            "When the valid input is passed to HeadlineInteractor "
                    + "Then presentHeadlineData should be called",
            presenterSpy.presentHeadlineDataIsCalled
        )
    }

    private inner class HeadlinePresenterSpy : HeadlinePresentationLogic {

        internal var presentHeadlineDataIsCalled = false
        internal lateinit var responseCopy: Headline.HeadlineData.Response

        override fun presentHeadlineData(response: Headline.HeadlineData.Response) {
            presentHeadlineDataIsCalled = true
            responseCopy = response
        }
    }
}