import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

WebUI.openBrowser('')

WebUI.navigateToUrl('http://localhost:8080/login')

WebUI.setText(findTestObject('Page_Spring Security Tutorial/input_Welcome_name (3)'), 'kulisse8')

WebUI.setEncryptedText(findTestObject('Page_Spring Security Tutorial/input_Welcome_password (3)'), '3pybmnes8qQ=')

not_run: WebUI.sendKeys(findTestObject('Page_Spring Security Tutorial/input_Welcome_password (3)'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('1/button_Login'))

WebUI.click(findTestObject('Page_Spring Security Tutorial/div_Hardware'))

WebUI.setText(findTestObject('Object Repository/Page_My subforum/input_Title_name'), 'title')

WebUI.click(findTestObject('Object Repository/Page_My subforum/p'))

WebUI.setText(findTestObject('Object Repository/Page_My subforum/div_messageContent'), '<p style="">messageContent</p>')

url = WebUI.getUrl()

WebUI.click(findTestObject('Object Repository/Page_My subforum/button_Submit'))

assert url != WebUI.getUrl()

WebUI.navigateToUrl('http://localhost:8080/logout')

WebUI.closeBrowser()

