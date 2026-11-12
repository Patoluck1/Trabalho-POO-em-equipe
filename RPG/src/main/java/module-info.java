module pr_pucpr.rpg {
    requires javafx.controls;
    requires javafx.fxml;


    opens pr_pucpr.rpg to javafx.fxml;
    exports pr_pucpr.rpg;
}