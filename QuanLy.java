
import java.util.Scanner;

public class QuanLy {

    public void menu() {
        QuanLyVatTu qlvt = new QuanLyVatTu();
        QuanLyPhieu qlp = new QuanLyPhieu();
        Scanner sc = new Scanner(System.in);
        qlvt.add("VatTu.txt");
        qlp.readFormFile("Phieu.txt");
        int choice;
        do {
            System.out.println("\n1.===== MENU QUAN LY VAT TU =====");
            System.out.println("\n2.===== MENU QUAN LY PHIEU =====");
            System.out.println("\n0. ====Thoat====");
            System.out.print("Chon chuc nang: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    int choice1;
                    do {
                        System.out.println("\n1. Nhap n vat tu ");                      
                        System.out.println("2. Xuat danh sach vat tu");
                        System.out.println("3. Tim vat tu theo ma");
                        System.out.println("4. Xoa vat tu theo ma");
                        System.out.println("5. Sua thong tin vat tu");
                        System.out.println("6. Nhap / Xuat hang");
                        System.out.println("7. Thong ke vat tu");
                        System.out.println("8. Luu vao file");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon chuc nang: ");
                        choice1 = sc.nextInt();
                        sc.nextLine();
                        switch (choice1) {
                            case 1:
                                System.out.print("Nhap so luong vat tu muon them: ");
                                int n = sc.nextInt();
                                sc.nextLine();
                                qlvt.add_n(n);
                                break;
                            case 2:
                                qlvt.printList();
                                break;
                            case 3:
                                System.out.print("Nhap ma vat tu can tim: ");
                                String idSearch = sc.nextLine();
                                VatTu vt = qlvt.searchID(idSearch);
                                if (vt != null) {
                                    vt.xuat();
                                }
                                break;
                            case 4:
                                System.out.print("Nhap ma vat tu can xoa: ");
                                String idRemove = sc.nextLine();
                                qlvt.remove(idRemove);
                                break;
                            case 5:
                                System.out.print("Nhap ma vat tu can sua: ");
                                String idEdit = sc.nextLine();
                                qlvt.edit(idEdit);
                                break;
                            case 6:
                                System.out.print("Nhap ma vat tu: ");
                                String idGoods = sc.nextLine();
                                System.out.print("Nhap so luong (+ de nhap, - de xuat): ");
                                int sl = sc.nextInt();
                                sc.nextLine();
                                qlvt.import_export_Goods(idGoods, sl);
                                break;
                            case 7:
                                qlvt.statistics();
                                break;
                            case 8:
                                qlvt.saveToFile();
                                break;
                            case 0:
                                System.out.println("<== Quay lai");
                                break;
                            default:
                                System.out.println("Lua chon khong hop le!");
                        }

                    } while (choice1 != 0);
                    break;
                case 2:
                    int choice2;
                    do {
                        System.out.println("\n1. Them phieu ");
                        System.out.println("2. Xoa phieu theo ma");
                        System.out.println("3. Sua thong tin phieu");
                        System.out.println("4. Tim phieu theo ma");
                        System.out.println("5. Xuat danh sach phieu");
                        System.out.println("6. Luu vao file");
                        System.out.println("7. Cap nhat vao kho");
                        System.out.println("8. Thong ke");
                        System.out.println("0. Quay lai");
                        System.out.print("Chon chuc nang: ");
                        choice2 = sc.nextInt();
                        sc.nextLine();
                        switch (choice2) {
                            case 1:
                                qlp.addPhieu();
                                break;

                            case 2:
                                System.out.print("Nhap ma phieu can xoa: ");
                                String maXoa = sc.nextLine();
                                qlp.removePhieu(maXoa);
                                break;

                            case 3:
                                System.out.print("Nhap ma phieu can sua: ");
                                String maSua = sc.nextLine();
                                qlp.editPhieu(maSua);
                                break;

                            case 4:
                                System.out.print("Nhap ma phieu can tim: ");
                                String maTim = sc.nextLine();
                                Phieu kq = qlp.searchPhieu(maTim);
                                if (kq != null) {
                                    System.out.println("==> Tim thay phieu:");
                                    kq.xuat();
                                } else {
                                    System.out.println("Khong tim thay phieu co ma: " + maTim);
                                }
                                break;

                            case 5:
                                qlp.printList();
                                break;

                            case 6:
                                qlp.saveToFile();
                                break;
                            case 7:
                                qlp.updateKhoS(qlvt);

                                break;
                            case 8:
                                qlp.statistics();
                                break;
                            case 0:
                                System.out.println("<== Quay lai");
                                break;

                            default:
                                System.out.println("Lua chon khong hop le!");
                                break;
                        }
                    } while (choice2 != 0);
                    break;
                case 0:
                    System.out.println("==> Thoat chuong trinh...");
                    sc.close();
                    break;

                default:
                    System.out.println("==> Lua chon khong hop le!");
                    break;
            }

        } while (choice != 0);
    }
}
