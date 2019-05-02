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

WebUI.navigateToUrl('http://localhost:8080/forum')

WebUI.setText(findTestObject('Page_Insert title here/input_Advanced search_searchText'), 'testTopic')

WebUI.sendKeys(findTestObject('Page_Insert title here/input_Advanced search_searchText'), Keys.chord(Keys.ENTER))

topicName = WebUI.getText(findTestObject('Page_Insert title here/h5_testTopic'))

WebUI.click(findTestObject('Page_Insert title here/h5_testTopic'))

topicName2 = WebUI.getText(findTestObject('Page_Spring Security Tutorial/a_testTopic'))

assert topicName == topicName2

WebUI.closeBrowser()

