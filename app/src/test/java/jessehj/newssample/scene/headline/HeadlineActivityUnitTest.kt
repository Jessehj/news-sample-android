package jessehj.newssample.scene.headline

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class HeadlineActivityUnitTest {

    @Test
    fun onCreate_shouldCall_fetchHeadlineData() {
        // Given
        val interactorSpy = HeadlineInteractorSpy()

        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchData to test our condition
        val activity = HeadlineActivity()
        activity.interactor = interactorSpy

        // When
        activity.fetchHeadlineData()

        // Then
        Assert.assertTrue(interactorSpy.fetchHeadlineDataIsCalled)
    }

    @Test
    fun onCreate_Calls_fetchHeadlineData_withCorrectData() {
        // Given
        val interactorSpy = HeadlineInteractorSpy()
        val activity = HeadlineActivity()
        activity.interactor = interactorSpy

        // When
        activity.fetchHeadlineData()

        // Then
        // Assert.assertNotNull(interactorSpy.requestCopy.context)
    }

    private inner class HeadlineInteractorSpy : HeadlineBusinessLogic {

        internal var fetchHeadlineDataIsCalled = false
        internal lateinit var requestCopy: Headline.HeadlineData.Request

        override fun fetchHeadlineData(request: Headline.HeadlineData.Request) {
            fetchHeadlineDataIsCalled = true
            requestCopy = request
        }
    }
}