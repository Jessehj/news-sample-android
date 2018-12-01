package jessehj.newssample

/**
 * Created by jessehj on 01/12/2018.
 */


object AppConstants {

    object Commons {
        const val q = "q"
        const val page = "page"
    }

    object Headers {
        const val X_API_KEY = "X-Api-Key"
        const val CONTENT_TYPE = "Content-Type"
        const val APPLICATION_JSON = "application/json"
    }

    object Article {
        const val articles = "articles"
        const val author = "author"
        const val title = "title"
        const val description = "description"
        const val url = "url"
        const val urlToImage = "urlToImage"
        const val publishedAt = "publishedAt"
        const val content = "content"
    }

    object Source {
        const val uri = "sources"
        const val sources = "sources"
        const val id = "id"
        const val name = "name"
        const val description = "description"
        const val url = "url"
        const val category = "category"
        const val language = "language"
        const val country = "country"
    }

    object Headline {
        const val uri = "top-headlines"
        const val category = "category"
        const val country = "country"
    }



    object CountryCode {
        const val kr = "kr"
        const val us = "us"
    }

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