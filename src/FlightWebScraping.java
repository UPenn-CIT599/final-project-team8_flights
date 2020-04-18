import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * This FlightWebScraping class takes url address to scrape useful 
 * information and parse them to the wanted format for data processing.
 * 
 * @author cit591 Spring 2020 team8
 *
 */
public class FlightWebScraping {
  
  String url = null;
  Document document;
  String inputHtmlFileName = "newFlight.html";
  String scrapedFileName = "scrapedData.txt";
  String parsedFileName = "parsedData.txt";
  String inputHtmlFileNameTest = "newFlightTest.html";
  String scrapedFileNameTest = "scrapedDataTest.txt";
  String parsedFileNameTest = "parsedDataTest.txt";

  int debugMode = 0;
  
  /**
   * constructor
   * initialize url and set debug mode default to 0 
   * if no debugMode argument is passed
   * 
   * @param url
   */
  public FlightWebScraping(String url) {
    this(url, 0);
  }

  /**
   * constructor
   * initialize url and debug mode. Overloading FlightWebScraping constructor.
   * 
   * @param url
   * @param debugMode
   */
  public FlightWebScraping(String url, int debugMode) {
    this.url = url;
    this.debugMode = debugMode;
    
    if (debugMode == 1)
      //this the file for htmlParsing method testing
      this.inputHtmlFileName = this.inputHtmlFileNameTest;
      this.scrapedFileName = this.scrapedFileNameTest;
    
    if (debugMode == 2)
      //this the file for htmlParsing method testing
      this.scrapedFileName = this.scrapedFileNameTest;
      this.parsedFileName = this.parsedFileNameTest;

  }

  
  /**
   * setup flight url connection using JSoup and stores the flight html file. 
   * Call htmlScraping method to scrape useful flight info, and 
   * call htmlParsing method to parse the scraped info into the target data format.
   * 
   * flight html file, scraped data, and parsed data are stored in three files.
   * 
   */
  public void htmlScrapingParsing() {
    System.out.println("normal mode start.");


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
        
        // add delay for page to load
        try {
          
          TimeUnit.SECONDS.sleep(2);
          System.out.println("loading page...");
          TimeUnit.SECONDS.sleep(14);
          
        } catch (InterruptedException ie) {
          Thread.currentThread().interrupt();
        }
        
        // Create an html Document object from the given url.
        this.document = connection.get();
        
        /*
         * user debug String strText =
         * Jsoup.connect("http://www.google.com/").userAgent("Mozilla/5.0") .get() .text();
         * System.out.println(strText);
         */
        String title = document.title(); // Get title
        System.out.println("Title: " + title); // Print title.
        
        // store flight html file for debug test
        FileWriter htmlFile = new FileWriter(inputHtmlFileName);
        BufferedWriter html = new BufferedWriter(htmlFile);
        html.write(document.toString());
        htmlFile.close();
        
        htmlScraping();
        htmlParsing();

      } catch (IOException ioe) {
        System.out.println("Exception: " + ioe);
        // ioe.printStackTrace();
      }
    }

    /* html scraping debug mode
     * this is only executed when debugMode is set to 1, assuming
     * flight html file is already present, ie normal mode was ran once.
     */
    else if (debugMode == 1) {
      
      htmlScraping();
    }
    
    /*
     * html parsing debug mode
     * this is only executed when debugMode is set to 2, assuming
     * scraped data file is already present, ie normal mode was ran once
     */
    else if (debugMode == 2) {
      
      htmlParsing();
    }
  }
  
  /**
   * use JSoup Element and Element.select methods to find elements in interest
   * from the flight html file and store them to the scraped_data.txt.
   * 
   */
  public void htmlScraping() {
    
    File fin;
    FileWriter fout;
    
    try {
      
      fin = new File(inputHtmlFileName);
      Document document = Jsoup.parse(fin, "UTF-8");
      
      fout = new FileWriter(scrapedFileName);
      BufferedWriter scraped = new BufferedWriter(fout);

      Elements divs = document.select("div.Base-Results-HorizonResult");

      for (Element div : divs) {
        
        String ariaLabel = div.attr("aria-label");
        String entry = (ariaLabel + ',' + div.text());
        scraped.append(entry + "\n");
        
      }
      
      System.out.println("Scraping complete.");
      fout.close();

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * From scraped_data.txt, parse each row's data and store them into this format: 
   * "rank|price|site|leg1|departure flight|departure flight details|
   * stops|departure flight duration|leg2|return flight|return flight details|
   * stops|return flight duration|". Every row values are separated by "|" for 
   * data processing 
   */
  public void htmlParsing() {
    
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

      System.out.println("Parsing complete.");

      input.close();
      parsed.close();
      
    } catch (FileNotFoundException e) {
      
      e.printStackTrace();
      
    } catch (IOException e) {
      
      e.printStackTrace();
      
    }
  }
}