package com.advanz.erp.common.entity.jpa.util;

public enum BooleanEnum {
	TRUE(1),  FALSE(0);
	
    private final int id;
	BooleanEnum(int id) { this.id = id; }
    public int getValue() { return id; }
}
