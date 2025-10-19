import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestQuanLy {
    public static void main(String[] args) {
        QuanLyVatTu ql = new QuanLyVatTu();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== MENU QUAN LY VAT TU =====");
            System.out.println("1. Nhap n vat tu ");
            System.out.println("2. Them vat tu (nhap tay)");
            System.out.println("3. Them vat tu tu file");
            System.out.println("4. Xuat danh sach vat tu");
            System.out.println("5. Tim vat tu theo ma");
            System.out.println("6. Xoa vat tu theo ma");
            System.out.println("7. Sua thong tin vat tu");
            System.out.println("8. Nhap / Xuat hang");
            System.out.println("9. Thong ke vat tu");
            System.out.println("0. Thoat");
            System.out.print("Chon chuc nang: ");
            choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Nhap so luong vat tu muon them: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    ql.add_n(n);
                    break;

                case 2:
                    ql.add();
                    break;

                case 3:
                    System.out.print("Nhap ten file (vi du: vat_tu.txt): ");
                    String filename = sc.nextLine();
                    try {
                        ql.add(filename);
                        System.out.println("Doc file thanh cong!");
                    } catch (FileNotFoundException e) {
                        System.out.println("Khong tim thay file!");
                    }
                    break;

                case 4:
                    ql.printList();
                    break;

                case 5:
                    System.out.print("Nhap ma vat tu can tim: ");
                    String idSearch = sc.nextLine();
                    ql.searchID(idSearch);
                    break;

                case 6:
                    System.out.print("Nhap ma vat tu can xoa: ");
                    String idRemove = sc.nextLine();
                    ql.remove(idRemove);
                    break;

                case 7:
                    System.out.print("Nhap ma vat tu can sua: ");
                    String idEdit = sc.nextLine();
                    ql.edit(idEdit);
                    break;

                case 8:
                    System.out.print("Nhap ma vat tu: ");
                    String idGoods = sc.nextLine();
                    System.out.print("Nhap so luong (+ de nhap, - de xuat): ");
                    int sl = sc.nextInt();
                    sc.nextLine();
                    ql.import_export_Goods(idGoods, sl);
                    break;

                case 9:
                    ql.statistics();
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh...");
                    sc.close();
                    break;

                default:
                    System.out.println("Lua chon khong hop le!");
            }

        } while (choice != 0);
    }
}
