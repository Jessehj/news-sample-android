package jessehj.newssample.util

import jessehj.newssample.R
import jessehj.newssample.base.AppConstants
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.*

/**
 * Created by jessehj on 06/12/2018.
 */

@Config(
    manifest = "src/main/AndroidManifest.xml",
    packageName = "jessehj.newssample"
)
@RunWith(RobolectricTestRunner::class)
class ModelUtilsTest {

    @Test
    fun modelUtils_articleDate_test() {
        val context = RuntimeEnvironment.application
        val date = ModelUtils.articleDate(context, "2018-12-05T17:14:00Z")
        Assert.assertNotNull(date)
    }

    @Test
    fun modelUtils_pastTime_test() {
        val context = RuntimeEnvironment.application
        val past = ModelUtils.pastTime(context, 36841)
        Assert.assertNotNull(past)
    }

    @Test
    fun modelUtils_convertStringToDate() {
        val dateFormat = AppConstants.Date.FORMAT_DEFAULT

        val result = ModelUtils.convertStringToDate(dateFormat, "2018-12-05T17:14:00Z")
        Assert.assertNotNull(result)

        val result2 = ModelUtils.convertStringToDate(dateFormat, "flaskjdasd")
        Assert.assertNull(result2)

        val result3 = ModelUtils.convertStringToDate(dateFormat, "")
        Assert.assertNull(result3)
    }

    @Test
    fun modelUtils_convertDateToString() {
        val dateFormat = AppConstants.Date.FORMAT_DEFAULT
        val result = ModelUtils.convertDateToString(dateFormat, Date())
        Assert.assertNotNull(result)
    }

    @Test
    fun modelUtils_sourceImageMapper_with_validId() {
        val result = ModelUtils.sourceImageMapper("abc-news")
        Assert.assertTrue(result == R.drawable.abc_news)
    }

    @Test
    fun modelUtils_sourceImageMapper_with_unknownId() {
        val result = ModelUtils.sourceImageMapper("aasdfs")
        Assert.assertTrue(result == R.drawable.news_placeholder)
    }

    @Test
    fun modelUtils_validUrl() {
        val result = ModelUtils.validUrl("http://kakao.com")
        Assert.assertNotNull(result)
    }

    @Test
    fun modelUtils_validUrl_with_nullUrl() {
        val result = ModelUtils.validUrl(null)
        Assert.assertNull(result)
    }

    @Test
    fun modelUtils_validUrl_with_emptyUrl() {
        val result = ModelUtils.validUrl("")
        Assert.assertNull(result)
    }


}
