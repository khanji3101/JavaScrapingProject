package scraping;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * スクレイピング動作確認クラス
 *
 * @author k-hanji
 *
 */
public class Scraping {

    // 取得対象URL
    private static final String TARGET_URL = "https://www.yahoo.co.jp/";
    // ファイル保存先(環境に応じて要変更)
    private static final String SAVE_SPACE = "src/main/resources/csv/";
    // ファイル名用日付設定
    private static final String TODAY = toLocalDateTime(LocalDateTime.now());
    // 改行
    private static final String NEW_LINE = "\r\n";

    /**
     * スクレイピング実践
     *
     * @param args
     */
    public static void main(String[] args) {

        try (FileWriter fileWriter = new FileWriter(SAVE_SPACE + "HeadLine_" + TODAY + ".csv")) {

            // 取得対象のURLをセットし、HTMLを取得
            Document doc = Jsoup.connect(TARGET_URL).get();

            // 取得したいタグにセットされているHTMLの属性をセットし、データを取得
            // #=id, .=class
            Elements elm = doc.select("._3cl937Zpn1ce8mDKd5kp7u");

            // 取得した結果をCSVに出力
            for (Element elms : elm) {
                System.out.println(elms.text());
                fileWriter.append(elms.text()); // ニュースの見出し
                fileWriter.append(NEW_LINE);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 日付フォーマット設定
     *
     * @param date 変換する日付
     * @return フォーマットにて変換した日付
     */
    public static String toLocalDateTime(LocalDateTime date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return date.format(dtf).toString();
    }

}
