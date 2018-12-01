package jessehj.newssample.entity.headline

import jessehj.newssample.AppConstants

/**
 * Created by jessehj on 01/12/2018.
 */
 
data class HeadlineFilter(
    var category: AppConstants.Headline.Category? = null,
    var country: AppConstants.Headline.Country? = null,
    var q: String? = null
)