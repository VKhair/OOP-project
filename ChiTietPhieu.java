
public class ChiTietPhieu {

    protected VatTu vatTu;
    protected long thanhTien;

    public ChiTietPhieu() {
        vatTu = new VatTu();
        thanhTien = 0;
    }

    public ChiTietPhieu(VatTu vt, long thanhTien) {
        this.vatTu = vt;
        this.thanhTien = thanhTien;
    }

    public long get_thanhTien() {
        return thanhTien;
    }

    public void set_thanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void tinhThanhTien() {
        thanhTien = 0;
        thanhTien += (vatTu.get_donGia() * vatTu.get_soLuong());
    }

    public void xuat() {
        vatTu.xuat();
        tinhThanhTien();
        System.out.printf("%15d\n", thanhTien);
    }
}
