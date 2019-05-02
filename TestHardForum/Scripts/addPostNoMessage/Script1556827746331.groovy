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

WebUI.setText(findTestObject('Page_Spring Security Tutorial/input_Welcome_name (2)'), 'user2')

WebUI.setEncryptedText(findTestObject('Page_Spring Security Tutorial/input_Welcome_password (2)'), 'jEDueeLlpJI=')

WebUI.click(findTestObject('1/button_Login'))

not_run: WebUI.sendKeys(findTestObject('Object Repository/input_Welcome_password (2) (3)'), Keys.chord(Keys.ENTER))

WebUI.click(findTestObject('Object Repository/a_Hardware        Le physique (2)'))

WebUI.setText(findTestObject('Object Repository/input_Title_name'), 'testTopic')

WebUI.click(findTestObject('Object Repository/p_Mon bon topic de test'))

WebUI.setText(findTestObject('Object Repository/div_Mon bon topic de test'), '<p style="">Mon bon topic de test</p>')

WebUI.click(findTestObject('Object Repository/button_Submit (1)'))

WebUI.click(findTestObject('Object Repository/a_testTopic (3)'))

WebUI.navigateToUrl('http://localhost:8080/login')

WebUI.click(findTestObject('Object Repository/div_HardForum      LoginAdvanced search   Search a topic                   Welcome     LoginORGo To Registration Page'))

WebUI.setText(findTestObject('Object Repository/input_Title_name'), 'testTopic')

url = WebUI.getUrl()

WebUI.click(findTestObject('Object Repository/button_Submit (1)'))

assert url == WebUI.getUrl()

