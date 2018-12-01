package jessehj.newssample.scene.headline

import java.lang.ref.WeakReference


interface HeadlinePresentationLogic {
    fun presentHeadlineData(response: Headline.HeadlineData.Response)
}

class HeadlinePresenter : HeadlinePresentationLogic {

    lateinit var activity: WeakReference<HeadlineDisplayLogic>

    override fun presentHeadlineData(response: Headline.HeadlineData.Response) {

    }
}