/**
 * cit591 final project
 * team 8
 * by VP, IW, CY
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Takes url and use JSoup library to start a web session.
 * Scrapes useful flight info and parse to CSV format
 * @author VP, IW, CY
 */
public class FlightWebScraping {
  String url = null;
  Document document;
  String inputHtmlFileName = "origin.html";
  String scrapedCsvFileName = "scraped.csv";
  String parsedJsonFileName = "parsed.json";
  
  /**
   * 
   * @param url
   */
  public FlightWebScraping(String url) {
    this.url = url;
  }
  
  /**
   * 
   */
  public void HtmlScraping() {
    System.out.println("running...");

    try {
      //Get Document object from given url.
      this.document = Jsoup.connect(this.url).get();
      String title = document.title(); //Get title
      System.out.println("Title: " + title); //Print title.

      FileWriter fIn = new FileWriter(inputHtmlFileName);  
      BufferedWriter html = new BufferedWriter(fIn);      
      html.write(document.toString());
      fIn.close();
      
      fIn = new FileWriter(scrapedCsvFileName);
      BufferedWriter csv = new BufferedWriter(fIn);      
      Elements divs = document.select("div.Base-Results-HorizonResult");
      for (Element div : divs) {
        String ariaLabel = div.attr("aria-label");
        csv.append(ariaLabel + ',' + div.text() + "\n");
      }
      fIn.close();

      System.out.println("Scraping complete.");
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 
   * @param fileName
   */
  public void HtmlParsing(String fileName) {
    File input = new File(fileName);
    try {
      Document doc = Jsoup.parse(input, "UTF-8", "");
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

