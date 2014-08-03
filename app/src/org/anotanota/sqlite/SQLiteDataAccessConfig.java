package org.anotanota.sqlite;

public class SQLiteDataAccessConfig {

  private final String mUserName;

  public SQLiteDataAccessConfig(String userName) {
    mUserName = userName;
  }

  public String getDatabaseName() {
    return "anotanota";
  }

  public int getDatabaseVersion() {
    return 1;
  }

  public String getUserName() {
    return mUserName;
  }

}
