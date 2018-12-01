package jessehj.newssample

/**
 * Created by jessehj on 01/12/2018.
 */


class AppConstants {

    object Commons {
        const val q = "q"
        const val page = "page"
    }

    object Headers {
        const val X_API_KEY = "X-Api-Key"
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
    }

    object Headline {
        const val ENDPOINT = "/v2/top-headlines"
        const val category = "category"
        const val country = "country"
        const val sources = "sources"
        const val articles = "articles"

        enum class Category(val tag: String, val value: String) {
            Business("Business", "business"),
            Entertainment("Entertainment", "entertainment"),
            General("General", "general"),
            Health("Health", "health"),
            Science("Science", "science"),
            Sports("Sports", "sports"),
            Technology("Technology", "technology")
        }

        enum class Country(val tag: String, val value: String) {
            Korea("Korean", CountryCode.kr),
            USA("USA", CountryCode.us)
        }
    }

    object CountryCode {
        const val kr = "kr"
        const val us = "us"
    }

}