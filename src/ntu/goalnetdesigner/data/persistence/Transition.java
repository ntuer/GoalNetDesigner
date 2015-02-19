package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


/**
 * The persistent class for the transition database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Transition.findAll",
                query="SELECT c FROM Transition c"),
    @NamedQuery(name="Transition.findById",
                query="SELECT c FROM Transition c WHERE c.id = :id"),
}) 
public class Transition extends ntu.goalnetdesigner.render.Drawable implements Serializable, IDataServiceUnitSubscriber {
	
	public String toString(){
		return this.getName();
	}
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int achievement;

	private int cost;

	private String description;

	private boolean enabled;

	private int exceptionStateID;

	private int level;

	private String name;

	private BigInteger taskCount;

	private String type;

	private int x;

	private int y;

	//bi-directional many-to-one association to Gnet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GNetID")
	private Gnet gnet;

	//bi-directional many-to-one association to Tasklist
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TaskListID")
	private Tasklist tasklist;

	public Transition() {
		this.name = "Transition";
		this.id = UUID.randomUUID().toString();
		this.type = "simple";
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAchievement() {
		return this.achievement;
	}

	public void setAchievement(int achievement) {
		this.achievement = achievement;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getEnabled() {
		return this.enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getExceptionStateID() {
		return this.exceptionStateID;
	}

	public void setExceptionStateID(int exceptionStateID) {
		this.exceptionStateID = exceptionStateID;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getTaskCount() {
		return this.taskCount;
	}

	public void setTaskCount(BigInteger taskCount) {
		this.taskCount = taskCount;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Gnet getGnet() {
		return this.gnet;
	}

	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}

	public Tasklist getTasklist() {
		return this.tasklist;
	}

	public void setTasklist(Tasklist tasklist) {
		this.tasklist = tasklist;
	}

}