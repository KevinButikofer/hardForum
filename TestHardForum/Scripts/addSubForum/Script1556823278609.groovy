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

WebUI.setText(findTestObject('Object Repository/1/input_Welcome_name (1)'), 'user6')

WebUI.setEncryptedText(findTestObject('Object Repository/1/input_Welcome_password (1)'), 'OfYSgXUfG6A=')

WebUI.click(findTestObject('Object Repository/1/button_Login (1)'))

WebUI.setText(findTestObject('Page_Spring Security Tutorial/input_Category_name'), categoryName)

WebUI.setText(findTestObject('Object Repository/1/input_Description_description (1)'), 'Le ceveau du PC')

WebUI.click(findTestObject('Object Repository/1/input_Description_btn btn-primary col-5'))

assert WebUI.getUrl() == ('http://localhost/forum/' + categoryName)

WebUI.navigateToUrl('http://localhost:8080/logout')

WebUI.closeBrowser()

