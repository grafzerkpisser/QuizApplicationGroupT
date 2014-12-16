package enumerations;

public enum Leerjaar {
	eerste(1), tweede(2), derde(3), vierde(4), vijfde(5), zesde(6);
	private final int jaarID;

	Leerjaar(int jaarID) {
		this.jaarID = jaarID;
	}

	public int getValue() {
		return jaarID;
	}
}
