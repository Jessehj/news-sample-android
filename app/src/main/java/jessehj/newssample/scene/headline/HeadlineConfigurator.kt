package jessehj.newssample.scene.headline

import java.lang.ref.WeakReference


object HeadlineConfigurator {

    fun configure(activity: HeadlineActivity) {

        val router = HeadlineRouter()
        router.activity = WeakReference(activity)

        val presenter = HeadlinePresenter()
        presenter.activity = WeakReference(activity)

        val interactor = HeadlineInteractor()
        interactor.presenter = presenter

        activity.interactor = interactor
        activity.router = router
        activity.router.dataStore = interactor
    }
}