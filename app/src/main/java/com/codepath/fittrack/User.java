package com.codepath.fittrack;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_USERNAME= "username";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_USERDESCRIPTION= "userDescription";
    public static final String KEY_IMAGE= "image";

    public ParseUser getUsername(){
        return getParseUser(KEY_USERNAME);
    }
    public void setUsername(ParseUser user){
        put(KEY_USERNAME,user);
    }
    public String getEmail(){
        return getString(KEY_EMAIL);
    }
    public void setEmail(String email){
        put( KEY_EMAIL, email);
    }

    public String getUserDescription(){
        return getString( KEY_USERDESCRIPTION);
    }
    public void setDescription(String description){
        put( KEY_USERDESCRIPTION, description);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }
    public void setImage(ParseFile parseFile){
        put(KEY_IMAGE,parseFile);
    }

}
