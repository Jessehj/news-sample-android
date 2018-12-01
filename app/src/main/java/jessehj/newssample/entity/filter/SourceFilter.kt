package jessehj.newssample.entity.filter

import jessehj.newssample.base.AppConstants

/**
 * Created by jessehj on 01/12/2018.
 */

data class SourceFilter(
    var category: AppConstants.Category? = null,
    var country: AppConstants.Country? = null
)