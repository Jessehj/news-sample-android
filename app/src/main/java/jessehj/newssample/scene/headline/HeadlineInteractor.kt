package jessehj.newssample.scene.headline


interface HeadlineBusinessLogic : HeadlineDataPassing, HeadlineDataStore {
    fun fetchHeadlineData(request: Headline.HeadlineData.Request)
}

interface HeadlineDataPassing {
    // fun setPassedData(Obj: Any)
}

interface HeadlineDataStore {
    // fun getData(): Any
}

class HeadlineInteractor : HeadlineBusinessLogic {

    lateinit var presenter: HeadlinePresentationLogic
    private var worker = HeadlineWorker()

    override fun fetchHeadlineData(request: Headline.HeadlineData.Request) {

    }
}