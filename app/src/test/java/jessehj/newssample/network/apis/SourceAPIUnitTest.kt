package jessehj.newssample.network.apis

import jessehj.newssample.base.AppConstants
import jessehj.newssample.entity.filter.SourceFilter
import jessehj.newssample.entity.source.Source
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Created by jessehj on 01/12/2018.
 */

@Config(
    manifest = "src/main/AndroidManifest.xml",
    packageName = "jessehj.newssample"
)
@RunWith(RobolectricTestRunner::class)
class SourceAPIUnitTest {
    @Test
    fun sourceAPI_loadAllSources() {
        val filter = SourceFilter(
            null,
            null
        )
        SourceAPI.loadSources(filter, object : SourceAPI.SourcesCompletion{
            override fun onSuccess(sources: MutableList<Source>) {
                Assert.assertNotNull(sources)
            }

            override fun onError(error: Error) {

            }
        })
    }

    @Test
    fun sourceAPI_loadSource_dataNotFound_shouldReturn_emptyList() {
        val filter = SourceFilter(
            AppConstants.Category.Science,
            AppConstants.Country.Korea
        )
        SourceAPI.loadSources(filter, object : SourceAPI.SourcesCompletion{
            override fun onSuccess(sources: MutableList<Source>) {
                Assert.assertTrue(sources.isEmpty())
            }

            override fun onError(error: Error) {

            }
        })
    }
}