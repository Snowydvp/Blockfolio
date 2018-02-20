package fr.snowy.model;

public enum Currency {
	
	EUR ("EUR", '€'), 
	USD ("USD", '$'),
	
	ZEC ("ZEC", 'z'),
	LTC ("LTC", 'l'),
	XRP ("XRP", 'x'),
	XLM ("XLM", 'l'),
	ETH ("ETH", 'e'),
	ETC ("ETC", 'e'),
	ICN ("ICN", 'i'),
	XMR ("XMR", 'm'),
	BCH ("BCH", 'b'),
	XBT ("XBT", 'B'),

	UNKNOW ("UKNOW", '?');
	
	private String currencyName = "";
	private char unit;
	
	Currency(String currencyName, char unit)
	{
		this.currencyName = currencyName;
		this.unit = unit;
	}
	
	public static Currency getByName(String name)
	{
		Currency cur = null;
		for(Currency currency : Currency.values())
			if (currency.currencyName.equalsIgnoreCase(name))
			{
				cur = currency;
			}
		if(cur == null)
		{
			cur = UNKNOW;
			System.out.println("UNKNOWN: " + name);
		}
		return cur;
	}
	
	public String getName()
	{
		return this.name();
	}
	
	public char getUnit()
	{
		return unit;
	}

}
