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

WebUI.setText(findTestObject('Object Repository/input_Welcome_name (9)'), 'kulisse8')

WebUI.setEncryptedText(findTestObject('Object Repository/input_Welcome_password (9)'), '3pybmnes8qQ=')

WebUI.click(findTestObject('Page_Spring Security Tutorial/button_Login'))

WebUI.click(findTestObject('Object Repository/a_My profile'))

WebUI.setText(findTestObject('Page_Spring Security Tutorial/input_Age_age'), '22')

WebUI.setText(findTestObject('Page_Spring Security Tutorial/textarea_Bio_bio'), 'my bio')

WebUI.setText(findTestObject('Page_Spring Security Tutorial/input_Signature_signature'), 'my signature')

Age = '22'

Bio = 'my bio'

Signature = 'my signature'

WebUI.click(findTestObject('Page_Spring Security Tutorial/button_Submit'))

assert Age == WebUI.getText(findTestObject('Object Repository/input_Age_age'))

assert Bio == WebUI.getText(findTestObject('Object Repository/textarea_Bio_bio'))

assert Signature == WebUI.getText(findTestObject('Object Repository/input_Signature_signature'))

WebUI.navigateToUrl('http://localhost:8080/logout')

WebUI.closeBrowser()

WebUI.setText(findTestObject('Page_Spring Security Tutorial/input_Signature_signature'), 'my signature')

