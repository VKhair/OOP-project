
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class QuanLyVatTu {

    protected int n;
    protected VatTu[] danhSach;

    public QuanLyVatTu() {
        this.n = 0;
        danhSach = new VatTu[n];
    }

   
    public int get_n() {
        return n;
    }

    public VatTu[] get_danhSach() {
        return danhSach;
    }

   
    public void add_n(int n) {
        if (n <= 0)
            return;
        danhSach = Arrays.copyOf(danhSach, this.n + n);
        Scanner sc = new Scanner(System.in);
        for (int i = this.n; i < this.n + n; i++) {
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

            if (loai.equalsIgnoreCase("ThucPham")) {
                System.out.print("Nhap HSD (yyyy-mm-dd): ");
                String s = sc.nextLine();
                LocalDate hsd = LocalDate.parse(s);
                danhSach[i] = new ThucPham(ma, ten, sl, dg, hsd);
            } else {
                danhSach[i] = new VatTu(ma, ten, loai, sl, dg);
            }
        }
        this.n += n;
    }

  
    public void printList() {
        System.out.printf("%-15s %-20s %-15s %-6s %-15s %-15s\n",
                "ID", "TEN", "LOAI", "SL", "DONGIA", "HSD");
        System.out.println("----------------------------------------------------------------------------------------");
        for (VatTu vt : danhSach) {
            if (vt == null) {
                System.out.println("Emty arr");
                return;
            }
            if (vt instanceof ThucPham) {
                ((ThucPham) vt).xuat(); 
            }else {
                vt.xuat();
                System.out.printf("%-15s", "//");
            }
        }
        System.out.println("\n--------------------------------------------------------------------------------------");
    }

  
    public void add(VatTu vt) {
        danhSach = Arrays.copyOf(danhSach, n + 1);
        danhSach[n] = vt;
        n++;
    }


    public void add() {
        Scanner sc = new Scanner(System.in);
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

        if (loai.equalsIgnoreCase("ThucPham")) {
            System.out.print("Nhap HSD (yyyy-mm-dd): ");
            LocalDate hsd = LocalDate.parse(sc.nextLine());
            add(new ThucPham(ma, ten, sl, dg, hsd));
        } else {
            add(new VatTu(ma, ten, loai, sl, dg));
        }
    }

    
    public void add(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.trim().split("\\s+");
                if (p.length < 5) {
                    continue;
                }

                String ma = p[0];
                String ten = p[1];
                String loai = p[2];
                int sl = Integer.parseInt(p[3]);
                int dg = Integer.parseInt(p[4]);

                if (loai.equalsIgnoreCase("ThucPham") && p.length >= 6) {
                    LocalDate hsd = LocalDate.parse(p[5]);
                    add(new ThucPham(ma, ten, sl, dg, hsd));
                } else {
                    add(new VatTu(ma, ten, loai, sl, dg));
                }
            }
            System.out.println("Da doc file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/VatTu.txt"))) {
            for (VatTu vt : danhSach) {
                if (vt == null) {
                    continue;
                }

                if (vt instanceof ThucPham) {
                    ThucPham tp = (ThucPham) vt;
                    bw.write(tp.get_maSo() + " " + tp.get_ten() + " " + tp.get_loai() + " "
                            + tp.get_soLuong() + " " + tp.get_donGia() + " " + tp.get_HSD());
                } else {
                    bw.write(vt.get_maSo() + " " + vt.get_ten() + " " + vt.get_loai() + " "
                            + vt.get_soLuong() + " " + vt.get_donGia());
                }
                bw.newLine();
            }

            System.out.println("Da ghi file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

 
    public VatTu searchID(String id) {
        for (VatTu vt : danhSach) {
            if (vt.get_maSo().equalsIgnoreCase(id)) {
                return vt;
            }
        }
        System.out.println("Vat tu " + id + " chua co");
        return null;
    }

    public int search_ID_index(String id) {
        int index = -1;
        for (int i = 0; i < n; i++) {
            if (danhSach[i].get_maSo().equalsIgnoreCase(id)) {
                index = i;
                return index;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay vat tu ");
        }
        return -1;
    }

  
    public void remove(String id) {
        int index = search_ID_index(id);
        if (index == -1) {
            return;
        }
        for (int i = index; i < n - 1; i++) {
            danhSach[i] = danhSach[i + 1];
        }

        danhSach = Arrays.copyOf(danhSach, n - 1);
        n--;
        System.out.println("Da xoa thanh cong!");
    }

  
    public void import_export_Goods(String id, int sl) {
        int index = search_ID_index(id);
        if (index == -1) {
            return;
        }
        if ((sl < 0 && danhSach[index].get_soLuong() >= -sl) || sl > 0) {
            danhSach[index].set_soLuong(danhSach[index].get_soLuong() + sl); 
        }else {
            System.out.println("\nKhong du so luong yeu cau");
        }
    }

  
    public void edit(String id) {
        VatTu vt = searchID(id);
        if (vt == null) {
            return;
        }

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            vt.xuat();
            System.out.println("\n--- EDIT MENU ---");
            System.out.println("1. Sua ten");
            if(!(vt instanceof ThucPham))
                System.out.println("2. Sua loai");
            System.out.println("3. Sua so luong");
            System.out.println("4. Sua don gia");
            if (vt instanceof ThucPham) 
                System.out.println("5. Sua HSD");
            System.out.println("0. Thoat");
            System.out.print("Lua chon: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Nhap ten moi: ");
                    vt.set_ten(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Nhap loai moi: ");
                    vt.set_loai(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Nhap so luong moi: ");
                    vt.set_soLuong(sc.nextInt());
                    break;
                case 4:
                    System.out.print("Nhap don gia moi: ");
                    vt.set_donGia(sc.nextLong());
                    break;
                case 5:
                    if (vt instanceof ThucPham) {
                        System.out.print("Nhap HSD moi (yyyy-mm-dd): ");
                        ((ThucPham) vt).set_HSD(LocalDate.parse(sc.nextLine()));
                    }
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Lua chon sai!");
            }
        }
    }

  
    public void statistics() {
        int[] count = new int[5];
        int[] count_num = new int[5];
        long tongTien = 0;
        for (VatTu vt : danhSach) {
            tongTien += vt.get_donGia() * vt.get_soLuong();
            if (vt instanceof ThucPham) {
                count[0]++;
                count_num[0] += vt.get_soLuong();
            }
            if (vt.get_loai().equalsIgnoreCase("DoDienTu")) {
                count[1]++;
                count_num[1] += vt.get_soLuong();
            }
            if (vt.get_loai().equalsIgnoreCase("DoTieuDung")) {
                count[2]++;
                count_num[2] += vt.get_soLuong();
            }
            if (vt.get_loai().equalsIgnoreCase("PhuongTien")) {
                count[3]++;
                count_num[3] += vt.get_soLuong();
            }
            if (vt.get_loai().equalsIgnoreCase("CoSoVatChat")) {
                count[4]++;
                count_num[4] += vt.get_soLuong();
            }
        }
        System.out.printf("\n%-15s %-10s %-10s", "Loai", "So loai", "Tong so luong");
        System.out.println("\n----------------------------------------------");
        System.out.printf("\n%-15s %-10s %-10s", "Thuc pham", count[0], count_num[0]);
        System.out.printf("\n%-15s %-10s %-10s", "Do dien tu", count[1], count_num[1]);
        System.out.printf("\n%-15s %-10s %-10s", "Do tieu dung", count[2], count_num[2]);
        System.out.printf("\n%-15s %-10s %-10s", "Phuong tien", count[3], count_num[3]);
        System.out.printf("\n%-15s %-10s %-10s", "Co so vat chat", count[4], count_num[4]);
        System.out.printf("\n%-15s %-10s %-10s", "Vat tu", this.n, count_num[0] + count_num[1] + count_num[2] + count_num[3] + count_num[4]);
        System.out.println("\n----------------------------------------------");
        System.out.printf("\nTong gia tri:  %,-15d VND\n", tongTien);
    }
}
