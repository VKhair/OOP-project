
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class Phieu {

    protected String maPhieu;
    protected LocalDate thoiGian;
    protected long tongTien;
    protected ChiTietPhieu[] chiTiet;
    protected int soLoai;

    public Phieu(String maPhieu, LocalDate thoiGian) {
        this.maPhieu = maPhieu;
        this.thoiGian = thoiGian;
        this.tongTien = 0;
        this.soLoai = 0;
        chiTiet = new ChiTietPhieu[soLoai];
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public LocalDate getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDate thoiGian) {
        this.thoiGian = thoiGian;
    }

    public long getTongTien() {
        tinhTongTien();
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public ChiTietPhieu[] getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(ChiTietPhieu[] chiTiet) {
        this.chiTiet = chiTiet;
    }

    public int get_soLoai() {
        return soLoai;
    }

    public void set_soLoai(int soloai) {
        this.soLoai = soloai;
    }

    public void addChiTiet(ChiTietPhieu ctp) {
        chiTiet = Arrays.copyOf(chiTiet, soLoai + 1);
        chiTiet[soLoai++] = ctp;
    }

    public void add(int n) {
        if (n <= soLoai) {
            System.out.println("Vui long truyen vao so lon hon " + soLoai);
            return;
        }
        chiTiet = Arrays.copyOf(chiTiet, n);
        Scanner sc = new Scanner(System.in);
        for (int i = soLoai; i < n; i++) {
            System.out.println("Nhap thong tin vat tu thu " + (i + 1) + ":");
            System.out.print("Ma so: ");
            String ma = sc.nextLine();

            System.out.print("Ten: ");
            String ten = sc.nextLine();

            System.out.print("Loai: ");
            String loai = sc.nextLine();

            System.out.print("So luong: ");
            int sl = sc.nextInt();
            System.out.print("Don gia: ");
            long dg = sc.nextLong();
            sc.nextLine();
            chiTiet[i] = new ChiTietPhieu();
            if (loai.equalsIgnoreCase("ThucPham")) {
                System.out.print("Nhap HSD (yyyy-mm-dd): ");
                String s = sc.nextLine();
                LocalDate hsd = LocalDate.parse(s);
                chiTiet[i].vatTu = new ThucPham(ma, ten, sl, dg, hsd);
            } else {
                chiTiet[i].vatTu = new VatTu(ma, ten, loai, sl, dg);
            }
            chiTiet[i].tinhThanhTien();
        }
        soLoai = n;
        tinhTongTien();
    }

    public void remove(String maVatTu) {
        int index = -1;
        for (int i = 0; i < soLoai; i++) {
            if (chiTiet[i].vatTu.get_maSo().equalsIgnoreCase(maVatTu)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }

        for (int i = index; i < soLoai - 1; i++) {
            chiTiet[i] = chiTiet[i + 1];
        }
        chiTiet = Arrays.copyOf(chiTiet, soLoai - 1);
        soLoai--;
        tinhTongTien();
    }

    public void tinhTongTien() {
        tongTien = 0;
        for (int i = 0; i < soLoai; i++) {
            chiTiet[i].tinhThanhTien();
            tongTien += chiTiet[i].get_thanhTien();
        }
    }

    public void updateKho(QuanLyVatTu ql) {
        for (ChiTietPhieu ct : chiTiet) {

            if (ql.searchID(ct.vatTu.get_maSo()) != null) {
                ql.import_export_Goods(ct.vatTu.get_maSo(), ct.vatTu.get_soLuong());
            } else {
                ql.add(ct.vatTu);
            }
        }
    }

    public void xuat() {

        System.out.printf("%-15s %-20s %-15s %-6s %-15s %-15s %-15s\n",
                "ID", "TEN", "LOAI", "SL", "DONGIA", "HSD", "THANH TIEN");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < soLoai; i++) {
            if (chiTiet[i].vatTu == null) {
                System.out.println("Emty arr");
                return;
            }
            if (chiTiet[i].vatTu instanceof ThucPham) {
                ((ThucPham) chiTiet[i].vatTu).xuat(); 
            }else {
                chiTiet[i].vatTu.xuat();
                System.out.printf("%-15s", "//");
            }
            System.out.printf("%,-15d", chiTiet[i].thanhTien);
        }
        System.out.println("\n---------------------------------------------------------------------------------------------------------");
        tinhTongTien();
        System.out.printf("\nTong tien phieu: %,15d VND", tongTien);

    }
}
