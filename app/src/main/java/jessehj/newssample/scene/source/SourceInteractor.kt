package jessehj.newssample.scene.source

import jessehj.newssample.base.AppManager
import jessehj.newssample.entity.filter.SourceFilter
import jessehj.newssample.network.apis.SourceAPI
import jessehj.newssample.util.ModelUtils


interface SourceBusinessLogic : SourceDataPassing, SourceDataStore {
    fun fetchSourceData(request: Source.SourceData.Request)
    fun fetchFilterData(request: Source.FilterData.Request)
    fun fetchDetailData(request: Source.DetailData.Request)
}

interface SourceDataPassing {
    // fun setPassedData(Obj: Any)
}

interface SourceDataStore {
    // fun getData(): Any
}

class SourceInteractor : SourceBusinessLogic {

    lateinit var presenter: SourcePresentationLogic
    private var worker = SourceWorker()
    private var sourceFilter = SourceFilter()
    private var sources = mutableListOf<jessehj.newssample.entity.source.Source>()

    override fun fetchSourceData(request: Source.SourceData.Request) {
        presenter.presentProgress()

        SourceAPI.loadSources(sourceFilter, object: SourceAPI.SourcesCompletion {
            override fun onSuccess(sources: MutableList<jessehj.newssample.entity.source.Source>) {
                this@SourceInteractor.sources = sources

                Source.SourceData.Response().apply {
                    context = request.context
                    this.sources = sources
                    presenter.presentSourceData(this)
                }
                presenter.dismissProgress()
            }

            override fun onError(error: Error) {
                presenter.dismissProgress()
                presenter.presentError(error.localizedMessage)
            }
        })
    }

    override fun fetchFilterData(request: Source.FilterData.Request) {
        val filterJson = AppManager.getSourceFilter(request.context)
        sourceFilter = ModelUtils.parseJson<SourceFilter>(filterJson)?.let { it } ?: SourceFilter()
    }

    override fun fetchDetailData(request: Source.DetailData.Request) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}