import java.time.LocalDate;
import java.util.Scanner;

public class PhieuNhapHang {
    private String maPhieuNhap;
    private LocalDate thoiGianNhap;
    private String nhaCungCap;
    private QuanLyVatTu danhSachNhap;

    // ====== Constructor ======
    public PhieuNhapHang() {
        danhSachNhap = new QuanLyVatTu(); 
    }
    public PhieuNhapHang(String maPhieu, LocalDate tg , String ncc , QuanLyVatTu ds){
        this.maPhieuNhap = maPhieu;
        this.thoiGianNhap = tg;
        this.nhaCungCap = ncc;
        this.danhSachNhap = ds;
    }
    // ====== Getters ======
    public String getMaPhieuNhap() { return maPhieuNhap; }
    public LocalDate getthoiGianNhap() { return thoiGianNhap; }
    public String getNhaCungCap() { return nhaCungCap; }
    public QuanLyVatTu getDanhSachNhap() { return danhSachNhap; }
    // ====== Nhập dữ liệu phiếu nhập ======
    public void nhap(Scanner sc) {
        System.out.print("Nhap ma phieu nhap: ");
        maPhieuNhap = sc.nextLine();

        System.out.print("Nhap ngay nhap (yyyy-mm-dd): ");
        thoiGianNhap = LocalDate.parse(sc.nextLine());

        System.out.print("Nhap ten nha cung cap: ");
        nhaCungCap = sc.nextLine();

        System.out.print("Nhap so luong vat tu trong phieu: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println("\nNhap vat tu thu " + (i + 1) + ":");
            danhSachNhap.add();
        }
    }

    // ====== Xuất thông tin phiếu nhập ======
    public void xuat() {
        System.out.println("\n=== PHIEU NHAP HANG ===");
        System.out.println("Ma phieu: " + maPhieuNhap);
        System.out.println("Ngay nhap: " + thoiGianNhap);
        System.out.println("Nha cung cap: " + nhaCungCap);
        System.out.println("Danh sach vat tu nhap:");
        danhSachNhap.printList();
        System.out.printf("Tong tien phieu: %2d VND\n", tinhTongTien());
    }

    // ====== Tính tổng tiền ======
    public long tinhTongTien() {
        long tong = 0;
        VatTu[] ds = danhSachNhap.get_danhSach();
        for (int i = 0; i < danhSachNhap.get_n(); i++) {
            tong += ds[i].get_donGia() * ds[i].get_soLuong();
        }
        return tong;
    }

    // ====== Cập nhật vào kho chính ======
    public void capNhatVaoKho(QuanLyVatTu khoChinh) {
        VatTu[] dsNhap = danhSachNhap.get_danhSach();
        for (int i = 0; i < danhSachNhap.get_n(); i++) {
            VatTu vt = dsNhap[i];
            khoChinh.import_export_Goods(vt.get_maSo(), vt.get_soLuong());
        }
    }

}
