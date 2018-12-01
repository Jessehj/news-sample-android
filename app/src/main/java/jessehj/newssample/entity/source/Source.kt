package jessehj.newssample.entity.source

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import jessehj.newssample.AppConstants

/**
 * Created by jessehj on 01/12/2018.
 */


class Source {
    @SerializedName(AppConstants.Source.id)
    @Expose
    private val id: String? = null
    @SerializedName(AppConstants.Source.name)
    @Expose
    private val name: String? = null
    @SerializedName(AppConstants.Source.description)
    @Expose
    private val description: String? = null
    @SerializedName(AppConstants.Source.url)
    @Expose
    private val url: String? = null
    @SerializedName(AppConstants.Source.category)
    @Expose
    private val category: String? = null
    @SerializedName(AppConstants.Source.language)
    @Expose
    private val language: String? = null
    @SerializedName(AppConstants.Source.country)
    @Expose
    private val country: String? = null

}