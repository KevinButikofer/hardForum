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

WebUI.setText(findTestObject('Object Repository/Page_Spring Security Tutorial/input_Welcome_name (3)'), 'testCase')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Spring Security Tutorial/input_Welcome_password (3)'), 'k5M9B2M38/NbP4F7W5R/fg==')

WebUI.sendKeys(findTestObject('Object Repository/Page_Spring Security Tutorial/input_Welcome_password (3)'), Keys.chord(
        Keys.ENTER))

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/div_Hardware'))

WebUI.click(findTestObject('Object Repository/Page_My subforum/h5_testTopic'))

WebUI.setText(findTestObject('Object Repository/Page_Spring Security Tutorial/input_Title_title'), 'Test Post')

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/p'))

WebUI.setText(findTestObject('Object Repository/Page_Spring Security Tutorial/div_post content'), '<p style="">post content </p>')

WebUI.setText(findTestObject('Object Repository/Page_Spring Security Tutorial/div_post content dwdwad'), '<h1 style="">post content dw</h1><p style="">dwad</p>')

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/button_Submit'))

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/h4_Test Post'))

