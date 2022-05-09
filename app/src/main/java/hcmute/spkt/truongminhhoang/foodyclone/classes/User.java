package hcmute.spkt.truongminhhoang.foodyclone.classes;

public class User {
    private int id;
    private String userName;
    private String fullName;
    private String password;
    private int phone;
    private String address;


    public User(int id, String userName, String fullName, String password, int phone, String address) {
        this.id = id;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
