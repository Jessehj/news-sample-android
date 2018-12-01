package jessehj.newssample.scene.source

import java.lang.ref.WeakReference


interface SourcePresentationLogic {
    fun presentSourceData(response: Source.SourceData.Response)
}

class SourcePresenter : SourcePresentationLogic {

    lateinit var activity: WeakReference<SourceDisplayLogic>

    override fun presentSourceData(response: Source.SourceData.Response) {

    }
}