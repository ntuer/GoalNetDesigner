package ntu.goalnetdesigner.validation.syntax;

public class ObjectStringPair {
	private Object object;
	private String string;
	public ObjectStringPair(Object object2, String error) {
		// TODO Auto-generated constructor stub
		this.object = object2;
		this.string = error;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
}
