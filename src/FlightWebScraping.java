/**
 * cit591 final project
 * team 8
 * by VP, IW, CY
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
  String scrapedFileName = "scraped_data.txt";
  String parsedFileName = "parsed_data.txt";
  int debugMode = 0;
  
  /**
   * 
   * @param url
   */
  public FlightWebScraping(String url) {
    this.url = url;
    this.debugMode = 0;
  }

  /**
   * 
   * @param url
   */
  public FlightWebScraping(String url, int debugMode) {
    this.url = url;
    this.debugMode = debugMode;
  }

  
  /**
   * 
   */
  public void HtmlScrapingParsing() {
    System.out.println("running...");


    if (debugMode == 0) {
      try {
        // start a url connection
        Connection connection = Jsoup.connect(this.url);

        /*
         * user debug String strText = Jsoup.connect("http://www.useragentstring.com") .get()
         * .text(); System.out.println(strText);
         */

        // set user agent to Mozilla
        connection.userAgent("Mozilla/5.0");
        // accept cookie
        connection.cookie("auth", "token");
        // set timeout to 10 seconds
        connection.timeout(10 * 1000);
        // Create an html Document object from the given url.
        this.document = connection.get();
        /*
         * user debug String strText =
         * Jsoup.connect("http://www.google.com/").userAgent("Mozilla/5.0") .get() .text();
         * System.out.println(strText);
         */
        String title = document.title(); // Get title
        System.out.println("Title: " + title); // Print title.

        FileWriter fin = new FileWriter(inputHtmlFileName);
        BufferedWriter html = new BufferedWriter(fin);
        html.write(document.toString());
        fin.close();
        
        HtmlScraping();
        System.out.println("Scraping complete.");
        
        HtmlParsing();
        System.out.println("Parsing complete.");


      } catch (IOException ioe) {
        System.out.println("Exception: " + ioe);
        // ioe.printStackTrace();
      }
    }

    else if (debugMode == 1) {
      
      HtmlScraping();
      System.out.println("Parsing complete.");

    }

    else if (debugMode == 2) {
      
      HtmlParsing();
      System.out.println("Parsing complete.");

    }
  }
  
  /**
   * 
   * @param fileName
   */
  public void HtmlScraping() {
    
    FileWriter fout;
    
    try {
    
      fout = new FileWriter(scrapedFileName);

      BufferedWriter scraped = new BufferedWriter(fout);

      Elements divs = document.select("div.Base-Results-HorizonResult");

      for (Element div : divs) {
        
        String ariaLabel = div.attr("aria-label");
        String entry = (ariaLabel + ',' + div.text());
        scraped.append(entry + "\n");
        
      }
      
      fout.close();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * 
   * @param fileName
   */
  public void HtmlParsing() {
    
    FileReader fin;
    FileWriter fout;
    
    try {
      
      fin = new FileReader(scrapedFileName);
      
      fout = new FileWriter(parsedFileName);
      
      BufferedWriter parsed = new BufferedWriter(fout);

      parsed.append("rank|price|site|leg1|departure flight|departure flight details|"
          + "stops|departure flight duration|leg2|return flight|return flight details|"
          + "stops|return flight duration|\n");

      Scanner input = new Scanner(fin);
      String entry = null;
      
      while(input.hasNextLine()) {
        
        entry = input.nextLine().replaceAll("Result number |: |from |\\.[ ,]| Go to ", "|");
        String[] cells = entry.split("\\|");
        //|2|$1371 |Kiwi.com|Leg 1|Provided by Multiple Airlines
        //|Departs |Vancouver, BC, Canada - Vancouver Intl on 4/27 at 2:00 am, lands in Beijing, China - Capital on 4/28 at 12:35 pm
        //|1 stop (TPE)|Total duration|19h 35m|Leg 2|Provided by Multiple Airlines|Departs |Beijing, China - Capital on 5/27 at 9:30 am, lands in Vancouver, BC, Canada - Vancouver Intl on 5/27 at 7:40 pm
        //|2 stops (CKG, TPE)|Total duration|25h 10m.Operated by EVA Air

        for (int i = 0; i < cells.length ; i++) {
          if(i != 0 && i != 4 && i != 6 && i != 9 && i != 11 && i != 13 && i <= 15) {
            parsed.append(cells[i]+"|");
          }
        }
        
        parsed.append("\n");
        
      }
      
      input.close();
      parsed.close();
      
    } catch (FileNotFoundException e) {
      
      e.printStackTrace();
      
    } catch (IOException e) {
      
      e.printStackTrace();
      
    }
  }
}

