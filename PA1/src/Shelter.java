
public class Shelter extends IntegerComparable {
	
	private Integer chiralFrequency;
	
	private Boolean timefall;
	
	private String guid;
	
	private String name;
	
	private String phone;
	
	private String address;
	
	@Override
	public boolean equals(Object o) {
		if( o == this) {
			return true;
		}
		
		if(!(o instanceof Shelter)) {
			return false;
		}
		
		Shelter s = (Shelter)o;
		
		if(Integer.compare(chiralFrequency, s.chiralFrequency) == 0 && Boolean.compare(timefall, s.timefall) == 0 && guid.compareTo(s.guid) == 0 
				&& name.compareTo(s.name) == 0 && phone.compareTo(s.phone) == 0 && address.compareTo(s.address) == 0) {
			return true;
		}
		
		return false;
	}

	public Integer getChiralFrequency() {
	return chiralFrequency;
	}

	public void setChiralFrequency(Integer chiralFrequency) {
	this.chiralFrequency = chiralFrequency;
	}

	public Boolean getTimefall() {
	return timefall;
	}

	public void setTimefall(Boolean timefall) {
	this.timefall = timefall;
	}

	public String getGuid() {
	return guid;
	}

	public void setGuid(String guid) {
	this.guid = guid;
	}

	public String getName() {
	return name;
	}

	public void setName(String name) {
	this.name = name;
	}

	public String getPhone() {
	return phone;
	}

	public void setPhone(String phone) {
	this.phone = phone;
	}

	public String getAddress() {
	return address;
	}

	public void setAddress(String address) {
	this.address = address;
	}
	
	/**
	 * String representation of a shelter
	 */
	public String toString() {		
		String out = new String("");
		out = out.concat("- Chiral Frequency: " + chiralFrequency.toString());
		out = out.concat("\n- Timefall: " + timefall.toString());
		out = out.concat("\n- GUID: " + guid.toString());
		out = out.concat("\n- Name: " + name.toString());
		out = out.concat("\n- Phone: " + phone.toString());
		out = out.concat("\n- Address: " + address.toString() + "\n");
		return out;
	}

	/**
	 * Returns integer value to compare on
	 */
	@Override
	public int getCompareValue() {
		return chiralFrequency;
	}


}