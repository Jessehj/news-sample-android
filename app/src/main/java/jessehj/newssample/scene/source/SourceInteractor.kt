package jessehj.newssample.scene.source


interface SourceBusinessLogic : SourceDataPassing, SourceDataStore {
    fun fetchSourceData(request: Source.SourceData.Request)
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

    override fun fetchSourceData(request: Source.SourceData.Request) {

    }
}