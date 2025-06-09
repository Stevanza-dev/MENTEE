# MENTEE - Sistem Klasemen Turnamen

MENTEE adalah aplikasi berbasis Java yang digunakan untuk mengelola data klasemen turnamen, termasuk tim, pertandingan, dan hasil pertandingan. Proyek ini menggunakan arsitektur Model-View-Controller (MVC) dan terintegrasi dengan database menggunakan JDBC.

## Fitur Utama

- **Manajemen Tim:** Tambah, ubah, hapus, dan lihat data tim peserta turnamen.
- **Manajemen Pertandingan:** Atur jadwal pertandingan antar tim, input hasil pertandingan, dan cek riwayat pertandingan.
- **Klasemen Otomatis:** Sistem menghitung dan menampilkan klasemen berdasarkan hasil pertandingan yang telah diinput.
- **Antarmuka Pengguna:** GUI berbasis Java Swing yang mudah digunakan.
- **Validasi Data:** Cek duplikasi pertandingan dan validasi input data.

## Struktur Proyek

```
src/
├── Controller/      # Logika aplikasi dan penghubung antara View dan Model
├── DAO/             # Data Access Object untuk operasi database
├── DAOIMentee/      # Interface DAO
├── Koneksi/         # Koneksi ke database
├── Model/           # Representasi data (Tim, Match, dll)
└── View/            # Antarmuka pengguna (GUI)
```

## Cara Menjalankan

1. **Clone repository ini**
2. **Buka project di NetBeans atau IDE Java lain**
3. **Pastikan database sudah tersedia dan konfigurasi koneksi sudah benar**
4. **Build dan Run project**

## Dependensi

- Java 8 atau lebih baru
- JDBC Driver untuk database yang digunakan (misal: MySQL)
- [jcalendar-1.4](https://toedter.com/jcalendar/) (untuk input tanggal) (tidak jadi)

## Kontribusi

Kontribusi sangat terbuka! Silakan fork repository ini dan buat pull request untuk perbaikan atau penambahan fitur.

## Lisensi

Proyek ini menggunakan lisensi bebas sesuai kebutuhan pengembangan.

---

**Dibuat oleh:** KELOMPOK MENTEE MENTEE