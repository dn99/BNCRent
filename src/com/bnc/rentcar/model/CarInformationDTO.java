package com.bnc.rentcar.model;

public class CarInformationDTO {
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
	
	public String getCar_use() {
		return car_use;
	}
	public void setCar_use(String car_use) {
		this.car_use = car_use;
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
}
	