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

WebUI.setText(findTestObject('Object Repository/Page_Spring Security Tutorial/input_Welcome_name (5)'), 'kulisse4')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Spring Security Tutorial/input_Welcome_password (5)'), '3pybmnes8qQ=')

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/button_Login (2)'))

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/p_Le physique'))

WebUI.click(findTestObject('Page_My subforum/a_dwake      10      5119 713 PM'))

url = WebUI.getUrl()

WebUI.setText(findTestObject('Object Repository/Page_Spring Security Tutorial/input_Title_title'), 'title')

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/p'))

WebUI.setText(findTestObject('Object Repository/Page_Spring Security Tutorial/div_post'), '<p style="">post</p>')

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/button_Submit'))

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/input_title_btn btn-danger'))

assert url == WebUI.getUrl()

WebUI.navigateToUrl('http://localhost:8080/logout')

WebUI.closeBrowser()

