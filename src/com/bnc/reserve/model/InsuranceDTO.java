package com.bnc.reserve.model;

public class InsuranceDTO
{
	private int insurance_seq;
	private String insurance_type;
	private int insurance_price;
	
	public int getInsurance_seq()
	{
		return insurance_seq;
	}
	public void setInsurance_seq( int insurance_seq )
	{
		this.insurance_seq = insurance_seq;
	}
	public String getInsurance_type()
	{
		return insurance_type;
	}
	public void setInsurance_type( String insurance_type )
	{
		this.insurance_type = insurance_type;
	}
	public int getInsurance_price()
	{
		return insurance_price;
	}
	public void setInsurance_price( int insurance_price )
	{
		this.insurance_price = insurance_price;
	}
}
