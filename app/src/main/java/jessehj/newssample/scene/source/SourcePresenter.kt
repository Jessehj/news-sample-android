package jessehj.newssample.scene.source

import jessehj.newssample.entity.source.SourceViewModel
import java.lang.ref.WeakReference


interface SourcePresentationLogic {
    fun presentSourceData(response: Source.SourceData.Response)
    fun presentProgress()
    fun dismissProgress()
    fun presentError(errMsg: String)
}

class SourcePresenter : SourcePresentationLogic {

    lateinit var activity: WeakReference<SourceDisplayLogic>
    private var sourceViewModels = mutableListOf<SourceViewModel>()

    override fun presentSourceData(response: Source.SourceData.Response) {
        val viewModels = mutableListOf<SourceViewModel>()
        response.sources.forEach {
            viewModels.add(SourceViewModel(response.context, it))
        }
        sourceViewModels = viewModels

        Source.SourceData.ViewModel().apply {
            this.viewModels = sourceViewModels
            activity.get()?.displaySourceData(this)
        }
    }

    override fun presentProgress() {
        activity.get()?.displayProress()
    }

    override fun dismissProgress() {
        activity.get()?.dismissProgress()
    }

    override fun presentError(errMsg: String) {
        activity.get()?.displayError(errMsg)
    }
}