package thicki.DataBase_QLNV.controller;

import thicki.DataBase_QLNV.db.dbConnect;
import thicki.DataBase_QLNV.model.NhanVien;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {

    public List<NhanVien> getAllNhanVien(){
        List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            conn = dbConnect.connectToDatabase();
            if(conn != null){
                statement = conn.createStatement();
                String sql = "SELECT * FROM NHANVIEN";
                resultSet = statement.executeQuery(sql);
                while(resultSet.next()){
                    String maNV = resultSet.getString(1);
                    String tenNV = resultSet.getString(2);
                    String gioiTinh = resultSet.getString(3);
                    double luong = resultSet.getDouble(4);
                    listNhanVien.add(new NhanVien(maNV, tenNV, gioiTinh, luong));
                }
            }
        }catch(SQLException e){
            System.out.println("Loi: " + e.getMessage());

        }
        return listNhanVien;
    }
    public boolean addNhanVien(NhanVien nv) {
        Connection conn = null;
        PreparedStatement prtm = null;
        int row = 0;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                String sql = "INSERT INTO NHANVIEN VALUES(?, ?, ?, ?)";
                prtm = conn.prepareStatement(sql);
                prtm.setString(1, nv.getMaNV());
                prtm.setString(2, nv.getTenNV());
                prtm.setString(3, nv.getGioiTinh());
                prtm.setDouble(4, nv.getLuong());
                row = prtm.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return row > 0;
    }

    public boolean editNhanVien(NhanVien nv) {
        Connection conn = null;
        PreparedStatement prtm = null;
        int row = 0;
        try{
            Connection connection = dbConnect.connectToDatabase();
            if(connection != null){
                String sql = "UPDATE NHANVIEN SET TenNV = ?, Gioitinh = ?, Luong = ? WHERE MaNV = ?";
                prtm = connection.prepareStatement(sql);
                prtm.setString(1, nv.getTenNV());
                prtm.setString(2, nv.getGioiTinh());
                prtm.setDouble(3, nv.getLuong());
                prtm.setString(4, nv.getMaNV());
                row = prtm.executeUpdate();
            }
        }catch (SQLException e){
            System.out.println("Loi: " + e.getMessage());
        }
        return row > 0;
    }

    public boolean deleteNhanVien(String maNV) {
        Connection conn = null;
        PreparedStatement prtm = null;
        int row = 0;
        try {
            Connection connection = dbConnect.connectToDatabase();
            if (connection != null) {
                String sql = "DELETE FROM NHANVIEN WHERE MaNV = ?";
                prtm = connection.prepareStatement(sql);
                prtm.setString(1, maNV);
                row = prtm.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return row > 0;
    }

    public List<NhanVien> getNhanVienByLuong(double luong) {
        List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
        Connection conn = null;
        PreparedStatement prtm = null;
        ResultSet resultSet = null;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                String sql = "SELECT * FROM NHANVIEN WHERE Luong > ?";
                prtm = conn.prepareStatement(sql);
                prtm.setDouble(1, luong);
                resultSet = prtm.executeQuery();
                while (resultSet.next()) {
                    String maNV = resultSet.getString(1);
                    String tenNV = resultSet.getString(2);
                    String gioiTinh = resultSet.getString(3);
                    double luongNV = resultSet.getDouble(4);
                    listNhanVien.add(new NhanVien(maNV, tenNV, gioiTinh, luongNV));
                }
            }
        }catch (SQLException e){
            System.out.println("Loi: " + e.getMessage());
        }
        return listNhanVien;
    }
    public boolean isMaNVTonTai(String maNV) {
        Connection conn = null;
        PreparedStatement prtm = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                String sql = "SELECT 1 FROM NHANVIEN WHERE MaNV = ?";
                prtm = conn.prepareStatement(sql);
                prtm.setString(1, maNV);
                resultSet = prtm.executeQuery();
                exists = resultSet.next();
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return exists;
    }
    public int tongSoNV() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        int totalEmployees = 0;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                statement = conn.createStatement();
                String sql = "SELECT COUNT(*) FROM NHANVIEN";
                resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    totalEmployees = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return totalEmployees;
    }

    public double tongLuongTraNV() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        double totalSalary = 0.0;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                statement = conn.createStatement();
                String sql = "SELECT SUM(Luong) FROM NHANVIEN";
                resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    totalSalary = resultSet.getDouble(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return totalSalary;
    }

    public NhanVien NhanVienMucLuongCaoNhat() {
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        NhanVien mucLuongMax = null;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                statement = conn.createStatement();
                String sql = "SELECT TOP 1 * FROM NHANVIEN ORDER BY Luong DESC";
                resultSet = statement.executeQuery(sql);
                if (resultSet.next()) {
                    String maNV = resultSet.getString(1);
                    String tenNV = resultSet.getString(2);
                    String gioiTinh = resultSet.getString(3);
                    double luong = resultSet.getDouble(4);
                    mucLuongMax = new NhanVien(maNV, tenNV, gioiTinh, luong);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return mucLuongMax;
    }
}
