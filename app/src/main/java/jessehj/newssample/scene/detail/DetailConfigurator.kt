package jessehj.newssample.scene.detail

import java.lang.ref.WeakReference


object DetailConfigurator {

    fun configure(activity: DetailActivity) {

        val router = DetailRouter()
        router.activity = WeakReference(activity)

        val presenter = DetailPresenter()
        presenter.activity = WeakReference(activity)

        val interactor = DetailInteractor()
        interactor.presenter = presenter

        activity.interactor = interactor
        activity.router = router
        activity.router.dataStore = interactor
    }
}