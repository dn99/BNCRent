package com.bnc.amf;

import java.io.Serializable;

public class Employee implements Serializable
{
	public String name;
	public String phone;
	public String email;

	public Employee()
	{

	}

	public Employee( String name, String phone, String email )
	{
		this.name = name;
		this.phone = phone;
		this.email = email;
	}
}
