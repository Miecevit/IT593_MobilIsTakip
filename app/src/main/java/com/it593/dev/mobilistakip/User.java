package com.it593.dev.mobilistakip;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int _id;
    private String _firstname;
    private String _lastname;
    private String _password;
    private String _email;
    private String _job;
    private AUTHTYPE _authType = AUTHTYPE.Hicbiri;


    private String _workOrderCount = "0";

    public enum AUTHTYPE {
        Yonetici(2),
        Kullanici(1),
        Yetkili(3),
        Hicbiri(99);

        private int numVal;

        AUTHTYPE(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }

    public User() {
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getJob() {
        return _job;
    }

    public void setJob(String lastSeen) {
        this._job = lastSeen;
    }


    public String getFirstName() {

        if (_firstname == null)
            return "";

        return _firstname;
    }


    public void setFirstName(String name) {
        this._firstname = name;
    }


    public void setEmail(String email) {
        this._email = email;
    }


    public String getEmail() {

        if (_email == null)
            return "";

        return _email;
    }

    public String getLastName() {

        if (_lastname == null)
            return _lastname;

        return _lastname;
    }

    public void setLastName(String userName) {
        this._lastname = userName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        this._password = password;
    }

    public AUTHTYPE getAuthType() {
        return _authType;
    }

    public void setAuthType(int authType) {
        if (authType == AUTHTYPE.Yonetici.getNumVal())
            this._authType = AUTHTYPE.Yonetici;
        else if (authType == AUTHTYPE.Yetkili.getNumVal())
            this._authType = AUTHTYPE.Yetkili;
        else if (authType == AUTHTYPE.Kullanici.getNumVal())
            this._authType = AUTHTYPE.Kullanici;
        else this._authType = AUTHTYPE.Hicbiri;
    }

    public static AUTHTYPE getAuthType(int authType) {
        if (authType == AUTHTYPE.Yonetici.getNumVal())
            return AUTHTYPE.Yonetici;
        else if (authType == AUTHTYPE.Yetkili.getNumVal())
            return AUTHTYPE.Yetkili;
        else if (authType == AUTHTYPE.Kullanici.getNumVal())
            return AUTHTYPE.Kullanici;
        else return AUTHTYPE.Hicbiri;
    }

}