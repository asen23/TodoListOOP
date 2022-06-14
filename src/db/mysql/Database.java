package db.mysql;

import db.Connect;

public abstract class Database {
    protected final Connect db = Connect.getInstance();
}
