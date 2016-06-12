package com.bnc.member.model;

public class BookingDTO {
	private int rent_seq;
	private String rent_member_id;
	private int rent_car_seq;
	private String rent_rday;
	private String rent_rtime;
	private String rent_retday;
	private String rent_rettime;
	private int rent_rlocation;
	private int rent_roffice;
	private int rent_retlocation;
	private int rent_retoffice;
	private String rent_navicheck;
	private int rent_insurance_seq;
	private int rent_totalday;
	private int rent_totaltime;
	private int rent_price;
	private int rent_totalprice;
	private int rent_dc;
	
	private int car_seq;             //시퀀스번호
	private String car_name;		 //차이름
	private String car_opic;         //자동차사진
	private String car_spic;         //자동차사진
	private int car_grade;           //자동차등급(소형,중형,대형,승합)
	private int car_maker;           //자동차회사(현대,기아,대우,삼성,쌍용)
	private String car_disvolume;    //배기량
	private String car_mile;         //공인연비
	private int car_fueltype;        //연료종류(휘발류,디젤,LPG) 
	private int car_numofpeople;     //승차인원
	private int car_rentprice;       //랜트가격
	private int car_roffice;         //랜트지점
	private String car_detailopic;   //자동차상세사진
	private String car_detailspic;   //자동차상세사진
	private String car_content;      //자동차상세설명
	private String car_use;          //자동차 사용여부
	
	
	
	
	
	public int getRent_roffice() {
		return rent_roffice;
	}
	public int getRent_retoffice() {
		return rent_retoffice;
	}
	public String getRent_rtime() {
		return rent_rtime;
	}
	public void setRent_rtime(String rent_rtime) {
		this.rent_rtime = rent_rtime;
	}
	public String getRent_rettime() {
		return rent_rettime;
	}
	public void setRent_rettime(String rent_rettime) {
		this.rent_rettime = rent_rettime;
	}
	public int getRent_rlocation() {
		return rent_rlocation;
	}
	public void setRent_rlocation(int rent_rlocation) {
		this.rent_rlocation = rent_rlocation;
	}
	public int getRent_retlocation() {
		return rent_retlocation;
	}
	public void setRent_retlocation(int rent_retlocation) {
		this.rent_retlocation = rent_retlocation;
	}
	public String getRent_navicheck() {
		return rent_navicheck;
	}
	public void setRent_navicheck(String rent_navicheck) {
		this.rent_navicheck = rent_navicheck;
	}
	public int getRent_insurance_seq() {
		return rent_insurance_seq;
	}
	public void setRent_insurance_seq(int rent_insurance_seq) {
		this.rent_insurance_seq = rent_insurance_seq;
	}
	public int getRent_totalday() {
		return rent_totalday;
	}
	public void setRent_totalday(int rent_totalday) {
		this.rent_totalday = rent_totalday;
	}
	public int getRent_totaltime() {
		return rent_totaltime;
	}
	public void setRent_totaltime(int rent_totaltime) {
		this.rent_totaltime = rent_totaltime;
	}
	public int getRent_price() {
		return rent_price;
	}
	public void setRent_price(int rent_price) {
		this.rent_price = rent_price;
	}
	public int getRent_dc() {
		return rent_dc;
	}
	public void setRent_dc(int rent_dc) {
		this.rent_dc = rent_dc;
	}
	public int getCar_seq() {
		return car_seq;
	}
	public void setCar_seq(int car_seq) {
		this.car_seq = car_seq;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public String getCar_opic() {
		return car_opic;
	}
	public void setCar_opic(String car_opic) {
		this.car_opic = car_opic;
	}
	public String getCar_spic() {
		return car_spic;
	}
	public void setCar_spic(String car_spic) {
		this.car_spic = car_spic;
	}
	public int getCar_grade() {
		return car_grade;
	}
	public void setCar_grade(int car_grade) {
		this.car_grade = car_grade;
	}
	public int getCar_maker() {
		return car_maker;
	}
	public void setCar_maker(int car_maker) {
		this.car_maker = car_maker;
	}
	public String getCar_disvolume() {
		return car_disvolume;
	}
	public void setCar_disvolume(String car_disvolume) {
		this.car_disvolume = car_disvolume;
	}
	public String getCar_mile() {
		return car_mile;
	}
	public void setCar_mile(String car_mile) {
		this.car_mile = car_mile;
	}
	public int getCar_fueltype() {
		return car_fueltype;
	}
	public void setCar_fueltype(int car_fueltype) {
		this.car_fueltype = car_fueltype;
	}
	public int getCar_numofpeople() {
		return car_numofpeople;
	}
	public void setCar_numofpeople(int car_numofpeople) {
		this.car_numofpeople = car_numofpeople;
	}
	public int getCar_rentprice() {
		return car_rentprice;
	}
	public void setCar_rentprice(int car_rentprice) {
		this.car_rentprice = car_rentprice;
	}
	public int getCar_roffice() {
		return car_roffice;
	}
	public void setCar_roffice(int car_roffice) {
		this.car_roffice = car_roffice;
	}
	public String getCar_detailopic() {
		return car_detailopic;
	}
	public void setCar_detailopic(String car_detailopic) {
		this.car_detailopic = car_detailopic;
	}
	public String getCar_detailspic() {
		return car_detailspic;
	}
	public void setCar_detailspic(String car_detailspic) {
		this.car_detailspic = car_detailspic;
	}
	public String getCar_content() {
		return car_content;
	}
	public void setCar_content(String car_content) {
		this.car_content = car_content;
	}
	public String getCar_use() {
		return car_use;
	}
	public void setCar_use(String car_use) {
		this.car_use = car_use;
	}
	public void setRent_roffice(int rent_roffice) {
		this.rent_roffice = rent_roffice;
	}
	public void setRent_retoffice(int rent_retoffice) {
		this.rent_retoffice = rent_retoffice;
	}
	public int getRent_seq() {
		return rent_seq;
	}
	public void setRent_seq(int rent_seq) {
		this.rent_seq = rent_seq;
	}
	public String getRent_member_id() {
		return rent_member_id;
	}
	public void setRent_member_id(String rent_member_id) {
		this.rent_member_id = rent_member_id;
	}
	public int getRent_car_seq() {
		return rent_car_seq;
	}
	public void setRent_car_seq(int rent_car_seq) {
		this.rent_car_seq = rent_car_seq;
	}
	public String getRent_rday() {
		return rent_rday;
	}
	public void setRent_rday(String rent_rday) {
		this.rent_rday = rent_rday;
	}
	
	public String getRent_retday() {
		return rent_retday;
	}
	public void setRent_retday(String rent_retday) {
		this.rent_retday = rent_retday;
	}
	public int getRent_totalprice() {
		return rent_totalprice;
	}
	public void setRent_totalprice(int rent_totalprice) {
		this.rent_totalprice = rent_totalprice;
	}
}
