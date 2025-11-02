
import java.time.LocalDate;

public class PhieuNhap extends Phieu {

    private String nhaCungCap;

    public PhieuNhap(String ma, LocalDate tg, String ncc) {
        super(ma, tg);
        this.nhaCungCap = ncc;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    @Override
    public void xuat() {
        System.out.println("\n=== PHIEU NHAP ===");
        System.out.println("Ma phieu: " + maPhieu);
        System.out.println("Ngay: " + thoiGian);
        System.out.println("Nha cung cap: " + nhaCungCap);
        super.xuat();
    }
}
