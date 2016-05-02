package com.devsoft.contacts.dao;

import org.springframework.stereotype.Repository;

import java.io.Writer;
import java.sql.ResultSet;

/**
 * Created by Sergey on 30.04.2016.
 */

public interface ContactDao {
    ResultSet getContacts(Writer writer, String regex);
}
