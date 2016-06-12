package com.bnc.travel.model;

/**
 * 여행상품 예약
 */
public class TRentDTO
{
	private int trent_seq;
	private String trent_member_id;
	private int trent_tour_seq;
	private int trent_rent_seq;
	private int trent_car_seq;
	private String trent_checkinday;
	private String trent_checkoutday;
	private int trent_sumpeople;
	private int trent_totalprice;
	private String trent_note;
	
	public int getTrent_seq()
	{
		return trent_seq;
	}
	public void setTrent_seq( int trent_seq )
	{
		this.trent_seq = trent_seq;
	}
	public String getTrent_member_id()
	{
		return trent_member_id;
	}
	public void setTrent_member_id( String trent_member_id )
	{
		this.trent_member_id = trent_member_id;
	}
	public int getTrent_tour_seq()
	{
		return trent_tour_seq;
	}
	public void setTrent_tour_seq( int trent_tour_seq )
	{
		this.trent_tour_seq = trent_tour_seq;
	}
	public int getTrent_rent_seq()
	{
		return trent_rent_seq;
	}
	public void setTrent_rent_seq( int trent_rent_seq )
	{
		this.trent_rent_seq = trent_rent_seq;
	}
	public int getTrent_car_seq()
	{
		return trent_car_seq;
	}
	public void setTrent_car_seq( int trent_car_seq )
	{
		this.trent_car_seq = trent_car_seq;
	}
	public String getTrent_checkinday()
	{
		return trent_checkinday;
	}
	public void setTrent_checkinday( String trent_checkinday )
	{
		this.trent_checkinday = trent_checkinday;
	}
	public String getTrent_checkoutday()
	{
		return trent_checkoutday;
	}
	public void setTrent_checkoutday( String trent_checkoutday )
	{
		this.trent_checkoutday = trent_checkoutday;
	}
	public int getTrent_sumpeople()
	{
		return trent_sumpeople;
	}
	public void setTrent_sumpeople( int trent_sumpeople )
	{
		this.trent_sumpeople = trent_sumpeople;
	}
	public int getTrent_totalprice()
	{
		return trent_totalprice;
	}
	public void setTrent_totalprice( int trent_totalprice )
	{
		this.trent_totalprice = trent_totalprice;
	}
	public String getTrent_note()
	{
		return trent_note;
	}
	public void setTrent_note( String trent_note )
	{
		this.trent_note = trent_note;
	}
}
