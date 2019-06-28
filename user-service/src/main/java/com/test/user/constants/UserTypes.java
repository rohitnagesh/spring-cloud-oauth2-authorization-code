package com.test.user.constants;

public enum UserTypes {

	ADMIN("Admin"), T1("Tier 1"), T2("Tier 2");

	private final String type;

	private UserTypes(final String type) {
		this.type = type;
	}

	public boolean equals(String type) {
		return (type == null) ? false : this.type.equals(type);
	}

	@Override
	public String toString() {
		return this.type;
	}

}