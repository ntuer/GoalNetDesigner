package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;
import java.util.UUID;


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
public class User implements Serializable, IDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String answer;

	private String email;

	private String password;

	private String question;
	
	private Integer age;
	
	private String educationLevel;

	//bi-directional many-to-one association to ActionLog
	@OneToMany(mappedBy="user")
	private List<ActionLog> actionLogs;

	//bi-directional many-to-one association to FeedbackLog
	@OneToMany(mappedBy="user")
	private List<FeedbackLog> feedbackLogs;

	//bi-directional many-to-one association to UserUsergroup
	@OneToMany(mappedBy="user")
	private List<UserGnet> userGnets;

	public String toString(){
		return this.id;
	}
	
	public User() {
		this.id = UUID.randomUUID().toString();
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

	public List<ActionLog> getActionLogs() {
		return this.actionLogs;
	}

	public void setActionLogs(List<ActionLog> actionLogs) {
		this.actionLogs = actionLogs;
	}

	public ActionLog addActionLog(ActionLog actionLog) {
		getActionLogs().add(actionLog);
		actionLog.setUser(this);

		return actionLog;
	}

	public ActionLog removeActionLog(ActionLog actionLog) {
		getActionLogs().remove(actionLog);
		actionLog.setUser(null);

		return actionLog;
	}

	public List<FeedbackLog> getFeedbackLogs() {
		return this.feedbackLogs;
	}

	public void setFeedbackLogs(List<FeedbackLog> feedbackLogs) {
		this.feedbackLogs = feedbackLogs;
	}

	public FeedbackLog addFeedbackLog(FeedbackLog feedbackLog) {
		getFeedbackLogs().add(feedbackLog);
		feedbackLog.setUser(this);

		return feedbackLog;
	}

	public FeedbackLog removeFeedbackLog(FeedbackLog feedbackLog) {
		getFeedbackLogs().remove(feedbackLog);
		feedbackLog.setUser(null);

		return feedbackLog;
	}

	public List<UserGnet> getUserGnets() {
		return this.userGnets;
	}

	public void setUserGnets(List<UserGnet> userGnets) {
		this.userGnets = userGnets;
	}

	public UserGnet addUserGnet(UserGnet userGnets) {
		getUserGnets().add(userGnets);
		userGnets.setUser(this);
		return userGnets;
	}

	public UserGnet removeUserGnet(UserGnet userGnets) {
		getUserGnets().remove(userGnets);
		userGnets.setUser(null);

		return userGnets;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

}