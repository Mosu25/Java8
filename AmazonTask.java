package java8;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

public class AmazonTask {
	
	RemoteWebDriver driver;
	
  @Test
  public void beforeJava8() {
	  
		driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();

		driver.get("https://www.amazon.in/");
		List<WebElement> list = driver.findElements(By.xpath("//a"));

		List<String> text = new ArrayList<>();

		for (WebElement webElement : list) {
			String temp = webElement.getText();
			if(!temp.isBlank()) {
				text.add(temp);
			}
		}
		
		// Set<String> removeDuplicates = new HashSet<String>(text);
		List<String> listAfterRemovingDuplicates = new ArrayList<>( new HashSet<String>(text));
		Collections.sort(listAfterRemovingDuplicates);
		
		for (String list2 : listAfterRemovingDuplicates) {
			if (list2.startsWith("C") || list2.startsWith("D")) {
				System.out.println(list2);
			}
		}
		
		driver.quit();
  }
  
  @Test
  public void java8() {
	  
	  	driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		driver.findElements(By.xpath("//a")).stream()
				.map(e->e.getText())
				.distinct()
				.sorted()
				.filter(s->s.startsWith("C")||s.startsWith("D"))
				.forEach(s->System.out.println(s));
		driver.quit();
    }
 }
