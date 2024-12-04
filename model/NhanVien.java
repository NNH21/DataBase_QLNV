package thicki.DataBase_QLNV.model;

public class NhanVien {
    private String maNV;
    private String tenNV;
    private String gioiTinh;
    private double Luong;

    public NhanVien(String maNV, String tenNV, String gioiTinh, double luong) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.gioiTinh = gioiTinh;
        Luong = luong;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public double getLuong() {
        return Luong;
    }

    public void setLuong(double luong) {
        Luong = luong;
    }

    @Override
    public String toString() {
        return "NhanVien: " +
                maNV + " |\t " +
                tenNV + " |\t " +
                gioiTinh + " |\t " +
                Luong;
    }
}
