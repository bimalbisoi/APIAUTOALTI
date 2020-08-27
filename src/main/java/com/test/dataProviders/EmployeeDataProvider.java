package com.test.dataProviders;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.pojos.employee.request.CreateEmployee;
import com.test.utils.Config;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class EmployeeDataProvider {


	@DataProvider(name="CreateEmployee")
	public static  Object[] createEmployee() throws JsonProcessingException{
		EmployeeRequestBuilder empReqBuld= new EmployeeRequestBuilder();
		empReqBuld.getCreateEmployee().setName(Config.getConfigProperty("EMPLOYEE_NAME"));
		empReqBuld.getCreateEmployee().setSalary(Config.getConfigProperty("EMPLOYEE_SALARY"));
		empReqBuld.getCreateEmployee().setAge(Config.getConfigProperty("EMPLOYEE_AGE"));
		CreateEmployee createEmployeeRequest=empReqBuld.getCreateEmployee();
		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(createEmployeeRequest);
		RequestSpecification request=RestAssured.given().baseUri(Config.getConfigProperty("END_POINT_URL")).basePath(Config.getConfigProperty("PATH_POST")).header("Content-Type", "application/json")
				.body(payload);

		return new Object[][] {{request,createEmployeeRequest}};


	}
	@DataProvider(name="UpdateEmployee")
	public static  Object[] updateEmployee() throws JsonProcessingException{
		EmployeeRequestBuilder empReqBuld= new EmployeeRequestBuilder();
		empReqBuld.getCreateEmployee().setName(Config.getConfigProperty("EMPLOYEE_NAME_UPDATED"));
		empReqBuld.getCreateEmployee().setSalary(Config.getConfigProperty("EMPLOYEE_SALARY"));
		empReqBuld.getCreateEmployee().setAge(Config.getConfigProperty("EMPLOYEE_AGE_UPDATED"));
		CreateEmployee updateEmployeeRequest=empReqBuld.getCreateEmployee();
		ObjectMapper mapper = new ObjectMapper();
		String payload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateEmployeeRequest);
		String pathUpdate=Config.getConfigProperty("PATH_UPDATE")+Config.getConfigProperty("ID");
		RequestSpecification request=RestAssured.given().baseUri(Config.getConfigProperty("END_POINT_URL")).basePath(pathUpdate).header("Content-Type", "application/json")
				.body(payload);

		return new Object[][] {{request,updateEmployeeRequest}};


	}
	@DataProvider(name="getEmployee")
	public static  Object[] getEmployee() throws JsonProcessingException{
		String pathget=Config.getConfigProperty("PATH_GET")+Config.getConfigProperty("ID");
		RequestSpecification getrequest=RestAssured.given().baseUri(Config.getConfigProperty("END_POINT_URL")).basePath(pathget);
		return new Object[][] {{getrequest}};


	}
}
