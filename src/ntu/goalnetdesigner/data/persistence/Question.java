package ntu.goalnetdesigner.data.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Question.findAll",
                query="SELECT c FROM Question c"),
    @NamedQuery(name="Question.findById",
                query="SELECT c FROM Question c WHERE c.id = :id"),
}) 
public class Question implements Serializable, IDataServiceUnitSubscriber {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String body;

	//bi-directional many-to-one association to FeedbackLog
	@OneToMany(mappedBy="question")
	private List<FeedbackLog> feedbackLogs;

	public Question() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public List<FeedbackLog> getFeedbackLogs() {
		return this.feedbackLogs;
	}

	public void setFeedbackLogs(List<FeedbackLog> feedbackLogs) {
		this.feedbackLogs = feedbackLogs;
	}

	public FeedbackLog addFeedbackLog(FeedbackLog feedbackLog) {
		getFeedbackLogs().add(feedbackLog);
		feedbackLog.setQuestion(this);

		return feedbackLog;
	}

	public FeedbackLog removeFeedbackLog(FeedbackLog feedbackLog) {
		getFeedbackLogs().remove(feedbackLog);
		feedbackLog.setQuestion(null);

		return feedbackLog;
	}

}