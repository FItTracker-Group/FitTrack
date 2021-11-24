package com.codepath.fittrack;

import com.parse.Parse;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("UserInfo")
public class UserInfo extends ParseObject {

    public static final String KEY_USERNAME= "username";
    public static final String KEY_EMAIL= "email";
    public static final String KEY_USERDESCRIPTION= "UserDescription";
    public static final String KEY_IMAGE= "ProfileImage";
    public static final String KEY_USER= "User";
    public static final String KEY_OBJECTID= "objectID";

    public String getUsername(){ return getString(KEY_USERNAME); }
    public void setUsername(String username){ put(KEY_USERNAME,username); }

    public String getEmail(){ return getString(KEY_EMAIL); }
    public void setEmail(String email){ put( KEY_EMAIL, email); }

    public String getUserDescription(){  return getString( KEY_USERDESCRIPTION); }
    public void setDescription(String description){ put( KEY_USERDESCRIPTION, description); }

    public ParseFile getImage(){ return getParseFile(KEY_IMAGE); }
    public void setImage(ParseFile parseFile){ put(KEY_IMAGE,parseFile); }

    public ParseUser getUser() { return getParseUser(KEY_USER);}
    public void setUser(ParseUser parseUser){ put(KEY_USER,parseUser);}

    public String getObjectID(){ return getString(KEY_OBJECTID);}
}