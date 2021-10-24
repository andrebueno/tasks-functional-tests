package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {

	public WebDriver accessApplication() throws MalformedURLException
	{
		// usando o chrome driver para execucao direto no chrome
		//WebDriver driver = new ChromeDriver();
		// usando o selenium grid para execução nos nós do grid
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.0.9:4444/wd/hub"), cap);
		//driver.navigate().to("http://localhost:8001/tasks");
		driver.navigate().to("http://192.168.0.9:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void shouldSaveTask() throws MalformedURLException
	{	
		WebDriver driver = accessApplication();
		
		try {
			// encontrar e clicar em Add Todo
			//
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descrição
			//
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			//
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
			
			// clicar em salvar
			//
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			//
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
		} finally {
			// fechar o browser
			//
			driver.quit();
		}
	}
	
	@Test
	public void shouldnSaveTaskWithoutDescription() throws MalformedURLException
	{	
		WebDriver driver = accessApplication();
		
		try {
			// encontrar e clicar em Add Todo
			//
			driver.findElement(By.id("addTodo")).click();
					
			// escrever a data
			//
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2022");
			
			// clicar em salvar
			//
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de erro
			//
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the task description", message);
		} finally {
			// fechar o browser em caso de erro
			//
			driver.quit();
		}
	}
	
	@Test
	public void shouldnSaveTaskWithoutDate() throws MalformedURLException
	{	
		WebDriver driver = accessApplication();
		
		try {
			// encontrar e clicar em Add Todo
			//
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descrição
			//
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
						
			// clicar em salvar
			//
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de erro
			//
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Fill the due date", message);
		} finally {
			// fechar o browser
			//
			driver.quit();
		}
	}
	
	@Test
	public void shouldntSaveTaskWithPastDate() throws MalformedURLException
	{	
		WebDriver driver = accessApplication();
		
		try {
			// encontrar e clicar em Add Todo
			//
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descrição
			//
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			//
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2000");
			
			// clicar em salvar
			//
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de erro
			//
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			// fechar o browser
			//
			driver.quit();
		}
	}
	
	@Test
	public void shouldRemoveTask() throws MalformedURLException
	{	
		WebDriver driver = accessApplication();
		
		try {
			// inserir uma tarefa
			//
			driver.findElement(By.id("addTodo")).click();
			
			// escrever a descrição
			//
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			// escrever a data
			//
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			// clicar em salvar
			//
			driver.findElement(By.id("saveButton")).click();
			
			// validar mensagem de sucesso
			//
			String message = driver.findElement(By.id("message")).getText();
			
			Assert.assertEquals("Success!", message);
			
			// remover a tarefa
			driver.findElement(By.xpath("//a[@class='btn btn-outline-danger btn-sm]'")).click();
			
			// validar mensagem de sucesso
			//
			message = driver.findElement(By.id("message")).getText();
						
			Assert.assertEquals("Success!", message);
			
		} finally {
			// fechar o browser
			//
			driver.quit();
		}
	}
}
