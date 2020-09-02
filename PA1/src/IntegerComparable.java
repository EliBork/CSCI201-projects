

public abstract class IntegerComparable {
	public abstract int getCompareValue();
	
	public int compareTo(int value) {
		return this.getCompareValue() - value;
	}
	
	public int compareTo(IntegerComparable other) {
		return this.getCompareValue() - other.getCompareValue();
	}
}
