package com.mkyong.model;

public class BoardGame {
	private Player player1;
	private Player player2;
	private Board board;
	private Player currentActivePlayer;

	public BoardGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player getCurrentActivePlayer() {
		return this.currentActivePlayer;
	}

	public void setCurrentActivePlayer(Player currentPlayer) {
		this.currentActivePlayer = currentPlayer;
	}

	public void toggleActivePlayer() {
		if (currentActivePlayer == player1) {
			this.currentActivePlayer = player2;
		} else {
			this.currentActivePlayer = player1;
		}
	}

}
