package cn.mldn.dibmp.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class StorageApplyDetails implements Serializable{
	private Long sadid;
	private Long gid;
	private String name;
	private Integer num;
	private Double price;
	private Double weight;
	public StorageApplyDetails() {
	}
	public StorageApplyDetails(Long sadid, Long gid, String name, Integer num, Double price, Double weight) {
		super();
		this.sadid = sadid;
		this.gid = gid;
		this.name = name;
		this.num = num;
		this.price = price;
		this.weight = weight;
	}
	@Override
	public String toString() {
		return "Storage_apply_details [sadid=" + sadid + ", gid=" + gid + ", name=" + name + ", num=" + num + ", price="
				+ price + ", weight=" + weight + "]";
	}
	public Long getSadid() {
		return sadid;
	}
	public void setSadid(Long sadid) {
		this.sadid = sadid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
	
}
