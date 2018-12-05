package jessehj.newssample.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import jessehj.newssample.base.AppConstants
import jessehj.newssample.util.ModelUtils
import retrofit2.Response

/**
 * Created by jessehj on 05/12/2018.
 */

class ResError {
    constructor()
    constructor(message: String) {
        this.message = message
    }

    var code: Int = -1
    var message: String = ""
    var errParam: ErrParam? = null

    companion object {
        fun parseError(response: Response<*>): ResError {
            val error = ResError()
            error.code = response.code()
            error.message = response.message()

            response.errorBody()?.let {
                val result = ModelUtils.parseJson<ErrParam>(it.string())
                if (result != null) {
                    error.errParam = result
                }
            }
            return error
        }
    }
}

class ErrParam {
    @SerializedName(AppConstants.Commons.status)
    @Expose
    var status: String? = null
    @SerializedName(AppConstants.Commons.code)
    @Expose
    var code: String? = null
    @SerializedName(AppConstants.Commons.message)
    var message: String? = null
}