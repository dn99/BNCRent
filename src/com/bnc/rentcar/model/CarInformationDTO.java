package com.bnc.rentcar.model;

public class CarInformationDTO {
	private int car_seq;             //��������ȣ
	private String car_name;		 //���̸�
	private String car_opic;         //�ڵ�������
	private String car_spic;         //�ڵ�������
	private int car_grade;           //�ڵ������(����,����,����,����)
	private int car_maker;           //�ڵ���ȸ��(����,���,���,�Ｚ,�ֿ�)
	private String car_disvolume;    //��ⷮ
	private String car_mile;         //���ο���
	private int car_fueltype;        //��������(�ֹ߷�,����,LPG) 
	private int car_numofpeople;     //�����ο�
	private int car_rentprice;       //��Ʈ����
	private int car_roffice;         //��Ʈ����
	private String car_detailopic;   //�ڵ����󼼻���
	private String car_detailspic;   //�ڵ����󼼻���
	private String car_content;      //�ڵ����󼼼���
	private String car_use;          //�ڵ��� ��뿩��
	
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
	