
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;

public class QuanLyPhieu {

    private Phieu[] dsPhieu;
    private int soLuong;

    public QuanLyPhieu() {
        dsPhieu = new Phieu[0];
        soLuong = 0;
    }

    public void addPhieu(Phieu p) {
        dsPhieu = Arrays.copyOf(dsPhieu, soLuong + 1);
        dsPhieu[soLuong++] = p;
    }

    public void addPhieu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap loai phieu (1: Nhap, 2: Xuat): ");
        int loai = sc.nextInt();
        sc.nextLine();

        System.out.print("Nhap ma phieu: ");
        String ma = sc.nextLine();

        LocalDate ngay = LocalDate.now();

        Phieu p;
        if (loai == 1) {
            System.out.print("Nhap ten nha cung cap: ");
            String ncc = sc.nextLine();
            p = new PhieuNhap(ma, ngay, ncc);
        } else {
            System.out.print("Nhap ten nguoi nhan: ");
            String nn = sc.nextLine();
            p = new PhieuXuat(ma, ngay, nn);
        }

        System.out.print("Nhap so loai vat tu: ");
        int n = sc.nextInt();
        sc.nextLine();
        p.add(n);

        addPhieu(p);
        System.out.println("==> Them phieu thanh cong!");
    }

    public void removePhieu(String maPhieu) {
        int index = -1;
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieu[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Khong tim thay phieu co ma: " + maPhieu);
            return;
        }

        for (int i = index; i < soLuong - 1; i++) {
            dsPhieu[i] = dsPhieu[i + 1];
        }

        dsPhieu = Arrays.copyOf(dsPhieu, soLuong - 1);
        soLuong--;
        System.out.println("==> Da xoa phieu co ma " + maPhieu);
    }

    public void editPhieu(String maPhieu) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieu[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                System.out.println("Tim thay phieu:");
                dsPhieu[i].xuat();

                System.out.println("\nBan muon sua gi?");
                System.out.println("1. Sua ngay");
                System.out.println("2. Them vat tu");
                System.out.println("3. Xoa vat tu");
                System.out.print("Chon: ");
                int c = sc.nextInt();
                sc.nextLine();

                switch (c) {
                    case 1:
                        System.out.print("Nhap ngay moi (yyyy-mm-dd): ");
                        dsPhieu[i].setThoiGian(LocalDate.parse(sc.nextLine()));
                        break;
                    case 2:
                        System.out.print("Nhap so vat tu muon them: ");
                        int n = sc.nextInt();
                        sc.nextLine();
                        dsPhieu[i].add(dsPhieu[i].get_soLoai() + n);
                        break;
                    case 3:
                        System.out.print("Nhap ma vat tu can xoa: ");
                        String maVT = sc.nextLine();
                        dsPhieu[i].remove(maVT);
                        break;
                    default:
                        System.out.println("Khong hop le!");
                        break;
                }
                System.out.println("==> Sua thanh cong!");
                return;
            }
        }
        System.out.println("Khong tim thay phieu co ma " + maPhieu);
    }

    public Phieu searchPhieu(String maPhieu) {
        for (int i = 0; i < soLuong; i++) {
            if (dsPhieu[i].getMaPhieu().equalsIgnoreCase(maPhieu)) {
                return dsPhieu[i];
            }
        }
        return null;
    }

    public void printList() {
        if (soLuong == 0) {
            System.out.println("Danh sach phieu rong!");
            return;
        }
        System.out.println("\n=== DANH SACH PHIEU ===");
        for (int i = 0; i < soLuong; i++) {
            dsPhieu[i].xuat();
            System.out.println("\n==================");
        }
    }

    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/Phieu.txt"))) {
            for (Phieu p : dsPhieu) {
                if (p instanceof PhieuNhap) {
                    PhieuNhap pn = (PhieuNhap) p;
                    bw.write("PhieuNhap " + pn.getMaPhieu() + " " + pn.getThoiGian() + " " + pn.getNhaCungCap());
                    bw.newLine();
                } else if (p instanceof PhieuXuat) {
                    PhieuXuat px = (PhieuXuat) p;
                    bw.write("PhieuXuat " + px.getMaPhieu() + " " + px.getThoiGian() + " " + px.getNguoiNhan());
                    bw.newLine();
                }

                for (ChiTietPhieu ct : p.getChiTiet()) {
                    if (ct == null || ct.vatTu == null) {
                        continue;
                    }

                    VatTu vt = ct.vatTu;
                    if (vt instanceof ThucPham) {
                        ThucPham tp = (ThucPham) vt;
                        bw.write("CT " + tp.get_maSo() + " " + tp.get_ten() + " "
                                + tp.get_loai() + " " + tp.get_soLuong() + " "
                                + tp.get_donGia() + " " + ct.get_thanhTien() + " "
                                + tp.get_HSD());
                    } else {
                        bw.write("CT " + vt.get_maSo() + " " + vt.get_ten() + " "
                                + vt.get_loai() + " " + vt.get_soLuong() + " "
                                + vt.get_donGia() + " " + ct.get_thanhTien());
                    }
                    bw.newLine();
                }

                bw.newLine(); 
            }
            System.out.println("==> Ghi file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi ghi file: " + e.getMessage());
        }
    }

    public void readFormFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/" + filename))) {
            String line;
            Phieu phieuHienTai = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\s+");

                if (parts[0].equalsIgnoreCase("PhieuNhap")) {
                    String ma = parts[1];
                    LocalDate ngay = LocalDate.parse(parts[2]);
                    String nhaCC = parts[3];
                    phieuHienTai = new PhieuNhap(ma, ngay, nhaCC);

                    dsPhieu = Arrays.copyOf(dsPhieu, soLuong + 1);
                    dsPhieu[soLuong++] = phieuHienTai;

                } else if (parts[0].equalsIgnoreCase("PhieuXuat")) {
                    String ma = parts[1];
                    LocalDate ngay = LocalDate.parse(parts[2]);
                    String nguoiNhan = parts[3];
                    phieuHienTai = new PhieuXuat(ma, ngay, nguoiNhan);

                    dsPhieu = Arrays.copyOf(dsPhieu, soLuong + 1);
                    dsPhieu[soLuong++] = phieuHienTai;

                } else if (parts[0].equalsIgnoreCase("CT")) {
                    if (phieuHienTai == null) {
                        continue;
                    }

                    
                    String maVT = parts[1];
                    String ten = parts[2];
                    String loai = parts[3];
                    int sl = Integer.parseInt(parts[4]);
                    int dg = Integer.parseInt(parts[5]);
                    long tt = Long.parseLong(parts[6]);

                    VatTu vt;
                    if (parts.length == 8) {
                        LocalDate hsd = LocalDate.parse(parts[7]);
                        vt = new ThucPham(maVT, ten, sl, dg, hsd);
                    } else {
                        vt = new VatTu(maVT, ten, loai, sl, dg);
                    }

                    ChiTietPhieu ct = new ChiTietPhieu();
                    ct.vatTu = vt;
                    ct.thanhTien = tt;
                    phieuHienTai.addChiTiet(ct);
                }
            }
        System.out.println("Da doc file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi doc file: " + e.getMessage());
        }
    }

    public void updateKhoS(QuanLyVatTu ql) {
        for (Phieu p : dsPhieu) {
            p.updateKho(ql);
        }
    }

    public void statistics() {
        long[] tien = new long[2];
        tien[0] = tien[1] = 0;
        Phieu max = new Phieu("123", LocalDate.now());
        Phieu min = new Phieu("123", LocalDate.now());
        max.tongTien = -1;
        min.tongTien = Long.MAX_VALUE;
        for (Phieu p : dsPhieu) {
            if (p instanceof PhieuNhap) {
                tien[0] += ((PhieuNhap) p).getTongTien(); 
            }else {
                tien[1] += ((PhieuXuat) p).getTongTien();
            }
            if (p.tongTien < min.tongTien) {
                min = p;
            }
            if (p.tongTien > max.tongTien) {
                max = p;
            }
        }
        System.out.printf("\nTong tien nhap: %,10d VND", tien[0]);
        System.out.printf("\nTong tien xuat: %,10d VND", tien[1]);
        System.out.println("\nPhieu co gia tri lon nhat:");
        max.xuat();
        System.out.println("\nPhieu co gia tri nho nhat:");
        min.xuat();
    }
}
