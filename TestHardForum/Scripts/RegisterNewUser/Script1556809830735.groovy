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

WebUI.click(findTestObject('Object Repository/Page_Spring Security Tutorial/button_Go To Registration Page'))

WebUI.setText(findTestObject('Object Repository/Page_Registration Form/input_Registration Form_name (1)'), 'kulisse11')

WebUI.setText(findTestObject('Object Repository/Page_Registration Form/input_Registration Form_email (1)'), 'kulisse11@mail.com')

WebUI.setEncryptedText(findTestObject('Object Repository/Page_Registration Form/input_Registration Form_password (1)'), 
    '3pybmnes8qQ=')

WebUI.click(findTestObject('Object Repository/Page_Registration Form/button_Register User (1)'))

WebUI.click(findTestObject('Object Repository/Page_Registration Form/span_User has been registered successfully'))

WebUI.closeBrowser()

