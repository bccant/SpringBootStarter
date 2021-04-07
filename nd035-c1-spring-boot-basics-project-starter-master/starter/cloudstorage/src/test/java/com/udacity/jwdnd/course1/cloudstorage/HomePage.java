package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    @FindBy(id="nav-files-tab")
    public WebElement filesTab;

    @FindBy(id="nav-notes-tab")
    public WebElement notesTab;

    @FindBy(id="nav-credentials-tab")
    public WebElement credsTab;

    @FindBy(xpath=("//button[text()='Logout']"))
    public WebElement logoutButton;

    @FindBy(xpath=("//button[text()='\n" +
            "                            + Add a New Note\n" +
            "                        ']"))
    public WebElement addButton;

    @FindBy(xpath=("//button[text()='\n" +
            "                            + Add a New Credential\n" +
            "                        ']"))
    public WebElement addCred;

    @FindBy(id="noteModalLabel")
    public WebElement notesHeader;

    @FindBy(id="note-title")
    public WebElement noteTitle;

    @FindBy(id="note-description")
    public WebElement notesDesc;

    @FindBy(id="credential-url")
    public WebElement credURL;

    @FindBy(id="credential-username")
    public WebElement credUser;

    @FindBy(id="credential-password")
    public WebElement credPass;

    @FindBy(id="credential-save")
    public WebElement saveCreds;

    @FindBy(xpath=("//button[text()='Save changes']"))
    public WebElement saveChanges;

    @FindBy(xpath=("//button[text()='Close']"))
    public WebElement closeChanges;

    @FindBy(xpath=("//button[text()='Edit']"))
    public WebElement editChanges;

    @FindBy(xpath=("//a[text()='Delete']"))
    public WebElement deleteChanges;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void clickLogout() {
        this.logoutButton.click();
    }

    public boolean onHomePage(WebDriver driver)  {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        try {
        WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-files-tab")));
            return marker.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean addNotes(WebDriver driver, String title, String desciption) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            //WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-notes-tab")));
            WebElement marker = wait.until(ExpectedConditions.elementToBeClickable(notesTab));
            Thread.sleep(2000);
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(addButton));
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(noteTitle));
            marker.click();
            noteTitle.sendKeys(title);
            notesDesc.sendKeys(desciption);
            saveChanges.click();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean editNotes(WebDriver driver, String title, String desciption) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            //WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-notes-tab")));
            WebElement marker = wait.until(ExpectedConditions.elementToBeClickable(notesTab));
            Thread.sleep(2000);
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(editChanges));
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(noteTitle));
            marker.click();
            noteTitle.clear();
            notesDesc.clear();
            noteTitle.sendKeys(title);
            notesDesc.sendKeys(desciption);
            saveChanges.click();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean deleteNotesCreds(WebDriver driver, int type) {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement marker;
        try {
            if (type == 1) {
                marker = wait.until(ExpectedConditions.elementToBeClickable(notesTab));
            } else {
                marker = wait.until(ExpectedConditions.elementToBeClickable(credsTab));
            }
            Thread.sleep(2000);
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(deleteChanges));
            marker.click();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean addCreds(WebDriver driver, String url, String userName, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            //WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-notes-tab")));
            WebElement marker = wait.until(ExpectedConditions.elementToBeClickable(credsTab));
            Thread.sleep(2000);
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(addCred));
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(credURL));
            marker.click();
            credURL.sendKeys(url);
            credUser.sendKeys(userName);
            credPass.sendKeys(password);
            saveCreds.click();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean editcreds(WebDriver driver, String url, String userName, String password) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            //WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("nav-notes-tab")));
            WebElement marker = wait.until(ExpectedConditions.elementToBeClickable(credsTab));
            Thread.sleep(2000);
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(editChanges));
            marker.click();
            marker = wait.until(ExpectedConditions.elementToBeClickable(credURL));
            marker.click();
            credURL.clear();
            credUser.clear();
            credPass.clear();
            credURL.sendKeys(url);
            credUser.sendKeys(userName);
            credPass.sendKeys(password);
            saveCreds.click();
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public boolean verifyEmptySets(WebDriver driver, int type)  {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        WebElement marker;
        try {
            if (type == 1) {
                marker = wait.until(ExpectedConditions.elementToBeClickable(notesTab));
            } else {
                marker = wait.until(ExpectedConditions.elementToBeClickable(credsTab));
            }
            Thread.sleep(2000);
            marker.click();
            marker = wait.until(ExpectedConditions.visibilityOf(editChanges));
            return marker.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
