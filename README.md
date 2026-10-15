# **Sistem Pengelolaan Layanan Perawatan Hewan - Kya's Pet Care**

# Vidya Khansa Mizan | 2509116052 | Sistem Informasi B 2025

## Deskripsi Program
Sistem Pengelolaan Layanan Perawatan Hewan adalah sebuah program berbasis bahasa pemrograman Java yang digunakan untuk mengelola data layanan perawatan hewan secara sederhana. Program ini berfokus pada layanan perawatan kebersihan dan penampilan hewan, yaitu Perawatan (mandi, potong kuku, perawatan bulu) dan Penitipan (jasa menitipkan hewan selama jangka waktu tertentu). Program ini memungkinkan pengguna untuk melakukan CRUD (Create, Read, Update, Delete) sederhana terhadap data layanan tersebut.

## Struktur Package

<img width="188" height="379" alt="image" src="https://github.com/user-attachments/assets/72e3ec80-80c0-4313-bb32-9f281f53bfda" />

Program ini terbagi ke dalam beberapa package yang masing-masingnya terdiri dari:

**1. main**\
Berisi class 'KyaPetCareMinpro4.java', yaitu class utama atau entry point program. Class ini hanya memanggil satu method, yaitu 'jalankan()' dari class 'CRUDKyaPetCare.java', sehingga seluruh alur menu dan logika program terpusat di satu tempat.

**2. model**\
Berisi class-class entitas yang merepresentasikan data layanan:
- 'Layanan.java' — abstract class (superclass) yang menyimpan informasi umum layanan, seperti ID layanan, nama layanan, catatan untuk petugas, harga, serta data hewan terkait (nama hewan, nama pemilik, jenis hewan, umur hewan). Class ini menerapkan constructor, getter, setter dengan validasi, encapsulation, serta memiliki satu abstract method yaitu 'getKategori()'.
- 'Evalutable.java' — interface yang mendeklarasikan method 'hitungDiskon()', diimplementasikan oleh 'Perawatan' dan 'Penitipan' dengan aturan diskon berbeda.
- 'Perawatan.java' — subclass dari 'Layanan', implements 'Evalutable', digunakan untuk data layanan perawatan kebersihan dan penampilan hewan (Mandi, Potong Kuku, atau Bulu).
- 'Penitipan.java' — subclass dari 'Layanan', implements 'Evalutable', menambahkan atribut khusus 'lamaPenitipan'', digunakan untuk data layanan jasa menitipkan hewan.

**3. model.dao**\
Berisi class 'LayananDAO.java', yaitu Data Access Object yang bertugas menjembatani objek Java ('Layanan', 'Perawatan', 'Penitipan') dengan tabel 'layanan' di database MySQL. Seluruh proses simpan, baca, ubah, dan hapus data ke database dilakukan melalui class ini menggunakan 'PreparedStatement'.

**4. config**\
Berisi class 'Koneksi.java', yang bertugas membuka dan menyediakan koneksi ke database MySQL ('db_kyapetcare') menggunakan JDBC.

**5. Controller**\
Berisi class yang menangani validasi dan proses CRUD:
- 'CekKyaPetCare.java' — menangani validasi seluruh input dari pengguna, seperti validasi angka, validasi angka harus lebih dari 0, validasi string tidak boleh kosong, validasi pilihan menu, validasi jawaban ya atau tidak, serta validasi ID dengan opsi pembatalan jika input dikosongkan.
- 'CRUDKyaPetCare.java' — menangani proses CRUD pada program sekaligus menjalankan alur menu utama melalui method 'jalankan()'. Class ini memanggil 'LayananDAO' untuk setiap proses tambah, tampil, update, dan hapus data, bukan lagi menyimpan data di ArrayList.

**6. View**\
Berisi class 'Menu.java', yang menampilkan tampilan menu utama program ke layar.

Dengan struktur ini, pembagian MVC yang sudah dibangun sejak Minpro 2 dan Minpro 3 tetap dipertahankan, dengan tambahan dua package baru ('model.dao' dan 'config') khusus untuk menangani koneksi dan akses database, tanpa mencampurkan logika database ke dalam class model ('Layanan', 'Perawatan', 'Penitipan').

## Penerapan Encapsulation dan Inheritance

### Encapsulation
Encapsulation diterapkan pada seluruh class di package model, yaitu class 'Layanan', 'Perawatan', 'Penitipan'.

<img width="181" height="108" alt="image" src="https://github.com/user-attachments/assets/0ea0dd63-bd3b-4fb5-9776-e819a104db43" />

Seperti yang dapat dilihat pada gambar di atas, seluruh atribut yang berada di dalam class 'Layanan', yang nantinya akan diturunkan ke subclass, dibuat menjadi protected. Sehingga data tidak dapat diakses langsung dari luar class. Untuk atribut id dibuat menjadi private final, sehingga hanya bisa diisi sekali lewat constructor dan tidak memiliki setter.

<img width="206" height="44" alt="image" src="https://github.com/user-attachments/assets/0f45fb62-6322-431f-aaf4-bc0f60e7567d" />

<img width="479" height="80" alt="image" src="https://github.com/user-attachments/assets/638348b5-df3c-45a6-9d90-27505551a8a8" />

Setiap atribut yang boleh diubah memiliki getter dan setter, sehingga perubahan data hanya bisa dilakukan lewat method yang sudah disediakan. Hal ini berlaku untuk semua atribut kecuali atribut id. Setiap setter juga sudah dilengkapi validasi, sehingga jika ada nilai yang tidak sesuai (misalnya string kosong atau angka negatif/nol), setter akan menolak nilai tersebut dengan melempar 'IllegalArgumentException'.

### Inheritance
Inheritance diterapkan pada class yang berada di package model.

<img width="207" height="124" alt="image" src="https://github.com/user-attachments/assets/c03d94bd-2fd4-411d-a7b5-1276674cc91c" />

Seperti yang dapat dilihat pada gambar di atas, class 'Layanan' merupakan superclass yang menyimpan atribut umum ('idLayanan', 'namaLayanan', 'catatan', 'harga') beserta data hewan terkait ('namaHewan', 'namaPemilik', 'jenisHewan', 'umurHewan').

<img width="679" height="68" alt="image" src="https://github.com/user-attachments/assets/67fc6356-9bf6-4864-817e-2cfa3d522894" />

<img width="676" height="95" alt="image" src="https://github.com/user-attachments/assets/c7d9d34c-f941-432c-b6d2-15ca06547692" />

Kedua gambar di atas menunjukkan bahwa class 'Perawatan' dan 'Penitipan' merupakan subclass yang menggunakan "extends Layanan", sehingga mewarisi seluruh atribut dan method dari 'Layanan', ditambah atribut khusus milik 'Penitipan', yaitu 'lamaPenitipan'. Kedua subclass tersebut memanggil constructor superclass menggunakan super(...) untuk mengisi atribut umum sebelum mengisi atribut khususnya sendiri.

## Penerapan Polymorphism dan Abstraction

Berikut ini ialah penerapan kedua jenis polymorphism dan abstraction yang saya gunakan:

### Override

[GAMBAR: potongan kode tampilkanInfo() di Layanan.java]

[GAMBAR: potongan kode tampilkanInfo() di Perawatan.java]

[GAMBAR: potongan kode tampilkanInfo() di Penitipan.java]

Salah satu penerapan Override adalah method tampilkanInfo() yang ada di superclass Layanan di-override oleh subclass Perawatan dan Penitipan. Masing-masing subclass memanggil super.tampilkanInfo() terlebih dahulu untuk menampilkan info umum, lalu menambahkan baris info khusus miliknya sendiri (diskon untuk Perawatan, lama penitipan dan diskon untuk Penitipan). Dengan begitu, meskipun dipanggil dengan cara yang sama, hasil tampilannya berbeda tergantung jenis objeknya.

### Overload

[GAMBAR: potongan kode cetakStatus() dua versi]

Salah satu penerapan Overload ialah method cetakStatus() pada class Layanan yang memiliki dua versi, yaitu tanpa parameter (menampilkan status "Layanan Aktif") dan dengan parameter String (menampilkan status "Layanan Aktif" ditambah catatan tertentu, misalnya "Dapat Diskon").

### Abstraction

[GAMBAR: potongan kode "public abstract class Layanan"]

Class Layanan dideklarasikan sebagai abstract class, sehingga tidak dapat dibuat objeknya secara langsung (tidak bisa menulis new Layanan(...)). Hal ini memastikan setiap objek yang dibuat harus berupa subclass yang jelas jenisnya, yaitu Perawatan atau Penitipan.

[GAMBAR: potongan kode "public abstract String getKategori();"]

[GAMBAR: potongan kode implementasi getKategori() di Perawatan]

[GAMBAR: potongan kode implementasi getKategori() di Penitipan]

Class Layanan memiliki satu abstract method, yaitu getKategori(), yang tidak memiliki isi di superclass dan wajib diisi oleh setiap subclass. Class Perawatan mengembalikan nilai "Perawatan" dan class Penitipan mengembalikan nilai "Penitipan". Method ini kemudian dipanggil di dalam tampilkanInfo() milik Layanan, sehingga superclass tidak perlu tahu detail bagaimana kategori ditentukan, cukup tahu bahwa method tersebut pasti tersedia di setiap subclass-nya.

## Penerapan JDBC

Pada Minpro 4 ini, program yang sebelumnya menyimpan data di ArrayList (sehingga data hilang setiap program ditutup) diintegrasikan dengan database MySQL bernama `db_kyapetcare`, menggunakan JDBC (Java Database Connectivity), PreparedStatement, dan pola DAO (Data Access Object).

### Koneksi Database

[GAMBAR: potongan kode Koneksi.java]

Class Koneksi.java pada package config bertugas membuka koneksi ke database menggunakan DriverManager.getConnection(), dengan URL, username, dan password yang sudah ditentukan. Jika driver MySQL tidak ditemukan atau koneksi gagal, class ini akan melempar SQLException yang kemudian ditangani oleh Controller.

### Data Access Object (DAO)

[GAMBAR: potongan kode LayananDAO.java - method tambah]

Class LayananDAO.java pada package model.dao menjadi perantara antara objek Layanan (beserta subclass-nya) dengan tabel layanan di database. Seluruh perintah SQL (INSERT, SELECT, UPDATE, DELETE) di class ini menggunakan PreparedStatement, bukan Statement biasa, agar nilai yang dimasukkan pengguna diikat sebagai parameter (bukan digabung langsung ke teks query), sehingga lebih aman dari celah SQL Injection.

Setiap method pada LayananDAO dirancang untuk satu tugas spesifik:
- tambah(Layanan layanan) — menjalankan query INSERT, mengembalikan ID baru yang dibuat otomatis oleh database (AUTO_INCREMENT).
- tampilkanSemua() — menjalankan query SELECT seluruh data, lalu mengembalikannya sebagai ArrayList of Layanan.
- cariById(int id) — menjalankan query SELECT berdasarkan id_layanan, digunakan saat update dan hapus data.
- update(Layanan layanan) — menjalankan query UPDATE berdasarkan id_layanan.
- hapus(int id) — menjalankan query DELETE berdasarkan id_layanan.

[GAMBAR: potongan kode buatObjek() yang mengubah ResultSet menjadi objek Perawatan/Penitipan]

Setiap kali DAO membaca baris dari database (lewat ResultSet), data tersebut diubah kembali menjadi objek Perawatan atau Penitipan sesuai kolom kategori pada tabel, sehingga seluruh struktur OOP (abstract class, inheritance, polymorphism, interface Evalutable) yang sudah dibangun tetap digunakan sepenuhnya, walaupun sumber datanya kini berasal dari database, bukan lagi ArrayList di memori.

### Integrasi ke Controller

[GAMBAR: potongan kode CRUDKyaPetCare.java yang memanggil LayananDAO]

Pada class CRUDKyaPetCare.java, setiap method CRUD (tambahLayanan, tampilkanLayanan, updateLayanan, hapusLayanan) memanggil method yang sesuai di LayananDAO, kemudian hasil atau kemungkinan error-nya ditangani menggunakan blok try-catch untuk SQLException. Jika terjadi kegagalan koneksi atau query, program tidak akan menampilkan notifikasi keberhasilan, melainkan pesan error yang sesuai, sehingga tidak ada data yang dianggap berhasil tersimpan padahal sebenarnya gagal.

## Alur Program
Alur program dimulai ketika program dijalankan melalui class `KyaPetCareMinpro4.java`, yang langsung memanggil method `jalankan()` pada class `CRUDKyaPetCare.java`. Program akan menampilkan menu utama yang berisi beberapa pilihan, yaitu Tambah Data Layanan, Tampilkan Data Layanan, Update Data Layanan, Hapus Data Layanan, dan Keluar.

Pengguna memilih menu dengan memasukkan angka sesuai pilihan. Saat menambah data, pengguna cukup memilih jenis layanannya (Perawatan atau Penitipan), lalu mengisi data umum (catatan untuk petugas, harga, data hewan) serta data khusus sesuai jenisnya. Objek yang dibentuk dari input tersebut kemudian dikirim ke `LayananDAO`, yang menyimpannya ke tabel `layanan` di MySQL menggunakan `PreparedStatement`. ID layanan dibuat otomatis oleh database (AUTO_INCREMENT), sehingga pengguna tidak perlu menginputnya sendiri.

Saat menampilkan, mengupdate, atau menghapus data, `CRUDKyaPetCare` memanggil `LayananDAO` untuk mengambil data langsung dari database, bukan dari ArrayList di memori. Hal ini memastikan data yang ditampilkan selalu merupakan data terbaru yang tersimpan di MySQL, dan tetap tersedia walaupun program ditutup dan dijalankan kembali. Saat update atau hapus data, jika pengguna mengosongkan input ID, sistem akan otomatis membatalkan proses dan kembali ke menu utama tanpa mengubah data apapun di database.

Berikut adalah dokumentasi tampilan output sistem saat program dijalankan.

**1. Menu Utama**

[GAMBAR: tampilan menu utama]

Gambar di atas merupakan tampilan awal atau yang biasa disebut menu utama dari program yang telah saya rancang dan jalankan.

**2. Tambah Data Layanan**

[GAMBAR: submenu tambah data layanan]

Setelah memilih menu nomor pertama, maka kita akan dialihkan ke pilihan menu untuk menambahkan jenis layanan, yaitu Perawatan atau Penitipan.

* Tambah Data Layanan Perawatan

[GAMBAR: proses input tambah data perawatan]

Jika memilih opsi nomor 1 (Perawatan), kita diminta mengisi formulir data umum, lalu memilih jenis perawatan (Mandi, Potong Kuku, atau Bulu). Setelah diisi, muncul notifikasi "Horee! data sudah berhasil ditambahkan dengan ID [nomor]." yang menandakan data telah tersimpan ke database, lengkap dengan ID yang dibuat otomatis oleh MySQL.

* Tambah Data Layanan Penitipan

[GAMBAR: proses input tambah data penitipan]

Jika memilih opsi nomor 2 (Penitipan), kita diminta mengisi formulir data umum, lalu memasukkan lama penitipan dalam hitungan hari. Setelah diisi, muncul notifikasi serupa yang menandakan data telah tersimpan ke database.

**3. Tampilkan Data Layanan**

[GAMBAR: submenu tampilkan data layanan]

[GAMBAR: daftar seluruh data layanan dari database]

Jika memilih menu nomor 2 pada menu utama, sistem akan mengambil seluruh data layanan langsung dari tabel `layanan` di database MySQL dan menampilkannya, lengkap dengan kategori, informasi hewan, serta status diskon.

**4. Update Data Layanan**

[GAMBAR: proses update data]

Pada menu Update Data Layanan, kita diminta memasukkan ID Layanan yang ingin diubah. Sistem akan mencari data tersebut langsung ke database. Jika ditemukan, kita mengisi data baru, lalu sistem akan menjalankan proses UPDATE ke database dan menampilkan notifikasi "Horee! data sudah berhasil diupdate." beserta data terbaru.

**5. Hapus Data Layanan**

[GAMBAR: konfirmasi hapus - ya]

Pada menu Hapus Data Layanan, kita diminta memasukkan ID Layanan yang ingin dihapus. Setelah detail data ditampilkan, sistem meminta konfirmasi ya/tidak. Jika memilih "ya", sistem menjalankan proses DELETE ke database dan menampilkan "Data layanan berhasil dihapus!".

[GAMBAR: konfirmasi hapus - tidak]

Jika memilih "tidak", data tidak jadi dihapus dan sistem menampilkan "Penghapusan data dibatalkan."

**6. Keluar**

[GAMBAR: tampilan keluar program]

Jika memilih menu terakhir, maka kita akan dikeluarkan dari sistem.

**7. Input Validasi**

* Input Harus Berupa Angka

[GAMBAR: error input bukan angka]

Jika pengguna memasukkan input selain angka pada field yang membutuhkan angka, sistem akan menampilkan pesan error sesuai nama field tersebut, misalnya "Harga harus berupa angka!".

* Input Harus Lebih dari 0

[GAMBAR: error input angka negatif atau nol]

Jika pengguna memasukkan angka 0 atau negatif pada field yang membutuhkan angka positif, sistem akan menampilkan pesan error sesuai nama field, misalnya "Umur Hewan harus lebih dari 0!".

* Input Tidak Boleh Kosong

[GAMBAR: error input kosong]

Jika pengguna mengosongkan inputan data string, sistem akan menampilkan "Data tidak boleh kosong!".

* Input Min & Max Pilihan

[GAMBAR: error pilihan di luar jangkauan]

Jika pengguna memasukkan angka di luar jangkauan pilihan menu, sistem akan menampilkan "Pilihan hanya [min] sampai [max]!".

* Input Harus Berupa Ya/Tidak

[GAMBAR: error jawaban ya/tidak]

Jika pengguna memasukkan jawaban selain "ya" atau "tidak" pada konfirmasi penghapusan, sistem akan menampilkan "Jawaban harus 'ya' atau 'tidak'!".

* ID Kosong saat Update/Hapus

[GAMBAR: proses pembatalan karena ID dikosongkan]

Jika pengguna mengosongkan input ID pada menu Update atau Hapus, sistem akan langsung membatalkan proses dan menampilkan "Dibatalkan, kembali ke menu." tanpa mengubah data apapun di database.

* Koneksi atau Query Database Gagal

[GAMBAR: pesan error saat koneksi/query gagal]

Jika koneksi ke database gagal (misalnya MySQL belum dinyalakan, database tidak ditemukan, atau username/password salah) maupun ada kesalahan saat menjalankan query, sistem tidak langsung crash, melainkan menampilkan pesan error yang jelas, misalnya "Gagal mengambil data dari database: [pesan error]", dan tidak menampilkan notifikasi keberhasilan apapun.
