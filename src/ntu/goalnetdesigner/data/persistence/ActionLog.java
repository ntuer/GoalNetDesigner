package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the action_log database table.
 * 
 */
@Entity
@Table(name="action_log")
@NamedQueries({
    @NamedQuery(name="ActionLog.findAll",
                query="SELECT c FROM ActionLog c"),
    @NamedQuery(name="ActionLog.findById",
                query="SELECT c FROM ActionLog c WHERE c.id = :id"),
}) 
public class ActionLog implements Serializable, IDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String action;

	private String operationTargetObject;

	private String targetObjectID;

	private Timestamp timestamp;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserID")
	private User user;

	//bi-directional many-to-one association to Gnet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GNetID")
	private Gnet gnet;

	public ActionLog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getOperationTargetObject() {
		return this.operationTargetObject;
	}

	public void setOperationTargetObject(String operationTargetObject) {
		this.operationTargetObject = operationTargetObject;
	}

	public String getTargetObjectID() {
		return this.targetObjectID;
	}

	public void setTargetObjectID(String targetObjectID) {
		this.targetObjectID = targetObjectID;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Gnet getGnet() {
		return this.gnet;
	}

	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}

}