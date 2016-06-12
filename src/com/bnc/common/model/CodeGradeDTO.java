package com.bnc.common.model;

public class CodeGradeDTO extends CodeInfoDTO
{
	private String grade_icon;
	private int grade_dc;
	
	public String getGrade_icon()
	{
		return grade_icon;
	}
	public void setGrade_icon( String grade_icon )
	{
		this.grade_icon = grade_icon;
	}
	public int getGrade_dc()
	{
		return grade_dc;
	}
	public void setGrade_dc( int grade_dc )
	{
		this.grade_dc = grade_dc;
	}
}
