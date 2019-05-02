import com.kms.katalon.core.logging.KeywordLogger
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.reporting.ReportUtil
import com.kms.katalon.core.main.TestCaseMain
import com.kms.katalon.core.testdata.TestDataColumn
import com.kms.katalon.core.testcase.TestCaseBinding
import com.kms.katalon.core.driver.internal.DriverCleanerCollector
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.configuration.RunConfiguration
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import internal.GlobalVariable as GlobalVariable

Map<String, String> suiteProperties = new HashMap<String, String>();


suiteProperties.put('id', 'Test Suites/katalonTest')

suiteProperties.put('name', 'katalonTest')

suiteProperties.put('description', '')
 

DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.webui.contribution.WebUiDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.mobile.contribution.MobileDriverCleaner())
DriverCleanerCollector.getInstance().addDriverCleaner(new com.kms.katalon.core.cucumber.keyword.internal.CucumberDriverCleaner())



RunConfiguration.setExecutionSettingFile("C:\\Dev\\JEE\\hardForum\\TestHardForum\\Reports\\katalonTest\\20190502_170628\\execution.properties")

TestCaseMain.beforeStart()

TestCaseMain.startTestSuite('Test Suites/katalonTest', suiteProperties, [new TestCaseBinding('Test Cases/addPost', 'Test Cases/addPost',  null), new TestCaseBinding('Test Cases/addTopic', 'Test Cases/addTopic',  null), new TestCaseBinding('Test Cases/alreadyRegisteredUser', 'Test Cases/alreadyRegisteredUser',  null), new TestCaseBinding('Test Cases/LoginNotRegistered', 'Test Cases/LoginNotRegistered',  null), new TestCaseBinding('Test Cases/LoginRegisteredUser', 'Test Cases/LoginRegisteredUser',  null), new TestCaseBinding('Test Cases/search', 'Test Cases/search',  null)])
