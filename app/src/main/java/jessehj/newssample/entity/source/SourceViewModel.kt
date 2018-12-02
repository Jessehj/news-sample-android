package jessehj.newssample.entity.source

import android.content.Context
import jessehj.newssample.util.ModelUtils

/**
 * Created by jessehj on 02/12/2018.
 */

class SourceViewModel(context: Context, source: Source) {

    val imgPath = ModelUtils.sourceImageMapper(source.id)
    val name = source.name
    val content = source.description
}