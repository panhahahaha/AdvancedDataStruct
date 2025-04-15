module dsaAssign1 {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires junit;
	requires java.sql;
	opens GUI to javafx.graphics, javafx.fxml;
	opens Testing to junit, hamcrest;
}