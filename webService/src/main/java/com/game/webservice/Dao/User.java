package com.game.webservice.Dao;




public class User {
    int id;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + usrname + '\'' +
                ", password='" + password + '\'' +
                ", province='" + province + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", sum='" + sum + '\'' +
                ", cer=" + cer +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String usrname;
    String password;
    String province;
    String job;
    String phone;
    String email;
    String sum;
    boolean cer;
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isCer() {
        return cer;
    }

    public void setCer(boolean cer) {
        this.cer = cer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public String getUsername() {
        return usrname;
    }

    public void setUsername(String username) {
        this.usrname = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
