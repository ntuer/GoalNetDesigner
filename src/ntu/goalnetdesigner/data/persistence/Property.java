package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the property database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Property.findAll",
                query="SELECT c FROM Property c"),
    @NamedQuery(name="Property.findById",
                query="SELECT c FROM Property c WHERE c.id = :id"),
}) 
public class Property implements Serializable, IDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String name;

	private BigInteger parentID;

	private boolean st;

	private String type;

	private String val;

	public Property() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getParentID() {
		return this.parentID;
	}

	public void setParentID(BigInteger parentID) {
		this.parentID = parentID;
	}

	public boolean getSt() {
		return this.st;
	}

	public void setSt(boolean st) {
		this.st = st;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVal() {
		return this.val;
	}

	public void setVal(String val) {
		this.val = val;
	}

}