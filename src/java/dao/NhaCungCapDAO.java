/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.NhaCungCap;

/**
 *
 * @author Xuan Toog
 */
public class NhaCungCapDAO extends DAO{
    
    public NhaCungCapDAO() {
        super();
    }
    
    public int createNhaCungCap(NhaCungCap ncc) {
        int status = 0;
        try {
            PreparedStatement ps = con.prepareStatement("insert into nhacungcap (ma, ten, diachi, sodienthoai)"
                    + " values (?,?,?,?)");
            ps.setString(1, ncc.getMa());
            ps.setString(2, ncc.getTen());
            ps.setString(3, ncc.getDiachi());
            ps.setString(4, ncc.getSodienthoai());
            
            status = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return status;
    }
    
    public NhaCungCap getNhaCungCap(int nccId) {
        NhaCungCap ncc = null;
        try {
            PreparedStatement ps = con.prepareStatement("select * from nhacungcap where id=?");
            ps.setInt(1, nccId);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                ncc = new NhaCungCap();
                ncc.setId(rs.getInt(1));
                ncc.setMa(rs.getString(1));
                ncc.setTen(rs.getString(2));
                ncc.setDiachi(rs.getString(3));
                ncc.setSodienthoai(rs.getString(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return ncc;
    }
    
    public ArrayList<NhaCungCap> searchNhaCungCap(String tenNCC){
        ArrayList<NhaCungCap> arr = new ArrayList();
        try {
            String query = "select * from nhacungcap where ten like '%" + tenNCC + "%'";
            Statement statement = con.createStatement();
            
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setId(rs.getInt(1));
                ncc.setMa(rs.getString(2));
                ncc.setTen(rs.getString(3));
                ncc.setDiachi(rs.getString(4));
                ncc.setSodienthoai(rs.getString(5));
                
                arr.add(ncc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return arr;
    }
}
