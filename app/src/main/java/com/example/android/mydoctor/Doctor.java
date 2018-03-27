package com.example.android.mydoctor;

/**
 * Created by DELL on 10/4/2017.
 */

public class Doctor {

    private String docName;
    private String docAge;
    private String qualification;
    private String docRating;

    public Doctor(String docName, String docAge, String qualification, String docRating){
        this.docName = docName;
        this.docAge = docAge;
        this.qualification = qualification;
        this.docRating = docRating;
    }

    public String getDocAge() {
        return docAge;
    }

    public void setDocAge(String docAge) {
        this.docAge = docAge;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getDocRating() {
        return docRating;
    }

    public void setDocRating(String docRating) {
        this.docRating = docRating;
    }
}
