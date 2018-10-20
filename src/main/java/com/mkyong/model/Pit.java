package com.mkyong.model;
public class Pit {
	private PitType type;
	private Integer stoneCount;
	private Integer position;
	
	public Pit(PitType type, Integer stoneCount, Integer position) {
		this.type = type;
		this.setStoneCount(stoneCount);
		this.position = position;
	}

	public Integer getStoneCount() {
		return stoneCount;
	}

	public void setStoneCount(Integer stoneCount) {
		this.stoneCount = stoneCount;
	}
}
