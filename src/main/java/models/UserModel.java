package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    private String taiKhoan;
    private String email;
    private String soDT;
    private String maNhom;
    private String maLoaiNguoiDung;
    private String hoTen;
    private String accessToken;
    private List<ChiTietKhoaHocGhiDanh> chiTietKhoaHocGhiDanh;

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public String getMaNhom() {
        return maNhom;
    }

    public void setMaNhom(String maNhom) {
        this.maNhom = maNhom;
    }

    public String getMaLoaiNguoiDung() {
        return maLoaiNguoiDung;
    }

    public void setMaLoaiNguoiDung(String maLoaiNguoiDung) {
        this.maLoaiNguoiDung = maLoaiNguoiDung;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public List<ChiTietKhoaHocGhiDanh> getChiTietKhoaHocGhiDanh() {
        return chiTietKhoaHocGhiDanh;
    }

    public void setChiTietKhoaHocGhiDanh(List<ChiTietKhoaHocGhiDanh> chiTietKhoaHocGhiDanh) {
        this.chiTietKhoaHocGhiDanh = chiTietKhoaHocGhiDanh;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ChiTietKhoaHocGhiDanh
    {
        private String maKhoaHoc;
        private String tenKhoaHoc;
        private String biDanh;
        private String moTa;
        private int luotXem;
        private String hinhAnh;
        private String ngayTao;
        private int danhGia;

        public String getMaKhoaHoc() {
            return maKhoaHoc;
        }

        public void setMaKhoaHoc(String maKhoaHoc) {
            this.maKhoaHoc = maKhoaHoc;
        }

        public String getTenKhoaHoc() {
            return tenKhoaHoc;
        }

        public void setTenKhoaHoc(String tenKhoaHoc) {
            this.tenKhoaHoc = tenKhoaHoc;
        }

        public String getBiDanh() {
            return biDanh;
        }

        public void setBiDanh(String biDanh) {
            this.biDanh = biDanh;
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

        public String getNgayTao() {
            return ngayTao;
        }

        public void setNgayTao(String ngayTao) {
            this.ngayTao = ngayTao;
        }

        public int getDanhGia() {
            return danhGia;
        }

        public void setDanhGia(int danhGia) {
            this.danhGia = danhGia;
        }
    }
}
