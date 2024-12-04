package thicki.DataBase_QLNV.view;

import thicki.DataBase_QLNV.controller.NhanVienDAO;
import thicki.DataBase_QLNV.model.NhanVien;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        NhanVienDAO dao = new NhanVienDAO();
        List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("");
            System.out.println("CHUONG TRINH QUAN LY NHAN VIEN");
            System.out.println("1. Them thong tin nhan vien");
            System.out.println("2. Hien thi danh sach nhan vien");
            System.out.println("3. Sua thong tin nhan vien theo MaNV");
            System.out.println("4. Xoa thong tin nhan vien theo MaNV");
            System.out.println("5. Tim kiem nhung nhan vien co muc luong lon hon mot gia tri X");
            System.out.println("6. Thong ke muc luong nhan vien");
            System.out.println("0. Thoat");
            System.out.print("Vui long chon so: ");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:{
                        String maNV;
                        do {
                            System.out.println("Nhap ma nhan vien: ");
                            maNV = sc.nextLine();
                            if (dao.isMaNVTonTai(maNV)) {
                                System.out.println("Ma nhan vien da ton tai, vui long nhap lai.");
                            }
                        } while (dao.isMaNVTonTai(maNV));

                        System.out.println("Nhap ten nhan vien: ");
                        String tenNV = sc.nextLine();
                        System.out.println("Nhap gioi tinh: ");
                        String gioiTinh = sc.nextLine();
                        System.out.println("Nhap luong: ");
                        double luong = Double.parseDouble(sc.nextLine());
                        NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, luong);
                        if (dao.addNhanVien(nv)) {
                            System.out.println("Them thanh cong!!!");
                        } else {
                            System.out.println("Them that bai!!!");
                        }
                        break;
                }
                case 2:{
                    listNhanVien = dao.getAllNhanVien();
                    for (NhanVien nv : listNhanVien) {
                        System.out.println(nv);
                    }
                    break;
                }
                case 3:{
                    System.out.println("Nhap ma nhan vien can sua: ");
                    String maNV = sc.nextLine();
                    System.out.println("Nhap ten nhan vien: ");
                    String tenNV = sc.nextLine();
                    System.out.println("Nhap gioi tinh: ");
                    String gioiTinh = sc.nextLine();
                    System.out.println("Nhap luong: ");
                    double luong = Double.parseDouble(sc.nextLine());
                    NhanVien nv = new NhanVien(maNV, tenNV, gioiTinh, luong);
                    if (dao.editNhanVien(nv)) {
                        System.out.println("Sua thanh cong!!!");
                    }else {
                        System.out.println("Sua that bai!!!");
                    }
                    break;
                }
                case 4:{
                    System.out.println("Nhap ma nhan vien can xoa: ");
                    String maNV = sc.nextLine();
                    if (dao.deleteNhanVien(maNV)) {
                        System.out.println("Xoa thanh cong!!!");
                    }else {
                        System.out.println("Xoa that bai!!!");
                    }
                    break;
                }
                case 5:{
                    System.out.println("Nhap muc luong X: ");
                    double luong = Double.parseDouble(sc.nextLine());
                    listNhanVien = dao.getNhanVienByLuong(luong);
                    for (NhanVien nv : listNhanVien) {
                        System.out.println(nv);
                    }
                    break;
                }
                case 6:{
                    System.out.println("Thong ke muc luong nhan vien: ");
                    System.out.println("Tong so nhan vien: " + dao.tongSoNV());
                    System.out.println("Tong muc luong: " + dao.tongLuongTraNV());
                    System.out.println("Nhan vien co muc luong cao nhat: ");
                    System.out.println(dao.NhanVienMucLuongCaoNhat());
                    break;
                }

            }
        }while (choice != 0);
    }
}
