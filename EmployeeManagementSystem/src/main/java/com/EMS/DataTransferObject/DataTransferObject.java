package com.EMS.DataTransferObject;

public class DataTransferObject {

	private int id;

	private String name;

	private String designation;

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", name=" + name + ", designation=" + designation + ", salary=" + salary + "]";
	}

	public int getId() {

		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	private double salary;

}
