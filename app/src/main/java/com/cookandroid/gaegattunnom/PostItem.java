package com.cookandroid.gaegattunnom;

public class PostItem
{
    private String birthday;

    private String address;

    private String accountType;

    private String name;

    private String phoneNum;

    private String job;

    private String accountPassword;

    private String userId;

    private String email;

    public String getBirthday ()
    {
        return birthday;
    }

    public void setBirthday (String birthday)
    {
        this.birthday = birthday;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getAccountType ()
    {
        return accountType;
    }

    public void setAccountType (String accountType)
    {
        this.accountType = accountType;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getPhoneNum ()
    {
        return phoneNum;
    }

    public void setPhoneNum (String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public String getJob ()
    {
        return job;
    }

    public void setJob (String job)
    {
        this.job = job;
    }

    public String getAccountPassword ()
    {
        return accountPassword;
    }

    public void setAccountPassword (String accountPassword)
    {
        this.accountPassword = accountPassword;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [birthday = "+birthday+", address = "+address+", accountType = "+accountType+", name = "+name+", phoneNum = "+phoneNum+", job = "+job+", accountPassword = "+accountPassword+", userId = "+userId+", email = "+email+"]";
    }
}