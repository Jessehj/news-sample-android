package jessehj.newssample.scene.detail

import jessehj.newssample.util.ModelUtils
import java.lang.ref.WeakReference


interface DetailPresentationLogic {
    fun presentDetailData(response: Detail.DetailData.Response)
}

class DetailPresenter : DetailPresentationLogic {

    lateinit var activity: WeakReference<DetailDisplayLogic>

    override fun presentDetailData(response: Detail.DetailData.Response) {
        Detail.DetailData.ViewModel().apply {
            srcImgPath = ModelUtils.sourceImageMapper(response.srcId)
            srcName = response.srcName
            imgUrl = ModelUtils.validUrl(response.urlToImage)
            response.author?.let {
                author = "\'${response.author}\'"
            }
            title = response.title
            content = response.content
            date = ModelUtils.articleDate(response.context, response.publishedAt)
            activity.get()?.displayDetailData(this)
        }
    }
}