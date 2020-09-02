import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Shelter extends IntegerComparable {
	@SerializedName("chiralFrequency")
	@Expose
	private Integer chiralFrequency;
	@SerializedName("timefall")
	@Expose
	private Boolean timefall;
	@SerializedName("guid")
	@Expose
	private String guid;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("phone")
	@Expose
	private String phone;
	@SerializedName("address")
	@Expose
	private String address;

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
		return null;
	}

	/**
	 * Returns integer value to compare on
	 */
	@Override
	public int getCompareValue() {
		return null;
	}


}