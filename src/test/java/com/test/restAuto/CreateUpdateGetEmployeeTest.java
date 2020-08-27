package com.test.restAuto;



import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dataProviders.EmployeeDataProvider;
import com.test.pojos.employee.request.CreateEmployee;
import com.test.pojos.employee.response.CreateEmployeeReponse;
import com.test.utils.Config;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateUpdateGetEmployeeTest {
	@Test(dataProvider="CreateEmployee",dataProviderClass=EmployeeDataProvider.class ,enabled=true)
	public void createEmployee(RequestSpecification request ,CreateEmployee createEmployeeRequestObject) throws IOException { 
		SoftAssert  sa = new SoftAssert();
		Response response=request.post();
		Reporter.log("Reponse String is : "+response.asString(), true);
		CreateEmployeeReponse createEmployeeReponseObject = new ObjectMapper().readValue(response.asString(), CreateEmployeeReponse.class);
		sa.assertEquals(response.getStatusCode(), 201, "Expected Status code is : 201"+ 
				"  while Actual Status code is : " +response.getStatusCode());
		AssertJUnit.assertEquals(createEmployeeReponseObject.getName(), createEmployeeRequestObject.getName(), "Expected Name is : " +createEmployeeRequestObject.getName() 
		+ "  while Actual Name  is : " +createEmployeeReponseObject.getName());
		Config.setConfigProperty("ID",createEmployeeReponseObject.getId());
		sa.assertAll();	
	}
	@Test(dataProvider="UpdateEmployee",dataProviderClass=EmployeeDataProvider.class ,enabled=true)
	public void updateEmployee(RequestSpecification request ,CreateEmployee updateEmployeeRequestObject) throws JsonMappingException, JsonProcessingException { 
		SoftAssert  sa = new SoftAssert();
		Response response=request.put();
		Reporter.log("Reponse String is : "+response.asString(), true);
		CreateEmployeeReponse updateEmployeeReponseObject = new ObjectMapper().readValue(response.asString(), CreateEmployeeReponse.class);
		sa.assertEquals(response.getStatusCode(), 200, "Expected Status code is : 200"+ 
				"  while Actual Status code is : " +response.getStatusCode());
		AssertJUnit.assertEquals(updateEmployeeReponseObject.getName(), updateEmployeeRequestObject.getName(), "Expected Age is : " +updateEmployeeRequestObject.getName() 
		+ "  while Actual Name  is : " +updateEmployeeReponseObject.getName());
		AssertJUnit.assertEquals(updateEmployeeReponseObject.getAge(), updateEmployeeRequestObject.getAge(), "Expected Age is : " +updateEmployeeRequestObject.getAge() 
		+ "  while Actual Age  is : " +updateEmployeeReponseObject.getAge());
		sa.assertAll();	
	}
	@Test(dataProvider="UpdateEmployee",dataProviderClass=EmployeeDataProvider.class ,enabled=true)
	public void getEmployee(RequestSpecification request) throws JsonMappingException, JsonProcessingException { 
		SoftAssert  sa = new SoftAssert();
		Response response=request.get();
		Reporter.log("Reponse String is : "+response.asString(), true);
		sa.assertEquals(response.getStatusCode(), 200, "Expected Status code is : 200"+ 
				"  while Actual Status code is : " +response.getStatusCode());
		sa.assertAll();	
	}
}
