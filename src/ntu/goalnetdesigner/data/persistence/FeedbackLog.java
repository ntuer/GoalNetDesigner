package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the feedback_log database table.
 * 
 */
@Entity
@Table(name="feedback_log")
@NamedQueries({
    @NamedQuery(name="FeedbackLog.findAll",
                query="SELECT c FROM FeedbackLog c"),
    @NamedQuery(name="FeedbackLog.findById",
                query="SELECT c FROM FeedbackLog c WHERE c.id = :id"),
}) 
public class FeedbackLog implements Serializable, IDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private Timestamp timestamp;

	private String version;

	//bi-directional many-to-one association to Question
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="QuestionID")
	private Question question;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="UserID")
	private User user;

	public FeedbackLog() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}