/**
 * cit591 final project
 * team 8
 * by VP, IW, CY
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Connection;
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
      //start a url connection
      Connection connection = Jsoup.connect(this.url);

      /*user debug
       * String strText = Jsoup.connect("http://www.useragentstring.com")
          .get()
          .text();
      System.out.println(strText);
      */

      //set user agent to Mozilla
      connection.userAgent("Mozilla/5.0");
      //accept cookie
      connection.cookie("auth", "token");
      //set timeout to 10 seconds
      connection.timeout(10 * 1000);
      //Create an html Document object from the given url.
      this.document = connection.get();
      /*user debug
       * String strText = Jsoup.connect("http://www.google.com/").userAgent("Mozilla/5.0")
          .get()
          .text();
      System.out.println(strText);
      */
      String title = document.title(); //Get title
      System.out.println("Title: " + title); //Print title.

      FileWriter fIn = new FileWriter(inputHtmlFileName);  
      BufferedWriter html = new BufferedWriter(fIn);      
      html.write(document.toString());
      fIn.close();
      
      fIn = new FileWriter(scrapedCsvFileName);
      BufferedWriter csv = new BufferedWriter(fIn);      
      Elements divs = document.select("div.Base-Results-HorizonResult");
      
      csv.append("rank,price,departure flight duration,departure flight stops,"
          + "departure details,arrival flight duration,arrival flight stops,"
          + "arrival details");
      
      for (Element div : divs) {
        String ariaLabel = div.attr("aria-label");
        String entry = (ariaLabel + ',' + div.text());
        String parsedEntry = HtmlParsing(entry);
        
        csv.append(parsedEntry + "\n");
      }
      fIn.close();

      System.out.println("Scraping complete.");
      
    } catch (IOException ioe) {
      System.out.println("Exception: " + ioe);
      //ioe.printStackTrace();
    }
  }
  
  /**
   * 
   * @param fileName
   */
  public String HtmlParsing(String entry) {
    entry = entry.replace(".", "|");
    String[] parsedEntry = entry.split("\\|");
    
    for(String item : parsedEntry) {
      System.out.println(item);
    }
    return entry;
  }
}

