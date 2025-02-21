package com.sofka;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class practicaSelenium {


    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.uitestingplayground.com/home");
    }

    @Test
    public void createButton_dinamycIdTest() throws InterruptedException {
        WebElement dimanycId = driver.findElement(By.xpath("//a[text()='Dynamic ID']"));
        dimanycId.click();
        WebElement buttonDinamyc = driver.findElement(By.xpath("//button[text()='Button with Dynamic ID']"));
        buttonDinamyc.click();
        System.out.println("Primer test");
    }

    @Test
    public void class_atributte_select_button() throws InterruptedException {
        WebElement classAtribute = driver.findElement(By.xpath("//a[text()='Class Attribute']"));
        classAtribute.click();
        WebElement selectButtonGreen = driver.findElement(By.xpath("//*[contains(@class, 'btn-success')]"));
        selectButtonGreen.click();
        WebElement selectButtonBlue = driver.findElement(By.xpath("//*[contains(@class, 'btn-primary')]"));
        selectButtonBlue.click();
        Alert alert = driver.switchTo().alert();
        String messageAlert = alert.getText();
        String messageExpect = "Primary button pressed";
        if (messageAlert.equals(messageExpect)) {
            System.out.println("La alerta muestra el mensaje esperado.");
        } else {
            System.out.println("El mensaje de la alerta es diferente: " + messageAlert);
        }
        alert.accept();
        System.out.println("Segundo test");

    }

    @Test
    public void class_hiddenlayers_select_button() {
        WebElement classHiddenlayers = driver.findElement(By.xpath("//a[text()='Hidden Layers']"));
        classHiddenlayers.click();
        WebElement createButtonGreen = driver.findElement(By.xpath("//*[contains(@class, 'btn-success')]"));
        createButtonGreen.click();
        Boolean isEnabledButton = driver.findElement(By.xpath("//*[contains(@class, 'btn-success')]")).isEnabled();
        assertEquals(isEnabledButton, true);
        System.out.println("Tercer test");
    }

    @Test
    public void class_load_delay_button_appearing() {

        WebElement classLoadDelay = driver.findElement(By.xpath("//a[text()='Load Delay']"));
        classLoadDelay.click();

        WebElement createButtonAfterDelay = new WebDriverWait(driver, Duration.ofSeconds(50)).until(
                ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, 'btn-primary')]")));
        createButtonAfterDelay.click();
        System.out.println("Cuarto test");
    }

    @Test
    public void class_click_test_input_rename_button() {
        WebElement classTextInput = driver.findElement(By.xpath("//a[text()='Text Input']"));
        classTextInput.click();
        WebElement userText = driver.findElement(By.xpath("//input[contains(@class,'form-control')]"));
        userText.sendKeys("value");
        WebElement buttonChange = driver.findElement(By.xpath("//button[contains(@class,'btn-primary')]"));
        buttonChange.click();
        Boolean valueButton = buttonChange.isEnabled();
        assertEquals(valueButton, true);
        String actualValue = userText.getAttribute("value");
        assertEquals(actualValue, "value", "El valor del input no corresponde");

        System.out.println("Quinto test");
    }

    @Test
    public void scrollbars_click_button() {
        WebElement classScrollbars = driver.findElement(By.xpath("//a[text()='Scrollbars']"));
        classScrollbars.click();

        WebElement hidingBut = driver.findElement(By.id("hidingButton"));
        new Actions(driver)
                .scrollToElement(hidingBut)
                .perform();
        hidingBut.click();
        assert hidingBut.isEnabled();
        System.out.println("Sexto test");
    }

    @Test
    public void progressBar() {
        WebElement classProgressBar = driver.findElement(By.xpath("//a[text()='Progress Bar']"));
        classProgressBar.click();

        WebElement startButton = driver.findElement(By.id("startButton"));
        WebElement stopButton = driver.findElement(By.id("stopButton"));
        WebElement progressBar = driver.findElement(By.id("progressBar"));
        startButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(driver -> {
            String progressText = progressBar.getAttribute("aria-valuenow");
            int progressValue = Integer.parseInt(progressText);
            return progressValue >= 75;
        });
        stopButton.click();

    }

    @Test
    public void nonBreakingSpace() {
        WebElement classNonBreakingSpace = driver.findElement(By.xpath("//a[text()='Non-Breaking Space']"));
        classNonBreakingSpace.click();

        WebElement myButton = driver.findElement(By.xpath("//button[contains(@class, 'btn') and contains(@class, 'btn-primary')]"));
        myButton.click();

        String expectedTitle = "Non-Breaking Space"; // Cambia por el título esperado
        String actualTitle = driver.getTitle();

        Assertions.assertEquals(actualTitle, expectedTitle);

    }

    @Test
    public void verifyTextTest() {
        WebElement classVerifyTest = driver.findElement(By.xpath("//a[text()='Verify Text']"));
        classVerifyTest.click();

        WebElement userNameOneElement = driver.findElement(By.xpath("//section/div[1]/div[1]//span[contains(@class, 'badge-secondary')]"));
        String userNameOne = userNameOneElement.getText().trim();

        WebElement userNameTwoElement = driver.findElement(By.xpath("//section/div[1]/div[2]//span[contains(@class, 'badge-secondary')]"));
        String textComplet = userNameTwoElement.getText().replace("\u00A0", " ").trim();

        String[] partesTexto = textComplet.split("\n");
        String userNameTwo = (partesTexto.length > 1) ? partesTexto[1].trim() : "";

        if (userNameOne.equals(userNameTwo)) {
            System.out.println("Los nombres coinciden: " + userNameOne);
        } else {
            System.out.println("Los nombres NO coinciden: '" + userNameOne + "' vs '" + userNameTwo + "'");
        }
        System.out.println("Noveno test");
    }

    @Test
    public void ajaxDataExisting() {
        WebElement classAjaxDta = driver.findElement(By.xpath("//a[text()='AJAX Data']"));
        classAjaxDta.click();

        WebElement ajaxButton = driver.findElement(By.id("ajaxButton"));
        ajaxButton.click();

        WebElement bgSuccessCreate = new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//p[@class = 'bg-success']")));
        bgSuccessCreate.getText();

        System.out.println("Decimo test");
    }

    @Test
    public void clientSideExisting() {
        WebElement classClientSide = driver.findElement(By.xpath("//a[text()='Client Side Delay']"));
        classClientSide.click();

        WebElement clientButton = driver.findElement(By.id("ajaxButton"));
        clientButton.click();

        WebElement bgReportCreate = new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//p[text() = 'Data calculated on the client side.']")));
        bgReportCreate.getText();

        System.out.println("Onceavo test");

    }

    @Test
    public void clickDouble() {
        WebElement classClick = driver.findElement(By.xpath("//a[text()='Click']"));
        classClick.click();

        WebElement buttonBlue = driver.findElement(By.xpath("//button[@id = 'badButton']"));
        buttonBlue.click();

        System.out.println("Primer click realizado.");
        try {
            buttonBlue.click();
            System.out.println("Segundo click permitido (Error)");
        } catch (Exception e) {
            System.out.println("Segundo click bloqueado correctamente.");
        }

        System.out.println("Doceavo test");
    }

    @Test
    public void visibilityButtons() {
        WebElement classVisibilityButtons = driver.findElement(By.xpath("//a[text()='Visibility']"));
        classVisibilityButtons.click();

        WebElement buttonHide = driver.findElement(By.xpath("//button[@id='hideButton']"));
        buttonHide.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        List<By> buttonLocators = Arrays.asList(
                By.id("removedButton"), By.id("zeroWidthButton"), By.id("overlappedButton"),
                By.id("transparentButton"), By.id("invisibleButton"), By.id("notdisplayedButton"),
                By.id("offscreenButton")
        );

        boolean allInvisible = buttonLocators.stream().allMatch(locator -> {
            try {
                return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            } catch (Exception e) {
                return true;
            }
        });

        if (allInvisible) {
            System.out.println("Todos los botones desaparecieron correctamente.");
        } else {
            System.out.println("Algunos botones siguen visibles.");
        }

        System.out.println("Treceavo test");
    }


    @Test
    public void loginDemo() {

        WebElement classSampleApp = driver.findElement(By.xpath("//a[text()='Sample App']"));
        classSampleApp.click();

        WebElement userName = driver.findElement(By.xpath("//input[@name='UserName']"));
        userName.sendKeys("Rosa");

        WebElement password = driver.findElement(By.xpath("//input[@name='Password']"));
        password.sendKeys("pwd");

        WebElement buttonLogin = driver.findElement(By.xpath("//button[@id='login']"));
        buttonLogin.click();

        WebElement loginStatus = driver.findElement(By.id("loginstatus"));
        String actualText = loginStatus.getText();
        String expect = "Welcome, Rosa!";

        Assertions.assertEquals(actualText, expect);
        System.out.println("Catorceavo");
    }


    @Test
    public void mouseOverAdd() {

        WebElement classMouseOver = driver.findElement(By.xpath("//a[text()='Mouse Over']"));
        classMouseOver.click();

        WebElement clickMe = driver.findElement(By.xpath("//a[text()='Click me']"));
        WebElement initialCounter = driver.findElement(By.xpath("//span[@id='clickCount']"));

        int intInitialCounter = Integer.parseInt(initialCounter.getText().trim());

        Actions actions = new Actions(driver);

        actions.doubleClick(clickMe).perform();

        WebElement finalCounter = driver.findElement(By.xpath("//span[@id='clickCount']"));

        int intFinalCounter = Integer.parseInt(finalCounter.getText().trim());

        Assertions.assertEquals(0, intInitialCounter);
        Assertions.assertEquals(2, intFinalCounter);

        System.out.println("Quinceavo");
    }


    @AfterEach
    public void closeDown() {
        driver.quit();
    }

}
