package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseModel {
    private String maKhoaHoc;
    private String biDanh;
    private String tenKhoaHoc;
    private String moTa;
    private int luotXem;
    private String hinhAnh;
    private String maNhom;
    private String ngayTao;
    private int soLuongHocVien;
    private NguoiTao nguoiTao;
    private DanhMucKhoaHoc danhMucKhoaHoc;

    public String getMaKhoaHoc() {
        return maKhoaHoc;
    }

    public void setMaKhoaHoc(String maKhoaHoc) {
        this.maKhoaHoc = maKhoaHoc;
    }

    public String getBiDanh() {
        return biDanh;
    }

    public void setBiDanh(String biDanh) {
        this.biDanh = biDanh;
    }

    public String getTenKhoaHoc() {
        return tenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        this.tenKhoaHoc = tenKhoaHoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getLuotXem() {
        return luotXem;
    }

    public void setLuotXem(int luotXem) {
        this.luotXem = luotXem;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getSoLuongHocVien() {
        return soLuongHocVien;
    }

    public void setSoLuongHocVien(int soLuongHocVien) {
        this.soLuongHocVien = soLuongHocVien;
    }

    public NguoiTao getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(NguoiTao nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public DanhMucKhoaHoc getDanhMucKhoaHoc() {
        return danhMucKhoaHoc;
    }

    public void setDanhMucKhoaHoc(DanhMucKhoaHoc danhMucKhoaHoc) {
        this.danhMucKhoaHoc = danhMucKhoaHoc;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NguoiTao {
        private String taiKhoan;
        private String hoTen;
        private String maLoaiNguoiDung;
        private String tenLoaiNguoiDung;

        public String getTaiKhoan() {
            return taiKhoan;
        }

        public void setTaiKhoan(String taiKhoan) {
            this.taiKhoan = taiKhoan;
        }

        public String getHoTen() {
            return hoTen;
        }

        public void setHoTen(String hoTen) {
            this.hoTen = hoTen;
        }

        public String getMaLoaiNguoiDung() {
            return maLoaiNguoiDung;
        }

        public void setMaLoaiNguoiDung(String maLoaiNguoiDung) {
            this.maLoaiNguoiDung = maLoaiNguoiDung;
        }

        public String getTenLoaiNguoiDung() {
            return tenLoaiNguoiDung;
        }

        public void setTenLoaiNguoiDung(String tenLoaiNguoiDung) {
            this.tenLoaiNguoiDung = tenLoaiNguoiDung;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DanhMucKhoaHoc {
        private String maDanhMucKhoahoc;
        private String tenDanhMucKhoaHoc;

        public String getMaDanhMucKhoahoc() {
            return maDanhMucKhoahoc;
        }

        public void setMaDanhMucKhoahoc(String maDanhMucKhoahoc) {
            this.maDanhMucKhoahoc = maDanhMucKhoahoc;
        }

        public String getTenDanhMucKhoaHoc() {
            return tenDanhMucKhoaHoc;
        }

        public void setTenDanhMucKhoaHoc(String tenDanhMucKhoaHoc) {
            this.tenDanhMucKhoaHoc = tenDanhMucKhoaHoc;
        }
    }
}
