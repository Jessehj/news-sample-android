package jessehj.newssample.scene.source

import java.lang.ref.WeakReference


object SourceConfigurator {

    fun configure(activity: SourceActivity) {

        val router = SourceRouter()
        router.activity = WeakReference(activity)

        val presenter = SourcePresenter()
        presenter.activity = WeakReference(activity)

        val interactor = SourceInteractor()
        interactor.presenter = presenter

        activity.interactor = interactor
        activity.router = router
        activity.router.dataStore = interactor
    }
}