package jessehj.newssample.entity.source

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import jessehj.newssample.base.AppConstants

/**
 * Created by jessehj on 01/12/2018.
 */


class Source {
    @SerializedName(AppConstants.Source.id)
    @Expose
    var id: String? = null
    @SerializedName(AppConstants.Source.name)
    @Expose
    var name: String? = null
    @SerializedName(AppConstants.Source.description)
    @Expose
    var description: String? = null
    @SerializedName(AppConstants.Source.url)
    @Expose
    var url: String? = null
    @SerializedName(AppConstants.Source.category)
    @Expose
    var category: String? = null
    @SerializedName(AppConstants.Source.language)
    @Expose
    var language: String? = null
    @SerializedName(AppConstants.Source.country)
    @Expose
    var country: String? = null

}