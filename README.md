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

## Encapsulation
Encapsulation diterapkan pada seluruh class di package model, yaitu class 'Layanan', 'Perawatan', 'Penitipan'.

<img width="181" height="108" alt="image" src="https://github.com/user-attachments/assets/0ea0dd63-bd3b-4fb5-9776-e819a104db43" />

Seperti yang dapat dilihat pada gambar di atas, seluruh atribut yang berada di dalam class 'Layanan', yang nantinya akan diturunkan ke subclass, dibuat menjadi protected. Sehingga data tidak dapat diakses langsung dari luar class. Untuk atribut id dibuat menjadi private final, sehingga hanya bisa diisi sekali lewat constructor dan tidak memiliki setter.

<img width="206" height="44" alt="image" src="https://github.com/user-attachments/assets/0f45fb62-6322-431f-aaf4-bc0f60e7567d" />
<br><br>
<img width="479" height="80" alt="image" src="https://github.com/user-attachments/assets/638348b5-df3c-45a6-9d90-27505551a8a8" />

Setiap atribut yang boleh diubah memiliki getter dan setter, sehingga perubahan data hanya bisa dilakukan lewat method yang sudah disediakan. Hal ini berlaku untuk semua atribut kecuali atribut id. Setiap setter juga sudah dilengkapi validasi, sehingga jika ada nilai yang tidak sesuai (misalnya string kosong atau angka negatif/nol), setter akan menolak nilai tersebut dengan melempar 'IllegalArgumentException'.

## Inheritance
Inheritance diterapkan pada class yang berada di package model.

<img width="207" height="124" alt="image" src="https://github.com/user-attachments/assets/c03d94bd-2fd4-411d-a7b5-1276674cc91c" />

Seperti yang dapat dilihat pada gambar di atas, class 'Layanan' merupakan superclass yang menyimpan atribut umum ('idLayanan', 'namaLayanan', 'catatan', 'harga') beserta data hewan terkait ('namaHewan', 'namaPemilik', 'jenisHewan', 'umurHewan').

<img width="679" height="68" alt="image" src="https://github.com/user-attachments/assets/67fc6356-9bf6-4864-817e-2cfa3d522894" />
<br><br>
<img width="676" height="95" alt="image" src="https://github.com/user-attachments/assets/c7d9d34c-f941-432c-b6d2-15ca06547692" />

Kedua gambar di atas menunjukkan bahwa class 'Perawatan' dan 'Penitipan' merupakan subclass yang menggunakan "extends Layanan", sehingga mewarisi seluruh atribut dan method dari 'Layanan', ditambah atribut khusus milik 'Penitipan', yaitu 'lamaPenitipan'. Kedua subclass tersebut memanggil constructor superclass menggunakan super(...) untuk mengisi atribut umum sebelum mengisi atribut khususnya sendiri.

## Polymorphism 

Berikut ini ialah penerapan kedua jenis polymorphism yang saya gunakan:

### Override

<img width="399" height="146" alt="image" src="https://github.com/user-attachments/assets/69552095-e6de-47d0-a814-77080f4cb310" />
<br><br>
<img width="382" height="73" alt="image" src="https://github.com/user-attachments/assets/aafaf86e-0205-485b-ba03-a1a7e922233e" />
<br><br>
<img width="419" height="83" alt="image" src="https://github.com/user-attachments/assets/0de04121-1879-4910-892a-d02e35714e42" />

Salah satu penerapan Override adalah method 'tampilkanInfo()' yang ada di superclass Layanan di-override oleh subclass 'Perawatan' dan 'Penitipan'. Masing-masing subclass memanggil 'super.tampilkanInfo()' terlebih dahulu untuk menampilkan info umum, lalu menambahkan baris info khusus miliknya sendiri (diskon untuk 'Perawatan', lama penitipan dan diskon untuk 'Penitipan'). Dengan begitu, meskipun dipanggil dengan cara yang sama, hasil tampilannya berbeda tergantung jenis objeknya.

### Overload

<img width="486" height="95" alt="image" src="https://github.com/user-attachments/assets/2eac2472-2abf-422b-a5e1-4ee0767248dd" />

Salah satu penerapan Overload ialah method 'cetakStatus()' pada class Layanan yang memiliki dua versi, yaitu tanpa parameter (menampilkan status "Layanan Aktif") dan dengan parameter String (menampilkan status "Layanan Aktif" ditambah catatan tertentu, misalnya "Dapat Diskon").

## Abstraction

Berikut ini ialah penerapan abstraction yang saya gunakan:

<img width="197" height="13" alt="image" src="https://github.com/user-attachments/assets/e53e23d5-57be-4dc3-aa56-bd2517d1844f" />

Class 'Layanan' dideklarasikan sebagai abstract class, sehingga tidak dapat dibuat objeknya secara langsung (tidak bisa menulis new Layanan(...)). Hal ini memastikan setiap objek yang dibuat harus berupa subclass yang jelas jenisnya, yaitu 'Perawatan' atau 'Penitipan'.

<img width="230" height="18" alt="image" src="https://github.com/user-attachments/assets/ef02cd13-6039-4186-a0e8-28f011b2a5ea" />
<br><br>
<img width="185" height="54" alt="image" src="https://github.com/user-attachments/assets/a30f76a8-998f-4e3b-9fec-eb69c5248a41" />
<br><br>
<img width="180" height="59" alt="image" src="https://github.com/user-attachments/assets/ef509c20-d0b6-4883-a9a2-8c98fcbbd957" />

Class 'Layanan' memiliki satu abstract method, yaitu 'getKategori()', yang tidak memiliki isi di superclass dan wajib diisi oleh setiap subclass. Class 'Perawatan' mengembalikan nilai "Perawatan" dan class 'Penitipan' mengembalikan nilai "Penitipan". Method ini kemudian dipanggil di dalam 'tampilkanInfo()' milik 'Layanan', sehingga superclass tidak perlu tahu detail bagaimana kategori ditentukan, cukup tahu bahwa method tersebut pasti tersedia di setiap subclass-nya.

## Penerapan JDBC

Pada Minpro 4 ini, program yang sebelumnya menyimpan data di ArrayList (sehingga data hilang setiap program ditutup) diintegrasikan dengan database MySQL bernama 'db_kyapetcare', menggunakan JDBC (Java Database Connectivity), PreparedStatement, dan pola DAO (Data Access Object).

### Koneksi Database

<img width="497" height="299" alt="image" src="https://github.com/user-attachments/assets/c48b7466-af4c-4320-b645-90fe57fcfd1e" />

Class 'Koneksi.java' pada package config bertugas membuka koneksi ke database menggunakan 'DriverManager.getConnection()', dengan URL, username, dan password yang sudah ditentukan. Jika driver MySQL tidak ditemukan atau koneksi gagal, class ini akan melempar SQLException yang kemudian ditangani oleh Controller.

### Data Access Object (DAO)

Class 'LayananDAO.java' pada package 'model.dao' menjadi perantara antara objek Layanan (beserta subclass-nya) dengan tabel layanan di database. Seluruh perintah SQL (INSERT, SELECT, UPDATE, DELETE) di class ini menggunakan PreparedStatement, bukan Statement biasa, agar nilai yang dimasukkan pengguna diikat sebagai parameter (bukan digabung langsung ke teks query), sehingga lebih aman dari celah SQL Injection.

Setiap method pada LayananDAO dirancang untuk satu tugas spesifik:
- 'tambah(Layanan layanan)' — menjalankan query INSERT, mengembalikan ID baru yang dibuat otomatis oleh database (AUTO_INCREMENT).
- 'tampilkanSemua()' — menjalankan query SELECT seluruh data, lalu mengembalikannya sebagai ArrayList of Layanan.
- 'cariById(int id)' — menjalankan query SELECT berdasarkan id_layanan, digunakan saat update dan hapus data.
- 'update(Layanan layanan)' — menjalankan query UPDATE berdasarkan id_layanan.
- 'hapus(int id)' — menjalankan query DELETE berdasarkan id_layanan.

Setiap kali DAO membaca baris dari database (lewat ResultSet), data tersebut diubah kembali menjadi objek Perawatan atau Penitipan sesuai kolom kategori pada tabel, sehingga seluruh struktur OOP (abstract class, inheritance, polymorphism, interface Evalutable) yang sudah dibangun tetap digunakan sepenuhnya, walaupun sumber datanya kini berasal dari database, bukan lagi ArrayList di memori.

### Integrasi ke Controller

<img width="625" height="218" alt="image" src="https://github.com/user-attachments/assets/a4cac5ff-65b2-4021-b282-4e0802243c2c" />
<br><br>
<img width="425" height="97" alt="image" src="https://github.com/user-attachments/assets/bb10ea26-2d93-43dd-88e5-cd7bc8a4fc57" />
<br><br>
<img width="558" height="191" alt="image" src="https://github.com/user-attachments/assets/819c08a9-ef61-4c90-bef1-00ac633595b3" />
<br><br>
<img width="247" height="26" alt="image" src="https://github.com/user-attachments/assets/5632b3e6-df0f-4a27-951e-025afa3e81d7" />
<br><br>
<img width="566" height="341" alt="image" src="https://github.com/user-attachments/assets/c49fe256-5f77-43bd-b94a-dc559568d9b6" />

Pada class 'CRUDKyaPetCare.java', setiap method CRUD ('tambahLayanan', 'tampilkanLayanan', 'updateLayanan', 'hapusLayanan') memanggil method yang sesuai di 'LayananDAO', kemudian hasil atau kemungkinan error-nya ditangani menggunakan blok try-catch untuk SQLException. Jika terjadi kegagalan koneksi atau query, program tidak akan menampilkan notifikasi keberhasilan, melainkan pesan error yang sesuai, sehingga tidak ada data yang dianggap berhasil tersimpan padahal sebenarnya gagal.

## Alur Program
Alur program dimulai ketika program dijalankan melalui class 'KyaPetCareMinpro4.java', yang langsung memanggil method 'jalankan()' pada class 'CRUDKyaPetCare.java'. Program akan menampilkan menu utama yang berisi beberapa pilihan, yaitu Tambah Data Layanan, Tampilkan Data Layanan, Update Data Layanan, Hapus Data Layanan, dan Keluar.

Pengguna memilih menu dengan memasukkan angka sesuai pilihan. Saat menambah data, pengguna cukup memilih jenis layanannya (Perawatan atau Penitipan), lalu mengisi data umum (catatan untuk petugas, harga, data hewan) serta data khusus sesuai jenisnya. Objek yang dibentuk dari input tersebut kemudian dikirim ke 'LayananDAO', yang menyimpannya ke tabel 'layanan' di MySQL menggunakan 'PreparedStatement'. ID layanan dibuat otomatis oleh database (AUTO_INCREMENT), sehingga pengguna tidak perlu menginputnya sendiri.

Saat menampilkan, mengupdate, atau menghapus data, 'CRUDKyaPetCare' memanggil 'LayananDAO' untuk mengambil data langsung dari database, bukan dari ArrayList di memori. Hal ini memastikan data yang ditampilkan selalu merupakan data terbaru yang tersimpan di MySQL, dan tetap tersedia walaupun program ditutup dan dijalankan kembali. Saat update atau hapus data, jika pengguna mengosongkan input ID, sistem akan otomatis membatalkan proses dan kembali ke menu utama tanpa mengubah data apapun di database.

Berikut adalah dokumentasi tampilan output sistem saat program dijalankan.

**1. Menu Utama**

<img width="171" height="129" alt="image" src="https://github.com/user-attachments/assets/c0cbee2c-1eb9-47a9-9c6d-c7c0872be74d" />

Gambar di atas merupakan tampilan awal atau yang biasa disebut menu utama dari program yang telah saya rancang dan jalankan.

**2. Tambah Data Layanan**

<img width="151" height="91" alt="image" src="https://github.com/user-attachments/assets/ce33d674-9c6d-450b-a77e-5ced1ec52976" />

Setelah memilih menu nomor pertama, maka kita akan dialihkan ke pilihan menu untuk menambahkan jenis layanan, yaitu Perawatan atau Penitipan.

* Tambah Data Layanan Perawatan

<img width="286" height="295" alt="image" src="https://github.com/user-attachments/assets/3bbdc178-27e5-430b-98a0-89e9f064346e" />

Jika memilih opsi nomor 1 (Perawatan), kita diminta mengisi formulir data umum, lalu memilih jenis perawatan (Mandi, Potong Kuku, atau Bulu). Setelah diisi, muncul notifikasi "Horee! data sudah berhasil ditambahkan dengan ID 5." yang menandakan data telah tersimpan ke database, lengkap dengan ID yang dibuat otomatis oleh MySQL. Disini ia langsung otomatis ke ID nomor 5 dikarenakan, sebelumnya saya sudah ada mencoba untuk menambahkan data yang kemudian saya hapus dari databasenya, namun dari databasenya menganggap bahwa ID nomor 3 dan 4 itu sudah terisi. Oleh karena itu, ia mendapatkan ID nomor 5.

* Tambah Data Layanan Penitipan

<img width="285" height="194" alt="image" src="https://github.com/user-attachments/assets/c1960364-ccfc-4d8c-b84b-0124332ea863" />

Jika memilih opsi nomor 2 (Penitipan), kita diminta mengisi formulir data umum, lalu memasukkan lama penitipan dalam hitungan hari. Setelah diisi, muncul notifikasi serupa yang menandakan data telah tersimpan ke database. Setelah diisi, muncul notifikasi "Horee! data sudah berhasil ditambahkan dengan ID 6." yang menandakan data telah tersimpan ke database, lengkap dengan ID yang dibuat otomatis oleh MySQL.

**3. Tampilkan Data Layanan**

<img width="390" height="367" alt="image" src="https://github.com/user-attachments/assets/74e4f6e8-2a44-450e-947e-03f71cdafcf3" />

<img width="246" height="333" alt="image" src="https://github.com/user-attachments/assets/35b8d535-2532-496d-9b02-c1a62c5264dc" />

Jika memilih menu nomor 2 pada menu utama, sistem akan mengambil seluruh data layanan langsung dari tabel 'layanan' di database MySQL dan menampilkannya, lengkap dengan kategori, informasi hewan, serta status diskon.

**4. Update Data Layanan**

<img width="334" height="383" alt="image" src="https://github.com/user-attachments/assets/dff488cc-9d27-4d6a-a632-2d223ac5ca75" />

Pada menu Update Data Layanan, kita diminta memasukkan ID Layanan yang ingin diubah. Sistem akan mencari data tersebut langsung ke database. Jika ditemukan, kita mengisi data baru, lalu sistem akan menjalankan proses UPDATE ke database dan menampilkan notifikasi "Horee! data sudah berhasil diupdate." beserta data terbaru.

**5. Hapus Data Layanan**

<img width="269" height="241" alt="image" src="https://github.com/user-attachments/assets/b0fa49c7-ba3a-43a6-9c39-18f555af5a4d" />

Pada menu Hapus Data Layanan, kita diminta memasukkan ID Layanan yang ingin dihapus. Setelah detail data ditampilkan, sistem meminta konfirmasi ya/tidak. Jika memilih "ya", sistem menjalankan proses DELETE ke database dan menampilkan "Data layanan berhasil dihapus!".

<img width="278" height="232" alt="image" src="https://github.com/user-attachments/assets/2431c511-4f57-48e5-973b-7839ffc15763" />

Jika memilih "tidak", data tidak jadi dihapus dan sistem menampilkan "Penghapusan data dibatalkan."

**6. Keluar**

<img width="170" height="155" alt="image" src="https://github.com/user-attachments/assets/b111e1e4-4f4d-4b46-92c8-6ef860b27b55" />

Jika memilih menu terakhir, maka kita akan dikeluarkan dari sistem.

**7. Input Validasi**

* Input Harus Berupa Angka

<img width="218" height="142" alt="Screenshot 2026-09-24 094039" src="https://github.com/user-attachments/assets/50e11c4d-a453-4a81-96a6-9e46bdf090cb" />

Pada gambar di atas, dapat dilihat bahwa jika pengguna memasukkan input selain angka, maka sistem akan menampilkan "ID yang dimasukkan harus berupa angka!" untuk memastikan input yang dimasukkan valid.
  
* Input Harus Lebih dari 0

<img width="231" height="65" alt="Screenshot 2026-09-24 095106" src="https://github.com/user-attachments/assets/8b24c49b-9615-463a-9395-100ff37cfceb" />

Pada gambar di atas, dapat dilihat bahwa jika pengguna memasukkan ID Layanan bernilai 0 atau diluar angka positif, maka sistem akan menampilkan "ID yang dimasukkan harus lebih dari 0!" untuk memastikan input yang dimasukkan valid.

* Input Tidak Boleh Kosong

<img width="164" height="130" alt="Screenshot 2026-09-24 095558" src="https://github.com/user-attachments/assets/04f3387f-f0c6-434c-b317-9f17218b0257" />

Pada gambar di atas, dapat dilihat bahwa jika pengguna mengosongkan inputan data, maka sistem akan menampilkan "Data tidak boleh kosong!" untuk memastikan input yang dimasukkan valid.

* Input Min & Max Pilihan

<img width="171" height="144" alt="Screenshot 2026-09-24 094917" src="https://github.com/user-attachments/assets/67b2cc55-2334-46a0-8179-896f444a653a" />

Pada gambar di atas, dapat dilihat bahwa jika pengguna memasukkan angka di luar jangkauan pilihan menu, maka sistem akan menampilkan "Pilihan hanya 1 sampai 5!" untuk memastikan input yang dimasukkan valid.

* Input Harus Berupa Ya/Tidak

<img width="281" height="233" alt="Screenshot 2026-09-24 100002" src="https://github.com/user-attachments/assets/dc186648-3a42-4c76-9e6d-2da8083cd75f" />

Pada gambar di atas, dapat dilihat bahwa jika pengguna memasukkan jawaban selain "ya" atau "tidak" pada konfirmasi penghapusan, maka sistem akan menampilkan "Jawaban harus 'ya' atau 'tidak'!" untuk memastikan input yang dimasukkan valid.

* ID Kosong saat Update/Hapus

<img width="251" height="68" alt="image" src="https://github.com/user-attachments/assets/60071f03-7055-4366-bfbf-1948751e7ae0" />
<br><br>
<img width="251" height="68" alt="image" src="https://github.com/user-attachments/assets/5b1a7a40-1a33-4cfd-9f0c-2dee25db036d" />

Jika pengguna mengosongkan input ID pada menu Update atau Hapus, sistem akan langsung membatalkan proses dan menampilkan "Dibatalkan, kembali ke menu." tanpa mengubah data apapun di database.

* Koneksi atau Query Database Gagal

<img width="230" height="196" alt="image" src="https://github.com/user-attachments/assets/c9b64323-6a2c-4f09-885e-a86caeb607ab" />

Jika koneksi ke database gagal (misalnya MySQL belum dinyalakan, database tidak ditemukan, atau username/password salah) maupun ada kesalahan saat menjalankan query, sistem tidak langsung crash, melainkan menampilkan pesan error yang jelas dan tidak menampilkan notifikasi keberhasilan apapun.
