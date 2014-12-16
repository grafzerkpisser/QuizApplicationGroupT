package enumerations;

public enum QuizStatus {
	IN_CONSTRUCTIE(1), AFGEWERKT(2), OPENGESTELD(3), LAATSTE_KANS(4), AFGESLOTEN(
			5);

	private final int id;

	QuizStatus(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}
}
