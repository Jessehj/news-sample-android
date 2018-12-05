package jessehj.newssample.base

/**
 * Created by jessehj on 01/12/2018.
 */


object AppConstants {

    object Commons {
        const val q = "q"
        const val page = "page"
        const val status = "status"
        const val code = "code"
        const val message = "message"

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
        const val source = "source"
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

    object Everything {
        const val uri = "everything"
        const val sources = "sources"
    }

    object CountryCode {
        const val kr = "kr"
        const val us = "us"
    }

    object Date {
        const val FORMAT_DEFAULT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val FORMAT_SIMPLE = "yyyy-MM-dd"
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

    enum class ArticleType {
        Headline,
        Simple
    }

}