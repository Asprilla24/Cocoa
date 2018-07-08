package mlg.warkop.com.mypsychologist.model;

import android.net.Uri;

import mlg.warkop.com.mypsychologist.database.UserObject;

public class User {
    private String id;
    private String email;
    private String userName;
    private String education;
    private String majors;
    private int semester;
    private int level;
    private int age;
    private String address;
    private String phone;
    //private Uri photos;
    private int type;

    public User(){}

    public User(String _id, String _email, String _userName, String _education, String _majors, int _semester, int _level, int _age, String _address, String _phone){//, Uri _photos){
        this.id = _id;
        this.email = _email;
        this.userName = _userName;
        this.education = _education;
        this.majors = _majors;
        this.semester = _semester;
        this.level = _level;
        this.age = _age;
        this.address = _address;
        this.phone = _phone;
        //this.photos = _photos;
        this.type = 1;
    }

    public User(UserObject userObject){
        this.id = userObject.getId();
        this.email = userObject.getEmail();
        this.userName = userObject.getUserName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getMajors() {
        return majors;
    }

    public void setMajors(String majors) {
        this.majors = majors;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType(){
        return this.type;
    }

    public void setType(int type){
        this.type = type;
    }
//    public Uri getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(Uri photos) {
//        this.photos = photos;
//    }
}
