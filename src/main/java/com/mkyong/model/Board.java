package com.mkyong.model;

import java.util.List;

public class Board {
	public List<Pit> pits;
	
	public Integer getNumberOfStones(Integer position) {
		return pits.get(position).getStoneCount();
	}

	public void setNumberOfStones(int pitPosition, int stoneCount) {
		pits.get(pitPosition).setStoneCount(stoneCount);
	}

	public void incrementStoneCount(int pitPosition) {
		Integer stoneCount = pits.get(pitPosition).getStoneCount();
		pits.get(pitPosition).setStoneCount(stoneCount + 1);
	}
}
