package cn.mldn.dibmp.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class StorageRecord implements Serializable{
	private Long srid;
	private Long said;
	private Long gid;
	private String name;
	private Integer num;
	private Double price;
	private Double weight;
	//审核之后的状态此处进行假设 1:表示审核通过；0:表示审核不通过
	private Integer status;
	//审核人提交日期
	private Date indate;
	private String inmid;
	public StorageRecord() {
	}
	@Override
	public String toString() {
		return "StorageRecord [srid=" + srid + ", said=" + said + ", gid=" + gid + ", name=" + name + ", num=" + num
				+ ", price=" + price + ", weight=" + weight + ", status=" + status + ", indate=" + indate + ", inmid="
				+ inmid + "]";
	}
	public Long getSrid() {
		return srid;
	}
	public void setSrid(Long srid) {
		this.srid = srid;
	}
	public Long getSaid() {
		return said;
	}
	public void setSaid(Long said) {
		this.said = said;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public String getInmid() {
		return inmid;
	}
	public void setInmid(String inmid) {
		this.inmid = inmid;
	}
}
