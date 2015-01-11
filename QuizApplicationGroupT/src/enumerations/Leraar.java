package enumerations;

public enum Leraar {
	Eli(1),
	Patrick(2),
	Gavin(3),
	Brecht(4),
	Bart(5),
	eli(6),
	patrick(7),
	bart(8),
	brecht(9),
	gavin(10)
	;
	private final int id;
	Leraar(int id){this.id = id;}
	public int getValue(){return id;}
}
