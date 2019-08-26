package com.example.demo.pojo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Alias("bbb")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Boy {

	private Integer bId;

	private Integer bHeight;

	private String bName;

	public Integer getbId() {
		return bId;
	}

	public void setbId(Integer bId) {
		this.bId = bId;
	}

	public Integer getbHeight() {
		return bHeight;
	}

	public void setbHeight(Integer bHeight) {
		this.bHeight = bHeight;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

}
