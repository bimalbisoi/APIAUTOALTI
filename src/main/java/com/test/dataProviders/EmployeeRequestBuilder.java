package com.test.dataProviders;

import com.test.pojos.employee.request.CreateEmployee;

public class EmployeeRequestBuilder {
	CreateEmployee createEmployee;
	public EmployeeRequestBuilder() {
		createEmployee= new CreateEmployee();
	}
	public CreateEmployee getCreateEmployee() {
		return this.createEmployee;
	}

	public void setCreateEmployee(CreateEmployee createEmployee) {
		this.createEmployee = createEmployee;
	}
}
