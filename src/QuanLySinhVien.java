import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class QuanLySinhVien extends SinhVien{
    public static final String FILE_NAME = "students.csv";
    public static final String COMMA = ",";
    public ArrayList<SinhVien> students;

    public ArrayList<SinhVien> getStudents() {return students;}

    public QuanLySinhVien() {
        ArrayList<SinhVien> sinhVienList = new ArrayList<>();
        this.students = sinhVienList ;
    }


    public void menu() {
        char choice = '?';
        while (choice != '0') {
            System.out.println("--CHUONG TRINH QUAN LY SINH VIEN--");
            System.out.println("Chon chuc nang theo so:");
            System.out.println("1. Xem danh sach sinh vien");
            System.out.println("2. Them moi");
            System.out.println("3. Cap nhat");
            System.out.println("4. Xoa");
            System.out.println("5. Thoat");
            System.out.println("Chon chuc nang: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextLine().charAt(0);
            switch (choice) {
                case '1': {
                    List<SinhVien> listSinhVien = docDanhSachSV();
                    showDanhSach(listSinhVien);
                    break;}
                case '2': {
                    SinhVien student = themDataSV();
                    themMoiSV(student);
                    break;}
                case '3': {
                    int id = inputId();
                    updateInformationById(id);
                    break;}
                case '4': {
                    int id = inputId();
                    deleteSV(id);
                    break;}
                case '5': {
                    System.out.println("Thoat");
                    return;}
            }
        }
    }



    // Xem danh sach sinh vien
    private List<SinhVien> docDanhSachSV () {
        List<String> listLine = new ArrayList<>();
        List<SinhVien> sinhVienList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                listLine.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String line : listLine) {
            String[] lineSplit = line.split(COMMA);
            SinhVien student = new SinhVien(Integer.parseInt(lineSplit[0]), lineSplit[1], Integer.parseInt(lineSplit[2]), lineSplit[3], lineSplit[4], Double.parseDouble(lineSplit[5]));
            sinhVienList.add(student);
        }
        return sinhVienList;
    }
    private void showDanhSach (List<SinhVien> sinhVienList) {
        for (SinhVien sinhVien : sinhVienList) {
            System.out.println("Ma SV: "+ sinhVien.getId()+ COMMA + " Ho va ten: "+sinhVien.getHoTen()+ COMMA +" Tuoi: "+ sinhVien.getTuoi() + COMMA + " Gioi Tinh: "+ sinhVien.getGioiTinh() + COMMA + " Dia Chi: "+ sinhVien.getDiaChi() + COMMA + " Diem TB: "+ sinhVien.getDiemTB());
        }
    }

    // Them moi sinh vien
    public boolean checkID(int id) {
       /*List<SinhVien> sinhVienList = docDanhSachSV();
        if (!sinhVienList.isEmpty()) {
            for (SinhVien student : sinhVienList) {
                if (student.getId() == id)
                    return true;
            }
        }
        return false; */
         if (!students.isEmpty()) {
            for (SinhVien sinhVien : students) {
                if (sinhVien.getId() == id)
                    return true;
            }
        }
        return false;
    }

    private SinhVien themDataSV() {
        int id;
        String hoTen;
        int tuoi;
        String gioiTinh;
        String diaChi;
        double diemTB;
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten SV:");
        hoTen = sc.nextLine();
        boolean isID = true;
        do {
            System.out.println("Nhap id:");
            id = sc.nextInt();
            if (checkID(id)) {
                System.out.println("Ma sinh vien bi trung lap");
            } else {
                isID = false;
            }
        }
        while (isID);
        System.out.println("Nhap tuoi:");
        tuoi = sc.nextInt();
        System.out.println("Nhap gioi tinh:");
        sc.nextLine();
        gioiTinh = sc.nextLine();
        System.out.println("Nhap dia chi:");
        diaChi = sc.nextLine();
        System.out.println("Nhap diem:");
        diemTB = sc.nextDouble();

        SinhVien sinhVien = new SinhVien(id,hoTen,tuoi,gioiTinh,diaChi,diemTB);
        return sinhVien;
    }

    private void themMoiSV(SinhVien sinhVien) {
        students.add(sinhVien);
        String line = null;
        line = sinhVien.getId() + COMMA + sinhVien.getHoTen() + COMMA + sinhVien.getTuoi() + COMMA + sinhVien.getGioiTinh() + COMMA + sinhVien.getDiaChi() + COMMA + sinhVien.getDiemTB();
        try {
            FileWriter fileWriter = new FileWriter(FILE_NAME, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private int inputId() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id");
        int id = sc.nextInt();
        return id;
    }

    private void updateInformationById(int id) {
        List<SinhVien> sinhVienList = docDanhSachSV();
        for (int i = 0; i < sinhVienList.size(); i++) {
            if (sinhVienList.get(i).getId() == id) {
                String hoTen;
                int tuoi;
                String gioiTinh;
                String diaChi;
                double diemTB;
                Scanner sc = new Scanner(System.in);
                System.out.println("Nhap ho ten:");
                hoTen = sc.nextLine();
                System.out.println("Nhap tuoi:");
                tuoi = sc.nextInt();
                System.out.println("Nhap gioi tinh:");
                gioiTinh = sc.nextLine();
                sc.nextLine();
                System.out.println("Nhap dia chi:");
                diaChi = sc.nextLine();
                System.out.println("Nhap diem:");
                diemTB = sc.nextDouble();
                sinhVienList.get(i).setHoTen(hoTen);
                sinhVienList.get(i).setTuoi(tuoi);
                sinhVienList.get(i).setGioiTinh(gioiTinh);
                sinhVienList.get(i).setDiaChi(diaChi);
                sinhVienList.get(i).setDiemTB(diemTB);

            }
        }
        for (int i = 0; i < sinhVienList.size(); i++) {
            String line = null;
            line = line = sinhVienList.get(i).getId() + COMMA + sinhVienList.get(i).getHoTen() + COMMA + sinhVienList.get(i).getTuoi() + COMMA + sinhVienList.get(i).getGioiTinh() + COMMA + sinhVienList.get(i).getDiaChi() + COMMA + sinhVienList.get(i).getDiemTB();
            try {
                FileWriter fileWriter;
                if (i == 0) {
                    fileWriter = new FileWriter(FILE_NAME, false);
                } else {
                    fileWriter = new FileWriter(FILE_NAME, true);
                }
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


        private void deleteSV(int id) {
            List<SinhVien> sinhVienList = docDanhSachSV();
            for (int i = 0; i < sinhVienList.size(); i++) {
                if (sinhVienList.get(i).getId() == id) {
                    sinhVienList.remove(i);
                    break;
                } else {
                    System.out.println("Khong tim duoc ma sinh vien tren");
                    break;
                }
            }
            for (int i = 0; i < sinhVienList.size(); i++) {
                String line = null;
                line = line = sinhVienList.get(i).getId() + COMMA + sinhVienList.get(i).getHoTen() + COMMA + sinhVienList.get(i).getTuoi() + COMMA + sinhVienList.get(i).getGioiTinh() + COMMA + sinhVienList.get(i).getDiaChi() + COMMA + sinhVienList.get(i).getDiemTB();
                try {
                    FileWriter fileWriter;
                    if (i == 0) {
                        fileWriter = new FileWriter(FILE_NAME, false);
                    } else {
                        fileWriter = new FileWriter(FILE_NAME, true);
                    }
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(line);
                    bufferedWriter.newLine();
                    bufferedWriter.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }




}