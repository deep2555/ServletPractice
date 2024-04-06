package servletpractice;

public class Student {
	private String studentName;
	private String studentRollNumber;
	private String englishMarks;
	private String hindiMarks;
	private String chemistryMarks;
	
	public Student(String studentName, String studentRollNumber, String englishMarks, String hindiMarks,
			String chemistryMarks) {
		super();
		this.studentName = studentName;
		this.studentRollNumber = studentRollNumber;
		this.englishMarks = englishMarks;
		this.hindiMarks = hindiMarks;
		this.chemistryMarks = chemistryMarks;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentRollNumber() {
		return studentRollNumber;
	}

	public void setStudentRollNumber(String studentRollNumber) {
		this.studentRollNumber = studentRollNumber;
	}

	public String getEnglishMarks() {
		return englishMarks;
	}

	public void setEnglishMarks(String englishMarks) {
		this.englishMarks = englishMarks;
	}

	public String getHindiMarks() {
		return hindiMarks;
	}

	public void setHindiMarks(String hindiMarks) {
		this.hindiMarks = hindiMarks;
	}

	public String getChemistryMarks() {
		return chemistryMarks;
	}

	public void setChemistryMarks(String chemistryMarks) {
		this.chemistryMarks = chemistryMarks;
	}
	
}
