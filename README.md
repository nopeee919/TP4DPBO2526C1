# JANJI

Saya Novelio Yeheskiel Kapahang dengan NIM 2503048 mengerjakan TP 4 dalam mata kuliah Desain Dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin


# PeopleData — Aplikasi Manajemen Data People

## 1. Deskripsi Program

PeopleData merupakan aplikasi berbasis Java Swing yang digunakan untuk mengelola data orang. Aplikasi ini memiliki fitur untuk menambahkan, mengubah, dan menghapus data melalui form input serta menampilkan data dalam bentuk tabel.

## 2. Desain Program

Program terdiri dari dua bagian utama:

- **Person.java**: Class yang menyimpan data orang, yaitu ID, nama, tahun lahir, kategori, dan status keanggotaan.
- **PeopleMenu.java**: Class yang mengatur tampilan GUI dan proses pengelolaan data, mulai dari input hingga perubahan data pada tabel.

Antarmuka program menggunakan komponen Java Swing, seperti `JTextField`, `JComboBox`, `JRadioButton`, `JButton`, dan `JTable`.

## 3. Fungsi Program

1. **Tambah Data** — Menambahkan data baru berdasarkan input pengguna.
2. **Ubah Data** — Mengubah data yang telah dipilih dari tabel, kecuali ID.
3. **Hapus Data** — Menghapus data yang dipilih setelah pengguna memberikan konfirmasi.
4. **Batal** — Mengosongkan form dan membatalkan proses input atau perubahan.
5. **Tampilkan Data** — Menampilkan seluruh data dalam tabel, termasuk data awal yang telah disediakan.
6. **Validasi Input** — Memastikan data wajib diisi, tahun lahir berupa angka yang valid, dan ID tidak duplikat.

## 4. Alur Program

1. Program dijalankan dan menampilkan form serta data awal pada tabel.
2. Pengguna memilih tindakan yang ingin dilakukan.
3. Untuk menambah data, pengguna mengisi form lalu menekan tombol **Tambah**.
4. Untuk mengubah atau menghapus data, pengguna memilih data pada tabel terlebih dahulu.
5. Jika data diubah, program memperbarui informasi pada tabel. Jika data dihapus, program meminta konfirmasi sebelum menghapusnya.
6. Setelah proses selesai, tabel diperbarui dan form dikosongkan jika diperlukan.


# DOKUMENTASI

## TAMBAH DATA

![tambah](Dokumentasi/tambah_data.gif)

## UPDATE DATA

![tambah](Dokumentasi/update_data.gif)

## HAPUS DATA

![tambah](Dokumentasi/hapus_data.gif)