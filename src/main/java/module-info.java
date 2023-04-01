module hu.fzks.fazekas_tamas_visszaszamlalas {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens hu.fzks.fazekas_tamas_visszaszamlalas to javafx.fxml;
    exports hu.fzks.fazekas_tamas_visszaszamlalas;
}