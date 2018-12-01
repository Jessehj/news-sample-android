package jessehj.newssample.scene.source

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


@RunWith(RobolectricTestRunner::class)
class SourceActivityUnitTest {

    @Test
    fun onCreate_shouldCall_fetchSourceData() {
        // Given
        val interactorSpy = SourceInteractorSpy()

        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchData to test our condition
        val activity = SourceActivity()
        activity.interactor = interactorSpy

        // When
        activity.fetchSourceData()

        // Then
        Assert.assertTrue(interactorSpy.fetchSourceDataIsCalled)
    }

    @Test
    fun onCreate_Calls_fetchSourceData_withCorrectData() {
        // Given
        val interactorSpy = SourceInteractorSpy()
        val activity = SourceActivity()
        activity.interactor = interactorSpy

        // When
        activity.fetchSourceData()

        // Then
        // Assert.assertNotNull(interactorSpy.requestCopy.context)
    }

    private inner class SourceInteractorSpy : SourceBusinessLogic {

        internal var fetchSourceDataIsCalled = false
        internal lateinit var requestCopy: Source.SourceData.Request

        override fun fetchSourceData(request: Source.SourceData.Request) {
            fetchSourceDataIsCalled = true
            requestCopy = request
        }
    }
}