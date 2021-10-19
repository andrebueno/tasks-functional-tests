package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver accessApplication()
	{
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	@Test
	public void shouldSaveTask()
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
	public void shouldnSaveTaskWithoutDescription()
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
	public void shouldnSaveTaskWithoutDate()
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
	public void shouldntSaveTaskWithPastDate()
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
}
