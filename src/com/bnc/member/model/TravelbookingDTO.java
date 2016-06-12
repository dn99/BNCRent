package com.bnc.member.model;

public class TravelbookingDTO {
	private int tour_seq; 				//번호
	private int tour_location; 			//숙박지역
	private String tour_name;  			//숙박업소명
	private String tour_tel;   			//숙박업소 전화번호
	private String tour_addr;			//숙박업소 주소
	private int tour_roomtype;			//객실타입
	private int tour_roomsize;			//객실크기
	private int tour_numofpeople;  		//객실수용가능인원
	private int tour_price;  			//객실요금
	private String tour_opic;			//사진
	private String tour_spic;			//사진
	private String tour_detailopic1;	//상세사진
	private String tour_detailspic1;	//상세사진
	private String tour_detailopic2;	//상세사진
	private String tour_detailspic2;	//상세사진
	private String tour_content;		//상품 상세 설명
	private String tour_use;            //예약 여부
	
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
	public String getRent_rtime() {
		return rent_rtime;
	}
	public void setRent_rtime(String rent_rtime) {
		this.rent_rtime = rent_rtime;
	}
	public String getRent_retday() {
		return rent_retday;
	}
	public void setRent_retday(String rent_retday) {
		this.rent_retday = rent_retday;
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
	public int getRent_roffice() {
		return rent_roffice;
	}
	public void setRent_roffice(int rent_roffice) {
		this.rent_roffice = rent_roffice;
	}
	public int getRent_retlocation() {
		return rent_retlocation;
	}
	public void setRent_retlocation(int rent_retlocation) {
		this.rent_retlocation = rent_retlocation;
	}
	public int getRent_retoffice() {
		return rent_retoffice;
	}
	public void setRent_retoffice(int rent_retoffice) {
		this.rent_retoffice = rent_retoffice;
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
	public int getRent_totalprice() {
		return rent_totalprice;
	}
	public void setRent_totalprice(int rent_totalprice) {
		this.rent_totalprice = rent_totalprice;
	}
	public int getRent_dc() {
		return rent_dc;
	}
	public void setRent_dc(int rent_dc) {
		this.rent_dc = rent_dc;
	}
	public int getTrent_seq() {
		return trent_seq;
	}
	public void setTrent_seq(int trent_seq) {
		this.trent_seq = trent_seq;
	}
	public String getTrent_member_id() {
		return trent_member_id;
	}
	public void setTrent_member_id(String trent_member_id) {
		this.trent_member_id = trent_member_id;
	}
	public int getTrent_tour_seq() {
		return trent_tour_seq;
	}
	public void setTrent_tour_seq(int trent_tour_seq) {
		this.trent_tour_seq = trent_tour_seq;
	}
	public int getTrent_rent_seq() {
		return trent_rent_seq;
	}
	public void setTrent_rent_seq(int trent_rent_seq) {
		this.trent_rent_seq = trent_rent_seq;
	}
	public int getTrent_car_seq() {
		return trent_car_seq;
	}
	public void setTrent_car_seq(int trent_car_seq) {
		this.trent_car_seq = trent_car_seq;
	}
	public String getTrent_checkinday() {
		return trent_checkinday;
	}
	public void setTrent_checkinday(String trent_checkinday) {
		this.trent_checkinday = trent_checkinday;
	}
	public String getTrent_checkoutday() {
		return trent_checkoutday;
	}
	public void setTrent_checkoutday(String trent_checkoutday) {
		this.trent_checkoutday = trent_checkoutday;
	}
	public int getTrent_sumpeople() {
		return trent_sumpeople;
	}
	public void setTrent_sumpeople(int trent_sumpeople) {
		this.trent_sumpeople = trent_sumpeople;
	}
	public int getTrent_totalprice() {
		return trent_totalprice;
	}
	public void setTrent_totalprice(int trent_totalprice) {
		this.trent_totalprice = trent_totalprice;
	}
	public String getTrent_note() {
		return trent_note;
	}
	public void setTrent_note(String trent_note) {
		this.trent_note = trent_note;
	}
	public int getTour_seq() {
		return tour_seq;
	}
	public void setTour_seq(int tour_seq) {
		this.tour_seq = tour_seq;
	}
	public int getTour_location() {
		return tour_location;
	}
	public void setTour_location(int tour_location) {
		this.tour_location = tour_location;
	}
	public String getTour_name() {
		return tour_name;
	}
	public void setTour_name(String tour_name) {
		this.tour_name = tour_name;
	}
	public String getTour_tel() {
		return tour_tel;
	}
	public void setTour_tel(String tour_tel) {
		this.tour_tel = tour_tel;
	}
	public String getTour_addr() {
		return tour_addr;
	}
	public void setTour_addr(String tour_addr) {
		this.tour_addr = tour_addr;
	}
	public int getTour_roomtype() {
		return tour_roomtype;
	}
	public void setTour_roomtype(int tour_roomtype) {
		this.tour_roomtype = tour_roomtype;
	}
	public int getTour_roomsize() {
		return tour_roomsize;
	}
	public void setTour_roomsize(int tour_roomsize) {
		this.tour_roomsize = tour_roomsize;
	}
	public int getTour_numofpeople() {
		return tour_numofpeople;
	}
	public void setTour_numofpeople(int tour_numofpeople) {
		this.tour_numofpeople = tour_numofpeople;
	}
	public int getTour_price() {
		return tour_price;
	}
	public void setTour_price(int tour_price) {
		this.tour_price = tour_price;
	}
	public String getTour_opic() {
		return tour_opic;
	}
	public void setTour_opic(String tour_opic) {
		this.tour_opic = tour_opic;
	}
	public String getTour_spic() {
		return tour_spic;
	}
	public void setTour_spic(String tour_spic) {
		this.tour_spic = tour_spic;
	}
	public String getTour_detailopic1() {
		return tour_detailopic1;
	}
	public void setTour_detailopic1(String tour_detailopic1) {
		this.tour_detailopic1 = tour_detailopic1;
	}
	public String getTour_detailspic1() {
		return tour_detailspic1;
	}
	public void setTour_detailspic1(String tour_detailspic1) {
		this.tour_detailspic1 = tour_detailspic1;
	}
	public String getTour_detailopic2() {
		return tour_detailopic2;
	}
	public void setTour_detailopic2(String tour_detailopic2) {
		this.tour_detailopic2 = tour_detailopic2;
	}
	public String getTour_detailspic2() {
		return tour_detailspic2;
	}
	public void setTour_detailspic2(String tour_detailspic2) {
		this.tour_detailspic2 = tour_detailspic2;
	}
	public String getTour_content() {
		return tour_content;
	}
	public void setTour_content(String tour_content) {
		this.tour_content = tour_content;
	}
	public String getTour_use() {
		return tour_use;
	}
	public void setTour_use(String tour_use) {
		this.tour_use = tour_use;
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
	
	
}
