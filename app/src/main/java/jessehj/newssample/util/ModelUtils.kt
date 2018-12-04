package jessehj.newssample.util

import android.content.Context
import android.webkit.URLUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jessehj.newssample.R
import jessehj.newssample.base.AppConstants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by jessehj on 01/12/2018.
 */


object ModelUtils {
    inline fun <reified T> parseJson(json: String): T? =
        Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)

    fun pastTime(context: Context, seconds: Int): String? {
        val secs = seconds % 60
        val mins = (seconds / 60) % 60
        val hours = (seconds / 60 / 60) % 24
        val days = seconds / (60 * 60 * 24)

        return when {
            days > 0 -> null
            hours > 0 -> String.format(
                Locale.KOREA,
                context.getString(R.string.format_hours_ago),
                hours
            )
            mins > 0 -> String.format(
                Locale.KOREA,
                context.getString(R.string.format_mins_ago),
                mins
            )
            else -> context.getString(R.string.title_moment_ago)
        }
    }


    fun convertStringToDate(dateFormat: String, dateString: String): Date? {
        return try {
            SimpleDateFormat(dateFormat, Locale.KOREA).parse(dateString)
        } catch (e: ParseException) {
            e.printStackTrace()
            null
        }
    }

    fun convertDateToString(dateFormat: String, date: Date): String =
        SimpleDateFormat(dateFormat, Locale.KOREA).format(date)

    fun sourceImageMapper(id: String?): Int {
        return when (id) {
            "abc-news" -> R.drawable.abc_news
            "abc-news-au" -> R.drawable.abc_news_au
            "aftenposten" -> R.drawable.aftenposten
            "al-jazeera-english" -> R.drawable.al_jazeera_english
            "ansa" -> R.drawable.ansa
            "argaam" -> R.drawable.argaam
            "ars-technica" -> R.drawable.ars_technica
            "ary-news" -> R.drawable.ary_news
            "associated-press" -> R.drawable.associated_press
            "australian-financial-review" -> R.drawable.austrailan_financial_review
            "axios" -> R.drawable.axios
            "bbc-news" -> R.drawable.bbc_news
            "bbc-sport" -> R.drawable.bbc_sport
            "bild" -> R.drawable.bild
            "blasting-news-br" -> R.drawable.blasting_news_br
            "bleacher-report" -> R.drawable.bleacher_report
            "bloomberg" -> R.drawable.bloomberg
            "breitbart-news" -> R.drawable.breitbart_news
            "business-insider" -> R.drawable.business_insider
            "business-insider-uk" -> R.drawable.business_insider_uk
            "buzzfeed" -> R.drawable.buzzfeed
            "cbc-news" -> R.drawable.cbc_news
            "cbs-news" -> R.drawable.cbs_news
            "cnbc" -> R.drawable.cnbc
            "cnn" -> R.drawable.cnn
            "cnn-es" -> R.drawable.cnn_es
            "crypto-coins-news" -> R.drawable.crypto_coins_news
            "daily-mail" -> R.drawable.daily_mail
            "der-tagesspiegel" -> R.drawable.der_tagesspiegel
            "die-zeit" -> R.drawable.die_zeit
            "el-mundo" -> R.drawable.el_mundo
            "engadget" -> R.drawable.engadget
            "entertainment-weekly" -> R.drawable.entertainment_weekly
            "espn" -> R.drawable.espn
            "espn-cric-info" -> R.drawable.espn_cric_info
            "financial-post" -> R.drawable.finanical_post
            "financial-times" -> R.drawable.financial_times
            "focus" -> R.drawable.focus
            "football-italia" -> R.drawable.football_italia
            "fortune" -> R.drawable.fortune
            "four-four-two" -> R.drawable.four_fout_two
            "fox-news" -> R.drawable.fox_news
            "fox-sports" -> R.drawable.fox_sports
            "globo" -> R.drawable.globo
            "google-news",
            "google-news-ar",
            "google-news-au",
            "google-news-br",
            "google-news-ca",
            "google-news-fr",
            "google-news-in",
            "google-news-is",
            "google-news-it",
            "google-news-ru",
            "google-news-sa",
            "google-news-uk" -> R.drawable.google_news
            "goteborgs-posten" -> R.drawable.goteborgs_posten
            "gruenderszene" -> R.drawable.gruenderszene
            "hacker-news" -> R.drawable.hacker_news
            "handelsblatt" -> R.drawable.handelsblatt
            "ign" -> R.drawable.ign
            "il-sole-24-ore" -> R.drawable.il_sole_24_ore
            "independent" -> R.drawable.independent
            "infobae" -> R.drawable.infobae
            "info-money" -> R.drawable.info_money
            "la-gaceta" -> R.drawable.la_gaceta
            "la-nacion" -> R.drawable.la_nacion
            "la-repubblica" -> R.drawable.la_repubblica
            "le-monde" -> R.drawable.le_monde
            "lenta" -> R.drawable.lenta
            "lequipe" -> R.drawable.lequipe
            "les-echos" -> R.drawable.les_echos
            "liberation" -> R.drawable.liberation
            "marca" -> R.drawable.marca
            "mashable" -> R.drawable.mashable
            "medical-news-today" -> R.drawable.medical_news_today
            "metro" -> R.drawable.metro
            "mirror" -> R.drawable.mirror
            "msnbc" -> R.drawable.msnbc
            "mtv-news" -> R.drawable.mtv_news
            "mtv-news-uk" -> R.drawable.mtv_news_uk
            "national-geographic" -> R.drawable.national_geographic
            "national-review" -> R.drawable.national_review
            "nbc-news" -> R.drawable.nbc_news
            "new-scientist" -> R.drawable.new_scientist
            "news-com-au" -> R.drawable.news_com_au
            "newsweek" -> R.drawable.newsweek
            "new-york-magazine" -> R.drawable.new_york_magazine
            "next-big-future" -> R.drawable.next_big_future
            "nfl-news" -> R.drawable.nfl_news
            "nhl-news" -> R.drawable.nhl_news
            "nrk" -> R.drawable.nrk
            "politico" -> R.drawable.politico
            "polygon" -> R.drawable.polygon
            "rbc" -> R.drawable.rbc
            "recode" -> R.drawable.recode
            "reddit-r-all" -> R.drawable.reddit_r_all
            "reuters" -> R.drawable.reuters
            "rt" -> R.drawable.rt
            "rte" -> R.drawable.rte
            "rtl-nieuws" -> R.drawable.rtl_nieuws
            "sabq" -> R.drawable.sabq
            "spiegel-online" -> R.drawable.spiegel_online
            "svenska-dagbladet" -> R.drawable.svenska_dagbladet
            "t3n" -> R.drawable.t3n
            "talksport" -> R.drawable.talksport
            "techcrunch" -> R.drawable.techcrunch
            "techcrunch-cn" -> R.drawable.techcrunch_cn
            "techradar" -> R.drawable.techradear
            "the-american-conservative" -> R.drawable.the_american_conservative
            "the-economist" -> R.drawable.the_economist
            "the-globe-and-mail" -> R.drawable.the_globe_and_mail
            "the-guardian-au" -> R.drawable.the_guardian_au
            "the-guardian-uk" -> R.drawable.the_guardian_uk
            "the-hill" -> R.drawable.the_hill
            "the-hindu" -> R.drawable.the_hindu
            "the-huffington-post" -> R.drawable.the_huffington_post
            "the-irish-times" -> R.drawable.the_irish_times
            "the-jerusalem-post" -> R.drawable.the_jerusalem_post
            "the-lad-bible" -> R.drawable.the_lad_bible
            "the-new-york-times" -> R.drawable.the_new_york_times
            "the-next-web" -> R.drawable.the_next_web
            "the-sport-bible" -> R.drawable.the_sport_bible
            "the-telegraph" -> R.drawable.the_telegraph
            "the-times-of-india" -> R.drawable.the_times_of_india
            "the-verge" -> R.drawable.the_verge
            "the-wall-street-journal" -> R.drawable.the_wall_street_journal
            "the-washington-post" -> R.drawable.the_washington_post
            "the-washington-times" -> R.drawable.the_washington_times
            "time" -> R.drawable.time
            "usa-today" -> R.drawable.usa_today
            "vice-news" -> R.drawable.vice_news
            "wired" -> R.drawable.wired
            "wired-de" -> R.drawable.wired_de
            "wirtschafts-woche" -> R.drawable.wirtschafts_woche
            "xinhua-net" -> R.drawable.xinhua_net
            "ynet" -> R.drawable.ynet
            else -> R.drawable.news_placeholder
        }
    }

    fun validUrl(url: String?): String? {
        url?.let {
            if (URLUtil.isValidUrl(it)) {
                return it
            }
        }
        return null
    }

    fun authorComb(srcName: String?, author: String?): String {

        var result = srcName?.let { it } ?: ""

        author?.let {
            if (result.isEmpty()) {
                result = it
            } else {
                result += " - $it"
            }
        }

        return result
    }

    fun articleDate(context: Context, dateString: String?): String {
        dateString?.let {
            val date = convertStringToDate(AppConstants.Date.FORMAT_DEFAULT, it)
            date?.let { date ->
                val dateSecs: Long = date.time / 1000
                val curSecs: Long = System.currentTimeMillis() / 1000
                val pastSecs = (curSecs - dateSecs)

                return ModelUtils.pastTime(context, pastSecs.toInt())?.let { result ->
                    result
                } ?: run {
                    ModelUtils.convertDateToString(AppConstants.Date.FORMAT_SIMPLE, date)
                }
            }
        }
        return ""
    }

}