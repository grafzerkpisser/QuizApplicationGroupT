package enumerations;

public enum OpdrachtCategorie {
	Rekenen(1), NederlandseTaal(2), FranseTaal(3), AlgemeneKennis(4);
	private final int id;

	OpdrachtCategorie(int id) {
		this.id = id;
	}

	public int getValue() {
		return id;
	}

	public static String[] getValues() {
		String[] names = new String[OpdrachtCategorie.values().length];
		for (int i = 0; i < OpdrachtCategorie.values().length; i++) {
			names[i] = OpdrachtCategorie.values()[i].name();
		}
		return names;
	}
}
