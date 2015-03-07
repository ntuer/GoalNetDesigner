package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the user_gnet database table.
 * 
 */
@Entity
@Table(name="user_gnet")
@NamedQuery(name="UserGnet.findAll", query="SELECT u FROM UserGnet u")
public class UserGnet implements Serializable, IAssociationDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserGnetPK id;
	
	private String accessLevel;

	//bi-directional many-to-one association to Gnet
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GNetID")
	private Gnet gnet;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserID")
	private User user;

	public UserGnet() {
	}

	public UserGnetPK getId() {
		return id;
	}

	public void setId(UserGnetPK id) {
		this.id = id;
	}

	public String getAccessLevel() {
		return this.accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public Gnet getGnet() {
		return this.gnet;
	}

	public void setGnet(Gnet gnet) {
		this.gnet = gnet;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}