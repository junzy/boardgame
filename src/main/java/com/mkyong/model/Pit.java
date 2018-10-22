package com.mkyong.model;
public class Pit {
	private PitType type;
	private Integer stoneCount;
	private Integer position;
	
	public Pit(PitType type, Integer stoneCount, Integer position) {
		this.setType(type);
		this.setStoneCount(stoneCount);
		this.setPosition(position);
	}

	public Integer getStoneCount() {
		return stoneCount;
	}

	public void setStoneCount(Integer stoneCount) {
		this.stoneCount = stoneCount;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public PitType getType() {
		return type;
	}

	public void setType(PitType type) {
		this.type = type;
	}
}
