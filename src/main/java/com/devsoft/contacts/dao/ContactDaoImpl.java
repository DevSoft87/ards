package com.devsoft.contacts.dao;

import com.devsoft.contacts.logic.ContactLogic;
import com.devsoft.contacts.model.Contact;
import com.google.gson.stream.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Sergey on 30.04.2016.
 */
@Repository
public class ContactDaoImpl implements ContactDao {

    @Autowired
    private DataSource dataSource;

    public ResultSet getContacts(Writer writer, String regex){
        PreparedStatement ps      = null;
        ResultSet         rs      = null;
        boolean isAgree = true;

        String tmpName = null;
        try(Connection conn =  dataSource.getConnection();
            JsonWriter json = new JsonWriter(writer)){
            json.setLenient(true);
            json.beginObject();
            ps = conn.prepareStatement("SELECT id, name from contacts");
            ps.setFetchSize(100);
            if((rs = ps.executeQuery())!= null) {
                json.name("contacts");
                json.beginArray();
                while(rs.next()) {
                    tmpName = rs.getString(2);
                    if(!StringUtils.isEmpty(regex)) {
                       isAgree = !ContactLogic.isValidRegex(tmpName, regex);
                    }
                    if(isAgree){
                        json.beginObject().name("id").value(rs.getInt(1)).name("name").value(tmpName).endObject();
                        json.flush();
                        writer.flush();
                    }
                }
                json.endArray();
            }
            json.endObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rs;
    }
}
