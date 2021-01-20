package scraping;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Scraping {

    public static void main(String[] args) {

        // try-catch文が必要
        try {

            // Document A = Jsoup.connect("url").get(); urlにスクレイピング対象
            Document doc = Jsoup.connect("https://www.yahoo.co.jp/").get();

            // Elements B = A.select("タグ"); この形でソースに含まれるタグで指定された範囲を書き出す。
            // #=id, .=class
            Elements elm = doc.select("._3cl937Zpn1ce8mDKd5kp7u");

            // 拡張for文
            for (Element elms : elm) {
                System.out.println(elms.text()); // 結果 Yahoo!JAPAN
            }

            // 例外処理
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
