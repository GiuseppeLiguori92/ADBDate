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
    private Button btRoot;
    @FXML
    private Label lblRoot;
    @FXML
    private Button btBootBroadcast;

    @FXML
    private Button btDozeMode;
    @FXML
    private Button btNormalMode;
    @FXML
    private Label lblStatus;

    @FXML
    private void initialize() {
        presenter = new ControllerPresenter(this);
        presenter.initialise();

        btPlusHour.setOnAction(event -> presenter.addHour());
        btPlusDay.setOnAction(event -> presenter.addDay());
        btPlusWeek.setOnAction(event -> presenter.addWeek());
        btReset.setOnAction(event -> presenter.reset());

        btBootBroadcast.setOnAction(event -> presenter.sendBootBroadcast());

        btRoot.setOnAction(event -> presenter.root());

        btDozeMode.setOnAction(event -> presenter.dozeMode());
        btNormalMode.setOnAction(event -> presenter.normalMode());
    }

    @Override
    public void showDate(String date) {
        lblDate.setText(date);
    }

    @Override
    public void showStatus(String status) {
        lblStatus.setText(status);
    }

    @Override
    public void showIsRooted() {
        lblRoot.setText("Rooted");
    }

    @Override
    public void showIsNotRooted() {
        lblRoot.setText("*NOT Rooted");
    }
}
