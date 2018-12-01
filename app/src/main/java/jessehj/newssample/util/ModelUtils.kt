package jessehj.newssample.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jessehj.newssample.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jessehj on 01/12/2018.
 */


object ModelUtils {
    inline fun <reified T> parseJson(json: String): T? =
        Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)

    fun pastTime(context: Context, seconds: Int): String? {
        val secs = seconds % 60
        val mins = (seconds / 60) % 60
        val hours = (seconds / 60 / 60) % 24
        val days = seconds / (60 * 60 * 24)

        return when {
            days > 0 -> null
            hours > 0 -> String.format(
                Locale.KOREA,
                context.getString(R.string.format_hours_ago),
                hours
            )
            mins > 0 -> String.format(
                Locale.KOREA,
                context.getString(R.string.format_mins_ago),
                mins
            )
            else -> context.getString(R.string.title_moment_ago)
        }
    }


    fun convertStringToDate(dateFormat: String, dateString: String): Date? {
        return try {
            SimpleDateFormat(dateFormat, Locale.KOREA).parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun convertDateToString(dateFormat: String, date: Date): String =
        SimpleDateFormat(dateFormat, Locale.KOREA).format(date)

}