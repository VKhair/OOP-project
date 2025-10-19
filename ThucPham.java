import java.time.LocalDate;
public class ThucPham extends VatTu{
    //Atttribute
    private LocalDate HSD;
    //Constructor
    public  ThucPham(String maSo, String ten, int soLuong, int donGia, LocalDate HSD){ 
        super( maSo,  ten, "ThucPham",  soLuong, donGia);
        this.HSD = HSD;
    }
    public ThucPham(ThucPham tp){
        super(tp);
        this.HSD = tp.HSD;
    }
    //Method
        //Get
    public LocalDate get_HSD(){return HSD;}
        //Set
    public void set_HSD(LocalDate HSD){ this.HSD = HSD;}
        //print
    @Override public void xuat(){
      super.xuat();
      System.out.printf("%-15s" ,HSD);
    }
}
