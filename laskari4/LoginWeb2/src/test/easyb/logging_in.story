import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description 'User can log in with valid username/password-combination'

scenario "user can login with correct password", {
    given 'login selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("login"))       
        element.click()       
    }

    when 'a valid username and password are given', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("pekka")
        element = driver.findElement(By.name("password"))
        element.sendKeys("akkep")
        element = driver.findElement(By.name("login"))
        element.submit();
    }
 
    then 'user will be logged in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "user can not login with incorrect password", {
    given 'command login selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("login"))       
        element.click()  
    }
    when 'a valid username and incorrect password are given', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("pekka")
        element = driver.findElement(By.name("password"))
        element.sendKeys("kkep")
        element = driver.findElement(By.name("login"))
        element.submit()
    }
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("Give your credentials to login").shouldBe true
    }
}

scenario "nonexistent user can not login to system", {
    given 'command login selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("login"))       
        element.click()
    }
    when 'a nonexistent username and some password are given', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("kuhnuri")
        element = driver.findElement(By.name("password"))
        element.sendKeys("123456")
        element = driver.findElement(By.name("login"))
        element.submit()
    }
    then 'user will not be logged in to system', {
        driver.getPageSource().contains("Give your credentials to login").shouldBe true
    }
}      
description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation successfull with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("register new user"))       
        element.click()
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("tompBo")
        element = driver.findElement(By.name("password"))
        element.sendKeys("12345678")
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("12345678");
        element = driver.findElement(By.name("add"))
        element.submit()
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with successfully generated account", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("login"))       
        element.click()
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("tompBo")
        element = driver.findElement(By.name("password"))
        element.sendKeys("12345678")
        element = driver.findElement(By.name("login"))
        element.submit();
    }

    then  'new credentials allow logging in to system', {
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected',{
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("register new user"))       
        element.click()
    }
    when 'a valid username and too short password are entered',{
        element = driver.findElement(By.name("username"))
        element.sendKeys("tompBo")
        element = driver.findElement(By.name("password"))
        element.sendKeys("1as")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("1as");
        element = driver.findElement(By.name("add"))
        element.submit() 
   }

    then 'new user is not be registered to system',{
        driver.getPageSource().contains("Create username and give password").shouldBe true
    }
}

scenario "creation fails with correct username and password consisting of letters", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("register new user"))       
        element.click()
    }
    when 'a valid username and password consisting of letters are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("tomppa")
        element = driver.findElement(By.name("password"))
        element.sendKeys("asdfasdf")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("asdfasdf");
        element = driver.findElement(By.name("add"))
        element.submit()
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Create username and give password").shouldBe true
    }
}

scenario "creation fails with too short username and valid password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("register new user"))       
        element.click()
    }
    when 'a too sort username and valid password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("tom")
        element = driver.findElement(By.name("password"))
        element.sendKeys("asdfasd1")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("asdfasd1");
        element = driver.findElement(By.name("add"))
        element.submit()
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Create username and give password").shouldBe true
    }
}

scenario "creation fails with already taken username and valid password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("register new user"))       
        element.click()
    }
    when 'a already taken username and valid password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("tompBo")
        element = driver.findElement(By.name("password"))
        element.sendKeys("asdfasd1")
        element = driver.findElement(By.name("passwordConfirmation"))
        element.sendKeys("asdfasd1");
        element = driver.findElement(By.name("add"))
        element.submit()
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Create username and give password").shouldBe true
    }
}

scenario "can not login with account that is not successfully created", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver()
        driver.get("http://localhost:8080")
        element = driver.findElement(By.linkText("login"))       
        element.click()
    }
    when 'a invalid username/password are entered', {
        element = driver.findElement(By.name("username"))
        element.sendKeys("kuhnuri")
        element = driver.findElement(By.name("password"))
        element.sendKeys("123456")
        element = driver.findElement(By.name("login"))
        element.submit()
    }
    then  'new credentials do not allow logging in to system', {
        driver.getPageSource().contains("Give your credentials to login").shouldBe true
    }
} 