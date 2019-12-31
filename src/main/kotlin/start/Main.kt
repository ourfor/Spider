import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import top.ourfor.encoding.json.stringify
import top.ourfor.io.write
import top.ourfor.net.http.download
import java.io.File

fun main() = runBlocking {
    val spots = ArrayList<Spot>()
    coroutineScope {
        val userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36"
        for(i in 1..100){
            launch {
                val url = "https://you.ctrip.com/sightlist/suzhou11/s0-p$i.html"
                val doc = Jsoup.connect(url).header("User-Agent",userAgent).get()
                val lis = doc.select(".list_wide_mod2>.list_mod2")
                for(li in lis) {
                    val pic= li.select(".leftimg>a>img").attr("src")
                    val item = li.select(".rdetailbox .sight + a")
                    val href = item.attr("href")
                    val name = item.attr("title")
                    val comment = li.select(".bottomcomment").text()
                    var desc = ""
                    if(comment!="") {
                        val author = li.select(".sightc").text()
                        desc = comment.substring(author.length)
                    }
                    println("$name $desc $pic $href")
                    download(pic,"/Users/catalina/Desktop/spot/$name.jpg")
                    spots.add(Spot(href,name,pic,desc))
                }
            }
        }
    }

    write(File("/Users/catalina/Desktop/spot/asia.json"), stringify(spots))
}

data class Spot (
        val href: String,
        val name: String,
        val pic: String,
        val desc: String
)
