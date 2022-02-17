package com.resumebuilder.baseclass;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;



public class Baseclass {


	static WebDriver driver;
	static java.util.List<WebElement> row;



	public static List<WebElement> ElementNames ;
	public static int TotalResumes;
	public static ChromeOptions options ;
	//public static String list;
	public static String Xpath1;
	public static String Xpath2;
	public static String Var;


	public static String App = "./src/test/java/com/resumebuilder/pagelocators/RB0_App.properties";
	public static String LandingPage = "./src/test/java/com/resumebuilder/pagelocators/RB1_LandingPage.properties";
	public static String PreRequisite_Questions = "./src/test/java/com/resumebuilder/pagelocators/RB2_Pre_Requisite.properties";
	public static String Template = "./src/test/java/com/resumebuilder/pagelocators/RB3_Template.properties";
	public static String SelectTemplate = "./src/test/java/com/resumebuilder/pagelocators/RB4_SelectTemplate.properties";

	public static String ExcelData = "./src/main/java/com/resumebuilder/excel/ResumeBuilder.xlsx";

	public static String PropertyFile(String URL, String locatorfile ) throws Throwable {

		Properties Prop = new Properties();
		File Location = new File(locatorfile);
		FileReader File = new FileReader(Location);
		Prop.load(File);
		return Var = Prop.getProperty(URL);
	}


	public static void LaunchURLinChromeDriver(String locator, String locatorfile ) throws Throwable {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		PropertyFile(locator, locatorfile);
		driver.get(Var);
	}


	public static void LaunchURLinFirefoxDriver(String locator, String locatorfile  ) throws Throwable {

		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		PropertyFile(locator, locatorfile);
		driver.get(Var);
	}


	public static String ReadDataFrom(int row, int col, String sheetname) throws Throwable {


		File file = new File(ExcelData);
		FileInputStream fis = new FileInputStream(file);
		Workbook W = WorkbookFactory.create(fis);
		Sheet S = W.getSheet(sheetname);
		Row r = S.getRow(row);
		Cell s = r.getCell(col);
		String data = s.toString();
		return data;
	}



	public static WebElement getElement(String Locator, String locatorfile) throws Throwable {
		String EleType, Value;
		Properties Prop = new Properties();
		File Location = new File(locatorfile);
		FileReader File = new FileReader(Location);
		Prop.load(File);		
		EleType = Prop.getProperty(Locator).split(" ")[0];
		Value = Prop.getProperty(Locator).split(" ", 2)[1];
		switch (EleType) {
		case "id":
			return driver.findElement(By.id(Value));
		case "xpath":
			return driver.findElement(By.xpath(Value));
		default:
			return driver.findElement(By.xpath(Value));

		}
	}

	public static  List<WebElement> getElements(String Locator, String locatorfile) throws Throwable {
		String EleType, Value;
		Properties Prop = new Properties();
		File Location = new File(locatorfile);
		FileReader File = new FileReader(Location);
		Prop.load(File);		
		EleType = Prop.getProperty(Locator).split(" ")[0];
		Value = Prop.getProperty(Locator).split(" ", 2)[1];
		switch (EleType) {
		case "id":
			return ElementNames =  driver.findElements(By.id(Value));
		case "xpath":
			return ElementNames = driver.findElements(By.xpath(Value));
		default:
			return ElementNames = driver.findElements(By.xpath(Value));

		}

	}

	public static void ClickElement(String Locator,String locatorfile) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(getElement(Locator,locatorfile))).click();

	}


	public static void Clickelement(String Locator, String locatorfile, String Data) throws Throwable {

		getElements(Locator, locatorfile);
		for (WebElement ElementName : ElementNames) {
			if (ElementName.getText().equals(Data)) {
				ElementName.click();
				break;
			}

		}
	}

	public static void Clear (String Locator, String locatorfile ) throws Throwable {

		WebElement element = 	getElement(Locator, locatorfile);
		element.clear();

	}

	public static void ViewElement(String Locator, String locatorfile) throws Throwable {

		getElements(Locator, locatorfile);
		TotalResumes =  ElementNames.size();
		System.err.println("Total No Of Resumes : " + TotalResumes);
		for (WebElement ElementName : ElementNames) {
			System.err.println(	ElementName.getText());

		}
		//		int TotalResumes =	ElementNames.size();
		//		
		//		System.out.println(TotalResumes);
	}

	//}



	public static void PreviewTemplate(String Locator, String Locator2, String Locator3, String locatorfile, String Data) throws Throwable {


		getElements(Locator, locatorfile);
		TotalResumes =  ElementNames.size();
		System.err.println("Total No Of Resumes : " + TotalResumes);
		for (WebElement ElementName : ElementNames) {
			System.err.println(	ElementName.getText());


			if (ElementName.getText().equals(Data)) {
				//String Template = 	ElementName.getText();
				//WebElement Element = driver.findElement(By.xpath(ElementName));
				//JavascriptExecutor js = (JavascriptExecutor) driver;
				// Scrolling down the page till the element is found		
				// js.executeScript( "arguments[0].scrollIntoView();", ElementName);
				Thread.sleep(5000);
				WebElement ss =      getElement(Locator2, locatorfile);
				Actions action = new Actions(driver);

				action.moveToElement(ss).click().perform();
				String Template =    ElementName.getText();
				ClickElement(Template, locatorfile);
				Thread.sleep(5000);
				ClickElement(Locator3, locatorfile);
				break;
			}

		}


		//		getElements(Locator, locatorfile);
		//		TotalResumes =  ElementNames.size();
		//		System.err.println("Total No Of Resumes : " + TotalResumes);
		//		for (WebElement ElementName : ElementNames) {
		//			System.err.println(	ElementName.getText());
		//
		//	
		//			if (ElementName.getText().equals(Data)) {
		//				Actions action = new Actions(driver);
		//
		//				action.moveToElement(ElementName).click().perform();
		//				Thread.sleep(5000);
		//				ClickElement(Locator2, locatorfile);
		//				break;
		//			}
		//
		//		}

	}

	public static void ChooseTemplate(String Locator, String Locator2, String locatorfile, String Data) throws Throwable {

		getElements(Locator, locatorfile);
		TotalResumes =  ElementNames.size();
		System.err.println("Total No Of Resumes : " + TotalResumes);
		for (WebElement ElementName : ElementNames) {
			System.err.println(	ElementName.getText());


			if (ElementName.getText().equals(Data)) {
				//String Template = 	ElementName.getText();
				//WebElement Element = driver.findElement(By.xpath(ElementName));
				JavascriptExecutor js = (JavascriptExecutor) driver;
				// Scrolling down the page till the element is found		
				js.executeScript( "arguments[0].scrollIntoView();", ElementName);
				Thread.sleep(5000);
				ClickElement(Locator2, locatorfile);
				break;
			}

		}

	}


	public static void TypeDataInTheElement ( String Locator,String locatorfile, String Data) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement Element =	wait.until(ExpectedConditions.elementToBeClickable(getElement(Locator,locatorfile)));
		Element.sendKeys(Data);
	} 



	public static  void RobotClass() throws Throwable {

		Robot upf = new Robot();

		upf.keyPress(KeyEvent.VK_ENTER);

	}

	public static  void waitForElement(String Locator,String locatorfile) throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 120);
		wait.until(ExpectedConditions.elementToBeClickable(getElement(Locator,locatorfile)));

	}



	public static String GetText ( String Locator,String locatorfile) throws Throwable {

		WebDriverWait wait = new WebDriverWait(driver, 120);
		WebElement Element =	wait.until(ExpectedConditions.elementToBeClickable(getElement(Locator,locatorfile)));
		return Element.getText();
	} 


	public static void ScrollDown()throws Throwable {

		//		JavascriptExecutor js = (JavascriptExecutor) driver;String Locator,String locatorfile)
		//		// Scrolling down the page till the element is found		
		//		js.executeScript("window.scrollBy(0,-350)", "");

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}





	//
	//	public static void Refresh() {
	//
	//		driver.navigate().refresh();
	//	}
	//
	//	public static void TimeWait() {
	//
	//		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
	//	}
	//
	//	public static WebElement ExplicitTimeWait(String LoadXpath) {
	//
	//		WebDriverWait wait = new WebDriverWait(driver, 20);
	//		WebElement Element= 	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LoadXpath)));
	//		Element.click();
	//		return Element;
	//	}
	//
	//	public static WebElement GetWebElement(String LoadXpath) throws Throwable {
	//
	//		String Xpath = PropertyFile(LoadXpath);
	//		Webelement = 	driver.findElement(By.xpath(Xpath));
	//		return Webelement;
	//	}	
	//
	//	public static WebElement GetWebElementandClick(String LoadXpath) throws Throwable {
	//
	//		GetWebElement(LoadXpath);
	//		JavascriptExecutor js = (JavascriptExecutor)driver;
	//		js.executeScript("arguments[0].click()", Webelement);
	//		return Webelement;
	//	}
	//
	//
	//	public static String GetText(String LoadXpath) throws Throwable {
	//
	//		GetWebElement(LoadXpath);
	//		String Text =  Webelement.getText();
	//		return Text;
	//	}
	//

	//

	//
	//	public static  void LinkText(String LoadXpath) throws Throwable {
	//
	//		WebElement element = 	driver.findElement(By.linkText(LoadXpath));
	//		element.click();
	//	}
	//
	//	public static void List(String LoadXpath ) throws Throwable {
	//
	//		PropertyFile(LoadXpath);
	//		List<WebElement> allrow= 	driver.findElements(By.xpath(Var)); 
	//		rowcount = allrow.size(); 
	//	}
	//

	//
	//	public static void FourXpaths(String LoadXpath1 , String LoadXpath2 , String LoadXpath3 , String LoadXpath4) throws Throwable {
	//
	//		Properties Prop = new Properties();
	//		File Location = new File(XpathFileLocation);
	//		System.out.println(XpathFileLocation);
	//		FileReader File = new FileReader(Location);
	//		Prop.load(File);
	//		Xpath1 = Prop.getProperty(LoadXpath1);
	//		Xpath2 = Prop.getProperty(LoadXpath2);
	//		Xpath3 = Prop.getProperty(LoadXpath3);
	//		Xpath4 = Prop.getProperty(LoadXpath4);
	//	}
	//
	//	public static void ViewFromList(String LoadXpath1 , String LoadXpath2 , String Emails ) throws Throwable {
	//
	//		TwoXpaths(LoadXpath1, LoadXpath2);
	//
	//		for (int i = 1; i<=rowcount; i++ )		{	
	//			WebElement row = driver.findElement(By.xpath(Xpath1 + i + Xpath2));
	//			String AdminEmail = row.getText();
	//			if(AdminEmail.equalsIgnoreCase(Emails)) {
	//				System.err.println(AdminEmail); 
	//				break; 
	//			}	
	//		}
	//	}
	//

	//
	//	public static void ViewAndClick(String LoadXpath1 , String LoadXpath2 , String Emails ) throws Throwable {
	//
	//		TwoXpaths(LoadXpath1, LoadXpath2);
	//
	//		for (int i = 1; i<=rowcount; i++ )		{	
	//			WebElement row = driver.findElement(By.xpath(Xpath1 + i + Xpath2));
	//			String AdminEmail = row.getText();
	//			System.out.println(AdminEmail);
	//			if(AdminEmail.equalsIgnoreCase(Emails)) {
	//				row.click();
	//				break; 
	//			}		
	//		}
	//	}
	//

	//
	//	public static void EditFromList(String LoadXpath1 , String LoadXpath2 ,String LoadXpath3 , String LoadXpath4 , String Emails  ) throws Throwable {
	//
	//		FourXpaths(LoadXpath1, LoadXpath2, LoadXpath3, LoadXpath4);
	//
	//		for (int i = 1; i<=rowcount; i++ )		{	
	//			WebElement row = driver.findElement(By.xpath(Xpath1 + i + Xpath2));
	//			String AdminEmail = row.getText();
	//			if(AdminEmail.equalsIgnoreCase(Emails)) {
	//				WebElement edit = driver.findElement(By.xpath(Xpath3 + i + Xpath4));
	//				edit.click();
	//				break; 
	//			}
	//		}
	//	}
	//
	//	public static void DeleteFromList(String LoadXpath1 , String LoadXpath2 ,String LoadXpath3 , String LoadXpath4 , String Emails  ) throws Throwable {
	//
	//		FourXpaths(LoadXpath1, LoadXpath2, LoadXpath3, LoadXpath4);
	//
	//		for (int i = 1; i<=rowcount; i++ )		{	
	//			WebElement row = driver.findElement(By.xpath(Xpath1 + i + Xpath2));
	//			String AdminEmail = row.getText();
	//			if(AdminEmail.equalsIgnoreCase(Emails)) {
	//				WebElement edit = driver.findElement(By.xpath(Xpath3 + i + Xpath4));
	//				edit.click();
	//				break; 
	//			}
	//		}
	//	}
	//
	//	public static void AttenLiveSurvey(String LoadXpath1 , String LoadXpath2 ,String LoadXpath3 , String LoadXpath4 , String Emails  ) throws Throwable {
	//
	//		FourXpaths(LoadXpath1, LoadXpath2, LoadXpath3, LoadXpath4);
	//
	//		for (int i = 1; i<=rowcount; i++ )		{	
	//			WebElement row = driver.findElement(By.xpath(Xpath1 + i + Xpath2));
	//			String AdminEmail = row.getText();
	//			if(AdminEmail.equalsIgnoreCase(Emails)) {
	//				WebElement edit = driver.findElement(By.xpath(Xpath3 + i + Xpath4));
	//				edit.click();
	//				break; 
	//			}
	//		}
	//	}
	//
	//	public static void FilteredProject (String LoadXpath1 , String LoadXpath2 ,String LoadXpath3 , String LoadXpath4 , String ProjectStatus  ) throws Throwable {
	//
	//		FourXpaths(LoadXpath1, LoadXpath2, LoadXpath3, LoadXpath4);
	//
	//		for (int i = 1; i<=rowcount; i++ )		{	
	//			WebElement row = driver.findElement(By.xpath(Xpath1 + i + Xpath2));
	//			String ProjectStatus1 = row.getText();
	//
	//			if(ProjectStatus1.equalsIgnoreCase(ProjectStatus)) {
	//				WebElement ProjectName = driver.findElement(By.xpath(Xpath3 + i + Xpath4));
	//				System.out.println(ProjectName); 
	//			}
	//		}
	//	}
	//
	//	public static  List<WebElement> FindElements (String LoadXpath) throws Throwable {
	//		Properties Prop = new Properties();
	//
	//		File Location = new File(XpathFileLocation);
	//		System.out.println(XpathFileLocation);
	//		FileReader File = new FileReader(Location);
	//		Prop.load(File);
	//		String Xpath = Prop.getProperty(LoadXpath);
	//		Webelements= 	driver.findElements(By.xpath(Xpath));
	//		return Webelements;
	//	}
	//
	//	public static  List<WebElement> Calendar(String LoadXpath , String Value ) throws Throwable {
	//
	//		FindElements(LoadXpath);		
	//		rowcount = Webelements.size();
	//
	//		for (WebElement Key : Webelements) {
	//			if (Key.getText().equals(Value)) {
	//				Key.click();
	//				break;
	//			}
	//		}
	//		return Webelements;
	//	}	
	//
	//	public static  List<WebElement> Select(String LoadXpath ,  String Value ) throws Throwable {
	//
	//		FindElements(LoadXpath);		
	//		rowcount = Webelements.size();
	//
	//		for (WebElement Key : Webelements) {
	//			if (Key.getText().equals(Value)) {
	//				Key.click();
	//				break;
	//			}
	//		}
	//		return Webelements;
	//	}	
	//
	//	public static  List<WebElement> FindElementsandClick(String LoadXpath , String Value ) throws Throwable {
	//
	//		FindElements(LoadXpath); 
	//		rowcount = Webelements.size();
	//
	//		for (WebElement Key : Webelements) {
	//
	//			if (Key.getText().equals(Value)) {
	//				Key.click();
	//				break;
	//			}
	//		}
	//		return Webelements;
	//	}
	//
	//	public static  void ViewAll(String LoadXpath ) throws Throwable {
	//
	//		FindElements(LoadXpath); 
	//		rowcount = Webelements.size();
	//
	//		for (WebElement Key : Webelements) {
	//
	//			String UserEmail	=	Key.getText();
	//			System.err.println(UserEmail);
	//
	//		}
	//	}
	//
	//	public static void TakeScreenshot(String Location) throws Throwable {
	//
	//		File SrcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//		FileUtils.copyFile(SrcFile, new File("C:\\Users\\ajithkumar.j\\Desktop\\Optisol_Work\\Auto\\test.azure\\SS"+Location));
	//		String SS = "C:\\Users\\ajithkumar.j\\Desktop\\Optisol_Work\\Auto\\test.azure\\SS"+Location;
	//		extenttest.addScreenCaptureFromPath(SS);
	//	}
	//
	//	public static void ConditionPass(String Text) throws Throwable {
	//
	//		System.out.println(Text);
	//
	//	}
	//
	//	public static void ConditionFail(String Text) throws Throwable {
	//
	//		System.out.println(Text);
	//
	//	}
	//
	//	public static void PrintOutput(String OutputText ) {
	//		System.out.println(OutputText);
	//	}
	//

	//
	//
	//
	//	public static void TryandClick(String LoadXpath ) throws Throwable {
	//
	//		try {
	//			GetWebElementandClick(LoadXpath);
	//		} catch (StaleElementReferenceException e) {
	//			GetWebElementandClick(LoadXpath);
	//		}
	//	}
	//
	//
	//
	//	public static void DropdownList(String LoadXpath , String TypeOfStudy ) throws Throwable {
	//		GetWebElement(LoadXpath);
	//		Select w1 = new Select(Webelement);
	//		w1.selectByValue(TypeOfStudy);
	//
	//	}
	//
	//
	//
	//	public static  void Mouse(String LoadXpath) throws InterruptedException, Throwable {
	//
	//		GetWebElement(LoadXpath);
	//		Actions actionProvLocatorer = new Actions(driver);
	//		actionProvLocatorer.doubleClick(Webelement).perform();
	//	}
	//
	//	public static  void Random(String LoadXpath) throws InterruptedException, Throwable {
	//		Random rand = new Random();
	//
	//
	//		GetWebElement(LoadXpath);
	//
	//Webelement.sendKeys(rand.nextInt(1000)+"a");
	//
	//
	//
	//	}



}
