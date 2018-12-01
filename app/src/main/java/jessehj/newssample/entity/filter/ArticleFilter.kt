package jessehj.newssample.entity.filter

import jessehj.newssample.AppConstants

/**
 * Created by jessehj on 01/12/2018.
 */

data class ArticleFilter(
        var category: AppConstants.Category? = null,
        var country: AppConstants.Country? = null,
        var q: String? = null
)