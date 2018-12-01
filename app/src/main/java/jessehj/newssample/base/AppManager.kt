package jessehj.newssample.base

import android.content.Context
import android.preference.PreferenceManager
import jessehj.newssample.BuildConfig

/**
 * Created by jessehj on 02/12/2018.
 */

object AppManager {
    private const val ARTICLE_FILTER = "article_filter_${BuildConfig.API_VERSION}"
    private const val SOURCE_FILTER = "source_filter_${BuildConfig.API_VERSION}"

    fun getArticleFilter(context: Context): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return sharedPreferences.getString(ARTICLE_FILTER, "")!!
    }

    fun setArticleFilter(context: Context, filterJson: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        sharedPreferences.edit().putString(ARTICLE_FILTER, filterJson).apply()
    }
}

