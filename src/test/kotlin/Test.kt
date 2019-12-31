import org.jsoup.Jsoup
import top.ourfor.net.http.get

fun main() {
    val src = "https://file.ourfor.top/study/software_test.doc"
    val html = get("https://view.officeapps.live.com/op/embed.aspx?src=$src")
    val doc = Jsoup.parse(html);
    doc.head().append("<style>#application{border: 0 solid red}</style>")
    println(doc.html())
}