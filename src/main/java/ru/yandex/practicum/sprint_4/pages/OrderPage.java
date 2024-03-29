package ru.yandex.practicum.sprint_4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage extends AbstractPage {

    @FindBy(xpath = ".//input[@placeholder='* Имя']")
    private WebElement fieldName;

    @FindBy(xpath = ".//input[@placeholder='* Фамилия']")
    private WebElement fieldSurname;

    @FindBy(xpath = ".//input[@placeholder='* Адрес: куда привезти заказ']")
    private WebElement fieldDeliveryAddres;

    @FindBy(xpath = ".//input[@placeholder='* Станция метро']")
    private WebElement dropMetroStation;

    @FindBy(xpath = ".//input[@placeholder='* Телефон: на него позвонит курьер']")
    private WebElement fieldPhoneNUmber;

    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement btnNext;

    @FindBy(xpath = ".//input[@placeholder='* Когда привезти самокат']")
    private WebElement dropDeliveryDate;

    @FindBy(xpath = ".//span[@class='Dropdown-arrow']")
    private WebElement dropRentalPeriod;

    @FindBy(xpath = ".//input[@id='black']")
    private WebElement checkboxBlackPearl;

    @FindBy(xpath = ".//input[@id='grey']")
    private WebElement checkboxGrayHopelessness;

    @FindBy(xpath = ".//input[@placeholder='Комментарий для курьера']")
    private WebElement commentCourier;

    @FindBy(xpath = ".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']")
    private WebElement btnOrder;

    @FindBy(xpath = ".//button[contains(text(), 'Да')]")
    private WebElement btnYes;

    @FindBy(xpath = ".//div[@class='Order_ModalHeader__3FDaJ']")
    private WebElement headerOrderProcessed;

    public OrderPage() {
        PageFactory.initElements(driver, this);
    }

    public void enterUserName (String name, String surname) {
        enterName(name);
        enterSurname(surname);
    }

    private void enterName(String name) {
        fieldName.sendKeys(name);
    }

    private void enterSurname(String surname) {
        fieldSurname.sendKeys(surname);
    }

    public void enterDeliveryAddres(String deliveryAddres) {
        fieldDeliveryAddres.sendKeys(deliveryAddres);
    }

    public void enterMetroStation(String metroStation) {
        String metroOptionTemplate = ".//div[@class='select-search__select']//*[contains(text(), '%s')]";

        dropMetroStation.sendKeys(metroStation);
        (new WebDriverWait(driver, Duration.ofSeconds(3))).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format(metroOptionTemplate, metroStation)))));

        driver.findElement(By.xpath(String.format(metroOptionTemplate, metroStation))).click();
    }

    public void enterPhoneNUmber(String phoneNUmber) {
        fieldPhoneNUmber.sendKeys(phoneNUmber);
    }

    public void clickBtnNext() {
        btnNext.click();
    }

    public void enterDeliveryDate(String deliveryDate) {
        String date = ".//div[@aria-label='month  2022-11' ]//div[contains(@aria-label, '%s')]";
        dropDeliveryDate.click();
        (new WebDriverWait(driver, Duration.ofSeconds(3))).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format(date, deliveryDate)))));
        driver.findElement(By.xpath(String.format(date, deliveryDate))).click();
    }

    public void enterDropRentalPeriod(String rentalPeriod) {
        String period = ".//div[contains(text(), '%s')]";
        dropRentalPeriod.click();
        (new WebDriverWait(driver, Duration.ofSeconds(3))).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format(period, rentalPeriod)))));
        driver.findElement(By.xpath(String.format(period, rentalPeriod))).click();
    }

    public void setScooterColor(String color) {
        if (color.equals("чёрный жемчуг")) {
            checkboxBlackPearl.click();
        } else if (color.equals("серая безысходность")) {
            checkboxGrayHopelessness.click();
        }
    }

    public void enterCommentCourier(String comment) {
        commentCourier.sendKeys(comment);
    }

    public void clickBtnOrder() {
        btnOrder.click();
    }

    public void clickBtnYes() {
        btnYes.click();
    }

    public String getTextOrderProcessed() {
        return headerOrderProcessed.getText();
    }
}