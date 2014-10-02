package ntu.goalnetdesigner.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the arc database table.
 * 
 */
@Entity
@NamedQuery(name="Arc.findAll", query="SELECT a FROM Arc a")
public class Arc implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private BigInteger id;

	private String description;

	private boolean direction;

	private BigInteger GNetID;

	private BigInteger inputID;

	private boolean isDirect;

	private String name;

	private BigInteger outputID;

	public Arc() {
	}

	public BigInteger getId() {
		return this.id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getDirection() {
		return this.direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}

	public BigInteger getGNetID() {
		return this.GNetID;
	}

	public void setGNetID(BigInteger GNetID) {
		this.GNetID = GNetID;
	}

	public BigInteger getInputID() {
		return this.inputID;
	}

	public void setInputID(BigInteger inputID) {
		this.inputID = inputID;
	}

	public boolean getIsDirect() {
		return this.isDirect;
	}

	public void setIsDirect(boolean isDirect) {
		this.isDirect = isDirect;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getOutputID() {
		return this.outputID;
	}

	public void setOutputID(BigInteger outputID) {
		this.outputID = outputID;
	}

}