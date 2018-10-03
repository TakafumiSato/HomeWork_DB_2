/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author 佐藤孝史
 */
public interface TableController {
    
    public ArrayList<?> getTable() throws SQLException;
    public void setTable(ArrayList<?> list) throws SQLException;
}
