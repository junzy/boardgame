package com.bol.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public List<Pit> pits = new ArrayList<>();
	
	
	public Integer getNumberOfStones(Integer position) {
		return pits.get(position).getStoneCount();
	}

	public List<Pit> getPits() {
		return pits;
	}
	
	public void addPit(Pit pit) {
		this.pits.add(pit);
	}
	
	public void setNumberOfStones(int pitPosition, int stoneCount) {
		pits.get(pitPosition).setStoneCount(stoneCount);
	}

	public void addToStoneCount(int pitPosition, int stonesToAdd) {
		Integer stoneCount = pits.get(pitPosition).getStoneCount();
		pits.get(pitPosition).setStoneCount(stoneCount + stonesToAdd);
	}
}
