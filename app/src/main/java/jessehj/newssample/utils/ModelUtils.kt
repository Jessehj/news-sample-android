package jessehj.newssample.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by jessehj on 01/12/2018.
 */


object ModelUtils {
    inline fun <reified T> parseJson(json: String): T = Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)
}