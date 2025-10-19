public class VatTu {
    //Attribute
    private String maSo;
    private String ten;
    private String loai;
    private int soLuong;
    private int donGia;
    //Constructor
    public  VatTu() {}
    public  VatTu(String maSo, String ten, String loai, int soLuong, int donGia){
        this.maSo = maSo;
        this.ten = ten;
        this.loai = loai;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    public  VatTu(VatTu vt){
        this.maSo = vt.maSo;
        this.ten = vt.ten;
        this.loai = vt.loai;
        this.soLuong = vt.soLuong;
        this.donGia = vt.donGia;
    }
    //Method
        //Get
    public String get_maSo(){return maSo;}
    public String get_ten(){return ten;}
    public String get_loai(){return loai;}
    public int get_soLuong(){return soLuong;}
    public int get_donGia(){return donGia;}
        //Set
    public void set_maSo(String maSo){ this.maSo = maSo; }
    public void set_ten(String ten){ this.ten = ten;}
    public void set_loai(String loai){ this.loai = loai;}
    public void set_soLuong(int soLuong){ 
        if(soLuong < 0 ) return;
        this.soLuong = soLuong;
    }
    public void set_donGia(int donGia){ this.donGia = donGia;}
        //print
    public void xuat(){
        System.out.printf("\n%-15s %-20s %-15s %-6d %-15d",maSo,ten,loai,soLuong,donGia);
    }
}
