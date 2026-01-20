package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webElementActions.ElementActions;

public class RegistrationPage extends BasePage{
	
	private static final Logger logger = LogManager
			.getLogger(ArraysPage.class);
	
	@FindBy(name="username") 
	private WebElement username;
	@FindBy(name="password1")
	private WebElement password;
	@FindBy(id="id_password2") 
	private WebElement confirmpswd;
    @FindBy(xpath="//input[@value='Register']")
    private WebElement registerBtn;
    @FindBy(xpath="//div[contains(@class,'alert alert-primary')]")
    private WebElement pswdmismatch;
    @FindBy(xpath="//a[contains(text(),'Login ')]")
    private WebElement loginBtn;
	@FindBy(xpath="//div[contains(@class,'alert alert-primary')]")
	private WebElement newAccountMsg;
	
	
	ElementActions elementActions;
	
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver,this);
		elementActions =new ElementActions(driver);
	}

     public void usernameFieldvisible()
	{
    	 username.isEnabled();
    	 logger.info("Username Field is Enabled"+username.isEnabled());
	}
     public void passwordFieldvisible()
     {
    	 logger.info("Password Field is enabled"+password.isEnabled());
    	 
     }
	public void confirmpswdField()
	{
		logger.info("ConfirmPassword Field is enabled" +confirmpswd.isEnabled());
	}
	public void registerBtnvisible()
	{
		logger.info("Registration button is visible"+registerBtn.isDisplayed());
	}

	public void enterUsername(String user) {
		elementActions.clickAction(username);
		elementActions.sendKeys(username, user);
		}

		public void enterPassword(String pswd) {
			elementActions.clickAction(password);
			elementActions.sendKeys(password, pswd);
		}

		public void enterConfirmPassword(String cpswd) {
			elementActions.clickAction(confirmpswd);
			elementActions.sendKeys(confirmpswd, cpswd);
		}
		public void clickRegister() {
			registerBtn.click();
			}
	public String ErrorMsg()
	{
		String errorText=pswdmismatch.getText();
		logger.info("ErrorMessage:"  +errorText);
		return errorText;
	}
	public String getUsernameValidationMessage() {
	    return username.getAttribute("validationMessage");
	}
	public String getPasswordValidationMessage() {
	    return password.getAttribute("validationMessage");
	}
	

    
	public void AccountCreateMsg()
	{
		String msg=newAccountMsg.getText();
		logger.info("Account created message:"+msg);
	}
}



