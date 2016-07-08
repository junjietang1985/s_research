package com.junjie.indicator;

public enum MovingAverageType
{
	FIVE_DAY(5, "ma5"), TEN_DAY(10, "ma10"), TWENTY_DAY(20, "ma20"), THIRTY_DAY(30, "ma30"), SIXTY_DAY(60, "ma60");

	private int value;
	private String dbColName;

	private MovingAverageType(int value, String dbColName)
	{
		this.value = value;
		this.dbColName = dbColName;
	}

	public int getValue()
	{
		return value;
	}

	public String getDbColName()
	{
		return dbColName;
	}

}
