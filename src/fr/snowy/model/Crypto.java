package fr.snowy.model;

public enum Crypto implements Currency {
	
	ZEC,
	XBT,
	LTC,
	XRP,
	XLM,
	ETH,
	ETC,
	ICN,
	XMR,
	BCH;

	
	public static Currency getByName(String name)
	{
		for(Crypto currency : Crypto.values())
		{
			if (currency.toString().equalsIgnoreCase(name))
			{
				return currency;
			}
		}
		return null;
	}
	
	public String getUnit()
	{
		return this.toString().toLowerCase();
	}
	
}
