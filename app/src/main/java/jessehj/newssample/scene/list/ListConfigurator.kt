package jessehj.newssample.scene.list

import java.lang.ref.WeakReference


object ListConfigurator {

    fun configure(activity: ListActivity) {

        val router = ListRouter()
        router.activity = WeakReference(activity)

        val presenter = ListPresenter()
        presenter.activity = WeakReference(activity)

        val interactor = ListInteractor()
        interactor.presenter = presenter

        activity.interactor = interactor
        activity.router = router
        activity.router.dataStore = interactor
    }
}