
import java.time.LocalDate;

public class PhieuXuat extends Phieu {

    private String nguoiNhan;

    public PhieuXuat(String ma, LocalDate tg, String nguoiNhan) {
        super(ma, tg);
        this.nguoiNhan = nguoiNhan;
    }

    public String getNguoiNhan() {
        return nguoiNhan;
    }

    public void setNguoiNhan(String nguoiNhan) {
        this.nguoiNhan = nguoiNhan;
    }

    @Override
    public void updateKho(QuanLyVatTu ql) {
        for (ChiTietPhieu ct : chiTiet) {
            boolean have = false;
            if (ql.searchID(ct.vatTu.get_maSo()) != null) {
                ql.import_export_Goods(ct.vatTu.get_maSo(), -ct.vatTu.get_soLuong());
                have = true;
            }
            if (!have) {
                ql.add(ct.vatTu);
            }
        }
    }

    @Override
    public void xuat() {
        System.out.println("\n=== PHIEU XUAT ===");
        System.out.println("Ma phieu: " + maPhieu);
        System.out.println("Ngay: " + thoiGian);
        System.out.println("Nguoi nhan: " + nguoiNhan);
        super.xuat();
    }
}
