class Student {

	private String number;

	public Student(String number) {

		this.number = number;
	}

	@Override
	public String toString() {
		return "Student [number=" + number + "]";
	}

	@Override
	public boolean equals(Object obj) {
		//System.out.println("From equals");

		if (obj != null && obj instanceof Student) {
			String num = ((Student) obj).getNumber();
			if (num != null && num.equals(this.getNumber()));
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		//System.out.println("From hashcode");
		return this.number.hashCode();
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
