package Entity;

import java.util.Objects;

public class Seat {
	private String Id;
	private String Planetailnumber; 
	private String ClassType;
	private int LineNumber;
	private int LineNumberInRow;
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPlanetailnumber() {
		return Planetailnumber;
	}
	public void setPlanetailnumber(String planetailnumber) {
		Planetailnumber = planetailnumber;
	}
	public String getClassType() {
		return ClassType;
	}
	public void setClassType(String classType) {
		ClassType = classType;
	}
	public int getLineNumber() {
		return LineNumber;
	}
	public void setLineNumber(int lineNumber) {
		LineNumber = lineNumber;
	}
	public int getLineNumberInRow() {
		return LineNumberInRow;
	}
	public void setLineNumberInRow(int lineNumberInRow) {
		LineNumberInRow = lineNumberInRow;
	}
	public Seat(String id, String planetailnumber, String classType, int lineNumber, int lineNumberInRow) {
		super();
		Id = id;
		Planetailnumber = planetailnumber;
		ClassType = classType;
		LineNumber = lineNumber;
		LineNumberInRow = lineNumberInRow;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(Id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return Objects.equals(Id, other.Id);
	}
	public Seat(String id) {
		super();
		Id = id;
	}
	@Override
	public String toString() {
		return "Seat [Id=" + Id + ", ClassType=" + ClassType + ", LineNumber=" + LineNumber + ", LineNumberInRow="
				+ LineNumberInRow + "]";
	}

}
