
package rest_API

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import static org.assertj.core.api.Assertions.*
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.util.KeywordUtil
import groovy.json.JsonSlurper
import com.kms.katalon.core.testobject.RequestObject


public class Users {


	
	public static String  getUser = 'Object Repository/API/Rest_Reqres/GetUser'
	public static String  getSingleUser = 'Object Repository/API/Rest_Reqres/SingleUser'
	public static String createUser = 'Object Repository/API/Rest_Reqres/CreateUser'
	public static String updateUser = 'Object Repository/API/Rest_Reqres/UpdateUser'
	public static String deleteUser = 'Object Repository/API/Rest_Reqres/DeleteUser'

	
	
	
	@Keyword
	def static getUser() {

		def responseGetUser = WS.sendRequest(findTestObject(getUser))
		
		String apiRequestUrl = (findTestObject(getUser)).getRestUrl()
		WS.comment("The api request url is : " + apiRequestUrl)
		
		def responseValue = responseGetUser.getResponseText()
		WS.comment("Value of complete response is : " + responseValue)

		WS.verifyResponseStatusCode(responseGetUser, 200)
		
// here if we want to save any particular value from the response and want to use that value in other apis we can do below thing using passing them in global variable) 
	
		def email =	WS.getElementPropertyValue(responseGetUser, 'data[0].email')
		GlobalVariable.email = email
		WS.comment("Value is : " + email)
		
		def id = WS.getElementPropertyValue(responseGetUser, 'data[0].id')
		GlobalVariable.id = id
		WS.comment("Value is : " + id)
		
		def firstName = WS.getElementPropertyValue(responseGetUser, 'data[0].first_name')
		GlobalVariable.firstName = firstName
		WS.comment("Value is : " + firstName)
		
		def lastName = WS.getElementPropertyValue(responseGetUser, 'data[0].last_name')
		GlobalVariable.lastName = lastName
		WS.comment("Value is : " + lastName)
		
		
	}

	
	@Keyword
	def static getSingleUser() {

		def responseSingleUser = WS.sendRequest(findTestObject(getSingleUser))

		String apiRequestUrl = (findTestObject(getSingleUser)).getRestUrl()
		WS.comment("The api request url is " + apiRequestUrl)
		
		
		def responseValue = responseSingleUser.getResponseText()
		WS.comment("Value of complete response is " + responseValue)
		
		WS.verifyResponseStatusCode(responseSingleUser, 200)
		WS.getElementPropertyValue(responseSingleUser, 'data.id')
		WS.getElementPropertyValue(responseSingleUser, 'data.email')
		WS.getElementPropertyValue(responseSingleUser, 'data.first_name')
		WS.getElementPropertyValue(responseSingleUser, 'data.last_name')
	}
	
	
	@Keyword
	def static createUser() {
		
		
		def responseCreateUser = WS.sendRequest(findTestObject(createUser))
		
		String apiRequestUrl = (findTestObject(createUser)).getRestUrl()
		WS.comment("The api request url is " + apiRequestUrl)
		
		String apiRequestBody = (findTestObject(createUser)).getBodyContent().getText()
		WS.comment("The api request url is " + apiRequestBody)
		
		
		def responseValue = responseCreateUser.getResponseText()
		WS.comment("Value of complete response is " + responseValue)

		WS.verifyResponseStatusCode(responseCreateUser, 201)
	}




	@Keyword
	def updateUser() {
		
		def username = GlobalVariable.updateUserName
		def updatedjob = GlobalVariable.updatejob

		def responseUpdateUser = WS.sendRequest(findTestObject(updateUser))
		
		String apiRequestUrl = (findTestObject(updateUser)).getRestUrl()
		WS.comment("The api request url is " + apiRequestUrl)
		
		String apiRequestBody = (findTestObject(updateUser)).getBodyContent().getText()
		WS.comment("The api request url is " + apiRequestBody)
		
		
		def responseValue = responseUpdateUser.getResponseText()
		WS.comment("Value of complete response is " + responseValue)

		WS.verifyResponseStatusCode(responseUpdateUser, 200)			
		WS.verifyElementPropertyValue(responseUpdateUser, 'name', username)
		WS.verifyElementPropertyValue(responseUpdateUser, 'job',updatedjob)
			

	}
	
	
	@Keyword
	def static deleteUser() {
		
		
		def responseCreateUser = WS.sendRequest(findTestObject(deleteUser))
		
		String apiRequestUrl = (findTestObject(deleteUser)).getRestUrl()
		WS.comment("The api request url is " + apiRequestUrl)

		WS.verifyResponseStatusCode(responseCreateUser, 204)
	}
}
