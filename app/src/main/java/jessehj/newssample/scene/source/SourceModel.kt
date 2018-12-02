package jessehj.newssample.scene.source

import android.content.Context
import jessehj.newssample.entity.source.Source
import jessehj.newssample.entity.source.SourceViewModel


class Source {

    class SourceData {

        class Request {
            lateinit var context: Context
        }

        class Response {
            lateinit var context: Context
            var sources = mutableListOf<Source>()
        }

        class ViewModel {
            var viewModels = mutableListOf<SourceViewModel>()
        }
    }

    class FilterData {
        class Request {
            lateinit var context: Context
        }
    }

    class DetailData {
        class Request {
            lateinit var context: Context
            var position: Int = -1
        }
    }
}
