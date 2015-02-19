package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the arc database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Arc.findAll",
                query="SELECT c FROM Arc c"),
    @NamedQuery(name="Arc.findById",
                query="SELECT c FROM Arc c WHERE c.id = :id"),
}) 
public class Arc extends ntu.goalnetdesigner.render.Drawable implements Serializable, IDataServiceUnitSubscriber{
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String description;

	private boolean direction;

	private String inputID;

	private boolean isDirect;

	private String name;

	private String outputID;

	//bi-directional many-to-one association to Gnet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GNetID")
	private Gnet gnet;

	public Arc() {
		this.id = UUID.randomUUID().toString();
		this.name = "Arc";
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
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

	public String getInputID() {
		return this.inputID;
	}

	public void setInputID(String inputID) {
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

	public String getOutputID() {
		return this.outputID;
	}

	public void setOutputID(String outputID) {
		this.outputID = outputID;
	}

	public Gnet getGnet() {
		return this.gnet;
	}

	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}

	public String toString(){
		return this.getName();
	}
	
}