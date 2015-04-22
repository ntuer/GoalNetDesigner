package ntu.goalnetdesigner.viewcontroller;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ntu.goalnetdesigner.data.persistence.FeedbackLog;
import ntu.goalnetdesigner.data.persistence.Question;
import ntu.goalnetdesigner.data.service.DataService;
import ntu.goalnetdesigner.session.LoginSession;
import ntu.goalnetdesigner.utility.Resource;
import ntu.goalnetdesigner.utility.UIUtility;

public class FeedbackController {
	
    @FXML
    private GridPane grid;
    
    private List<Question> questions;
    
    private Button submitButton;
    
    private Label email;
    
    @FXML
    public void initialize() {
    	grid.setHgap(10);
    	grid.setVgap(10);
    	grid.setPadding(new Insets(10, 10, 10, 10));
    	questions = DataService.question.findAll();
    	
    	int i = 0;
    	for (; i < questions.size(); ++i){
    		Slider slider = new Slider(1, 5, 3);
			slider.setShowTickMarks(true);
			slider.setShowTickLabels(true);
			slider.setMajorTickUnit(1);
			slider.setBlockIncrement(1);
    		
        	grid.add(new Label(questions.get(i).getBody()), 0, i);
        	grid.add(slider, 1, i);
    	}

    	submitButton = new Button("Submit");
    	email = new Label("If you have any suggestions, please write to author Siyao at lisi0010@e.ntu.edu.sg.");
    	VBox vbox = new VBox();
    	vbox.getChildren().add(email);
    	vbox.getChildren().add(submitButton);
    	vbox.setSpacing(10);
    	grid.add(vbox, 0, i);
    	GridPane.setColumnSpan(vbox, 2);
    	GridPane.setHgrow(vbox, Priority.ALWAYS);
    	vbox.setAlignment(Pos.CENTER);
    	submitButton.setOnAction(onSubmitHandler);
    }
    
    private EventHandler<ActionEvent> onSubmitHandler = new EventHandler<ActionEvent>(){
		@Override
		public void handle(ActionEvent arg0) {
			for (int i = 0; i < questions.size(); ++i){
				FeedbackLog fb = new FeedbackLog();
				fb.setAnswer(String.valueOf(Math.round(((Slider)grid.getChildren().get(i*2 + 1)).getValue())));
				fb.setUser(LoginSession.user);
				fb.setQuestion(questions.get(i));
				fb.setVersion(Resource.System.SYSTEM_VERSION);
				if (DataService.getEntityManager().getTransaction().isActive())
					DataService.feedbackLog.persist(fb);
				else
					DataService.feedbackLog.atomicInsert(fb);
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Feedback");
			alert.setHeaderText("Feedback Submitted");
			alert.setContentText("Thank you for your feedback!");
			alert.showAndWait();
			UIUtility.Navigation.closeContainingStage(FeedbackController.this.submitButton);
		}
    };
}
