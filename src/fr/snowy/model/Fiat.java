package fr.snowy.model;

public enum Fiat implements Currency {
	EUR('€'), USD('$');

	private char unit;
	public static Fiat DEFAULT = Fiat.EUR;

	private Fiat(char unit) {
		this.unit = unit;
	}

	@Override
	public String getUnit() {
		return String.valueOf(this.unit);
	}

	public static Currency getByName(String name) {
		for(Fiat fiat : values())
		{
			if(fiat.toString().equalsIgnoreCase(name))
			{
				return fiat;
			}
		}
		return null;
	}
}
