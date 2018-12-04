package jessehj.newssample.scene.detail

import android.content.Context
import jessehj.newssample.R


class Detail {

    class DetailData {

        class Request {
            lateinit var context: Context
        }

        class Response {
            lateinit var context: Context
            var srcId: String? = null
            var srcName: String? = null
            var urlToImage: String? = null
            var author: String? = null
            var title: String? = null
            var content: String? = null
            var publishedAt: String? = null

        }

        class ViewModel {
            var srcImgPath: Int = R.drawable.news_placeholder
            var srcName: String? = null
            var imgUrl: String? = null
            var author: String? = null
            var title: String? = null
            var content: String? = null
            var date: String? = null
        }

    }
}
