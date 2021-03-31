package motoworld.project.model.binding;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class SupportRequestBindingModel {

    private String id;
    private String supportRequest;
    private String email;
    private String date;


    public SupportRequestBindingModel() {
    }

    public String getId() {
        return id;
    }

    public SupportRequestBindingModel setId(String id) {
        this.id = id;
        return this;
    }

    @Length(min = 5, message = "Message must be min 5 char")
    public String getSupportRequest() {
        return supportRequest;
    }

    public SupportRequestBindingModel setSupportRequest(String supportRequest) {
        this.supportRequest = supportRequest;
        return this;
    }

    @Email
    public String getEmail() {
        return email;
    }

    public SupportRequestBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }


    public String getDate() {
        return date;
    }

    public SupportRequestBindingModel setDate(String date) {
        this.date = date;
        return this;
    }
}
