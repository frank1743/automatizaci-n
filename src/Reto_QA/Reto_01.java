package Reto_QA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.List;
//import com.sun.tools.javac.util.List;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class Reto_01 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\geckodriver.exe");
		//WebDriver driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver","C:\\chromedriver\\chromedriver.exe");

	    WebDriver driver =  new ChromeDriver();   
	    driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
	    //INGRESAR A EBAY
	    driver.get("https://pe.ebay.com");
	    driver.manage().window().maximize();
	    
	    //BUSCAR ZAPATOS
	    driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("zapatos");
	    driver.findElement(By.xpath("//input[@id='gh-btn']")).click();
	    
	    //MARCA PUMA
	    driver.findElement(By.xpath("//div[@id='x-refine__group_1__1']//li[7]//div[1]//a[1]//div[1]//input[1]")).click();
	    
	    //TALLA 10
	    driver.findElement(By.xpath("//div[@id='x-refine__group_1__0']//li[5]//div[1]//a[1]//div[1]//input[1]")).click();
	        
	    WebElement TxtBoxContent = driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[1]/div/div[2]/div/div[1]/h1")); 
	   
	    //NÚMERO DE RESULTADOS
	    System.out.println("Filtros aplicados marca PUMA y talla 10: "+TxtBoxContent.getText());
	    
	    //ORDERNAR POR PRECIO ASC	    
	    Actions builder = new Actions(driver); 
	    WebElement element = driver.findElement(By.xpath("//div[@class='srp-controls--selected-value']")); 
	    builder.moveToElement(element).build().perform(); 
	    driver.findElement(By.xpath("//span[contains(text(),'Precio + Envío: más bajo primero')]")).click();
	    
	    //ELEGIR LOS 5 PRIMEROS	    
	    List theList = new ArrayList<>();
	    
	    for(int i=1; i <= 5; i++)
        {
           WebElement linkElement = driver.findElement(By.xpath("//li[@id='srp-river-results-listing"+i+"']"));
            
            System.out.println(i+".- "+ linkElement.getText()); 
            theList.add(linkElement.getText());

        }
	    
	    Collections.sort(theList);
	    //IMPRIMIR POR NOMBRE
        System.out.println("Nombres ordenados en forma ascendente:");
        showList(theList);      
        
        List theList01 = new ArrayList<>();
	    
	    for(int i=1; i <= 5; i++)
        {
                     
	    	driver.findElement(By.xpath("//li[@id='srp-river-results-listing"+i+"']")).click();
	    	
	    	WebElement linkElement01 = driver.findElement(By.xpath("//span[@id='convbidPrice']"));
           // System.out.println(i+".- "+ linkElement01.getText()); 
            theList01.add(linkElement01.getText());
            driver.navigate().back();
            
            Thread.sleep(6000);
            
        }
	    
	    Collections.sort(theList01, Collections.reverseOrder());
	    
	    //IMPRIMIR POR PRECIO
        System.out.println("Precios ordenados forma descendente:");
        showList01(theList01);  
        
	}

	 private static void showList(List theList) {
	        int size = theList.size();
	        for(int i=0; i<size; i++){
	            System.out.println(theList.get(i));
	        }
	    }
	
	 private static void showList01(List theList01) {
	        int size = theList01.size();
	        for(int i=0; i<size; i++){
	            System.out.println(theList01.get(i));
	        }
	    }
	 
	
}
