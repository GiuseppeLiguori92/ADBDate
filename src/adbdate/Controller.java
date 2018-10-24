package adbdate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller implements Contract.View {
    private Contract.Presenter presenter;

    @FXML
    private Label lblDate;
    @FXML
    private Button btPlusHour;
    @FXML
    private Button btPlusDay;
    @FXML
    private Button btPlusWeek;
    @FXML
    private Button btReset;

    @FXML
    private Button btBootBroadcast;

    @FXML
    private void initialize() {
        presenter = new ControllerPresenter(this);
        presenter.initialise();

        btPlusHour.setOnAction(event -> presenter.addHour());
        btPlusDay.setOnAction(event -> presenter.addDay());
        btPlusWeek.setOnAction(event -> presenter.addWeek());
        btReset.setOnAction(event -> presenter.reset());

        btBootBroadcast.setOnAction(event -> presenter.sendBootBroadcast());
    }

    @Override
    public void showDate(String date) {
        lblDate.setText(date);
    }
}
