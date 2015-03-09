package ntu.goalnetdesigner.fxcontrol;

import java.util.List;
import java.util.UUID;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import ntu.goalnetdesigner.data.persistence.FeedbackLog;
import ntu.goalnetdesigner.data.persistence.Question;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.session.UISession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class FeedbackController {
	
    @FXML
    private GridPane grid;
    
    private List<Question> questions;
    
    private Button submitButton;
    
    @FXML
    public void initialize() {
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(10, 10, 10, 10));
    	questions = DataService.question.findAll();
    	
    	int i = 0;
    	for (; i < questions.size(); ++i){
    		final TextField answer = new TextField(); 
        	grid.add(new Label(questions.get(i).getBody()), 0, i);
        	grid.add(answer, 1, i);
    	}

    	submitButton = new Button("Submit");
    	HBox hbox = new HBox();
    	hbox.getChildren().add(submitButton);
    	grid.add(hbox, 0, i);
    	GridPane.setColumnSpan(hbox, 2);
    	GridPane.setHgrow(hbox, Priority.ALWAYS);
    	hbox.setAlignment(Pos.CENTER);
    	submitButton.setOnAction(onSubmitHandler);
    }
    
    private EventHandler<ActionEvent> onSubmitHandler = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent arg0) {
			for (int i = 0; i < questions.size(); ++i){
				FeedbackLog fb = new FeedbackLog();
				fb.setAnswer(((TextField)grid.getChildren().get(i*2 + 1)).getText());
				fb.setUser(LoginSession.user);
				fb.setId(UUID.randomUUID().toString());
				fb.setQuestion(questions.get(i));
				fb.setVersion(Resource.System.SYSTEM_VERSION);
				DataService.feedbackLog.atomicInsert(fb);
			}
			Dialogs.showInformationDialog(UISession.primaryStage, "Thank you for your feedback!", 
				    "Feedback Submitted", "Feedback");
			UIUtility.Navigation.closeContainingStage(FeedbackController.this.submitButton);
		}
    };
}
