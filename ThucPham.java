
import java.time.LocalDate;

public class ThucPham extends VatTu {

   
    private LocalDate HSD;

    
    public ThucPham(String maSo, String ten, int soLuong, long donGia, LocalDate HSD) {
        super(maSo, ten, "ThucPham", soLuong, donGia);
        this.HSD = HSD;
    }

    public ThucPham(ThucPham tp) {
        super(tp);
        this.HSD = tp.HSD;
    }
   
    public LocalDate get_HSD() {
        return HSD;
    }
   

    public void set_HSD(LocalDate HSD) {
        this.HSD = HSD;
    }
    

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("%-15s", HSD);
    }
}
