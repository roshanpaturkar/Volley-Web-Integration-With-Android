package research.roshanpaturkar.com.vollyintegration.models;

public class PhoneDetails {

    private String phonename;
    private String model;
    private String date;
    private String phonetype;
    private String image;

    public PhoneDetails(){ }

    public PhoneDetails(String phonename, String model, String date, String phonetype, String image) {
        this.phonename = phonename;
        this.model = model;
        this.date = date;
        this.phonetype = phonetype;
        this.image = image;
    }

    public String getPhonename() {
        return phonename;
    }

    public String getModel() {
        return model;
    }

    public String getDate() {
        return date;
    }

    public String getPhonetype() {
        return phonetype;
    }

    public String getImage() {
        return image;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDate(String datel) {
        this.date = datel;
    }

    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
