package ntu.goalnetdesigner.validation;

public interface IComponentValidator {
	public default void validate(){
		throw new RuntimeException();
	}
}
