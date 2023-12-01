package entity;

public class Consumer {
    private int userId;
    private String userName;
    private String userSurname;
    private String address;

    public Consumer(String userName, String userSurname, String address) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.address = address;
    }

    public Consumer(int userId, String userName, String userSurname, String address) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Consumer{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
