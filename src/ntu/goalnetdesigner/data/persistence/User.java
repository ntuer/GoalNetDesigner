package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQueries({
    @NamedQuery(name="User.findAll",
                query="SELECT c FROM User c"),
    @NamedQuery(name="User.findById",
                query="SELECT c FROM User c WHERE c.id = :id"),
}) 
public class User implements Serializable, IDataServiceUnitSubscriber  {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String answer;

	private String email;

	private String password;

	private String question;

	//bi-directional many-to-one association to UserUsergroup
	@OneToMany(mappedBy="user")
	private List<UserUsergroup> userUsergroups;

	public User() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<UserUsergroup> getUserUsergroups() {
		return this.userUsergroups;
	}

	public void setUserUsergroups(List<UserUsergroup> userUsergroups) {
		this.userUsergroups = userUsergroups;
	}

	public UserUsergroup addUserUsergroup(UserUsergroup userUsergroup) {
		getUserUsergroups().add(userUsergroup);
		userUsergroup.setUser(this);

		return userUsergroup;
	}

	public UserUsergroup removeUserUsergroup(UserUsergroup userUsergroup) {
		getUserUsergroups().remove(userUsergroup);
		userUsergroup.setUser(null);

		return userUsergroup;
	}

}