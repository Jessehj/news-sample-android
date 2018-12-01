package jessehj.newssample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jessehj.newssample.entity.headline.Headline
import jessehj.newssample.entity.headline.HeadlineApi
import jessehj.newssample.entity.headline.HeadlineFilter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = HeadlineFilter(
            null,
            AppConstants.Headline.Country.Korea,
            null
        )
        HeadlineApi.findAll(1, filter, object: HeadlineApi.findAllCompletion {
            override fun onSuccess(headlines: MutableList<Headline>) {

            }

            override fun onError(error: Error) {

            }
        })
    }
}
