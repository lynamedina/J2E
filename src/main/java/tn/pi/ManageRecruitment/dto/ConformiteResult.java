package tn.pi.ManageRecruitment.dto;

public class ConformiteResult {
    private boolean conforme;
    private String message;

    public ConformiteResult() {}

    public ConformiteResult(boolean conforme, String message) {
        this.conforme = conforme;
        this.message = message;
    }

    public boolean isConforme() {
        return conforme;
    }

    public void setConforme(boolean conforme) {
        this.conforme = conforme;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}