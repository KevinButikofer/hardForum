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

WebUI.click(findTestObject('Object Repository/a_HardForum'))

WebUI.click(findTestObject('Object Repository/a_Advanced search'))

WebUI.setText(findTestObject('Object Repository/input_Author name_authorName'), 'kulisse6')

WebUI.setText(findTestObject('Object Repository/Page_Advanced Search/input_Topic name_topicName'), 'testTopic')

WebUI.click(findTestObject('Object Repository/input_Subforum_btn btn-primary col-5'))

WebUI.click(findTestObject('Object Repository/h5_testTopic'))
topicName = "testTopic"
topicName2 = WebUI.getText(findTestObject('Object Repository/a_testTopic'))

assert topicName == topicName2

WebUI.closeBrowser()

