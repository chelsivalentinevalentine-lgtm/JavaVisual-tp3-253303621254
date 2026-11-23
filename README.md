# Character Manager — TP10
## Sprint #4: Theming & Swing Component Properties

**Mata Kuliah:** Java Visual (SIH-6406)  
**Nama:** Chelsi Valentine Harefa  
**NIM:** 253303621254  
**Kelas:** 2 Pagi B  
**Versi Aplikasi:** Character Manager v0.4

---

## A. Deskripsi Proyek

TP10 merupakan pengembangan aplikasi Character Manager menggunakan Java Swing. Pada sprint ini, aplikasi dikembangkan dengan menerapkan pengaturan tampilan menggunakan `AppTheme`, seperti warna, font, dan border. Tampilan karakter dibuat lebih menarik dengan warna berbeda berdasarkan jenis karakter (role).

## B. Implementasi AppTheme

File `AppTheme.java` dibuat di package `com.unpri.charactermanager.view`.

Pengaturan tampilan dipusatkan di dalam kelas `AppTheme`, meliputi:

- Warna latar utama dan warna teks.
- Warna khusus untuk Warrior, Mage, Archer, dan Healer.
- Font judul, isi, dan monospace.
- Border untuk kartu karakter.
- Pemetaan warna menggunakan `colorFor(CharacterType)`.
- Deskripsi role menggunakan `describe(CharacterType)`.

Warna disimpan di `AppTheme`, bukan di `Character`, karena warna merupakan bagian dari tampilan aplikasi, bukan data inti karakter. Dengan pemisahan ini, model tetap independen dari Swing dan AWT sehingga dapat digunakan kembali pada tampilan lain. Pengaturan tema juga lebih mudah diperbarui tanpa mengubah logika model. Hal ini membantu menjaga prinsip pemisahan tanggung jawab dalam pola MVC.

## C. Screenshot Aplikasi

### Sebelum TP10 — Character Manager v0.3

![Screenshot sebelum TP10](screenshots/tp10-sebelum-v03.png)

### Sesudah TP10 — Character Manager v0.4

![Screenshot sesudah TP10](screenshots/tp10-sesudah-v04.png)

## D. Properti Tambahan

Dua properti tambahan yang diterapkan pada komponen JLabel:

1. **ToolTipText (`setToolTipText`)**  
   Menampilkan informasi tambahan berupa deskripsi role, HP, dan ATK ketika pengguna mengarahkan kursor ke kartu karakter. Properti ini membantu pengguna memperoleh informasi lebih lengkap tanpa membuat tampilan utama terlalu ramai.

2. **Horizontal Alignment (`setHorizontalAlignment`)**  
   Mengatur perataan teks karakter ke sisi kiri agar informasi setiap karakter lebih mudah dibaca dan tampil konsisten.

Selain itu, aplikasi juga menerapkan cursor `HAND_CURSOR` dan penekanan visual untuk karakter dengan level minimal 5 menggunakan warna emas dan font tebal.

## E. Mini Challenge

Karakter dengan level minimal 5 akan ditampilkan menggunakan teks berwarna emas dan font tebal. Batas level ditentukan melalui konstanta `HIGH_LEVEL_THRESHOLD` di `AppTheme`, sehingga nilai batas dapat diubah dari satu tempat.

## F. Analisis Properti & MVC

Laporan analisis lengkap minimal 500 kata tersedia melalui Google Docs:

**Link Google Docs:** 
  https://docs.google.com/document/d/1yy_5cEwf07As2V81NW2msPiOi7dqrEgTQLt_NBXsT8w/edit?usp=sharing

## G. Struktur File Utama

```text
src/
├── Main.java
└── com/
    └── unpri/
        └── charactermanager/
            ├── model/
            │   ├── Character.java
            │   ├── CharacterManager.java
            │   └── CharacterType.java
            └── view/
                ├── AppTheme.java
                └── MainWindow.java
```

## H. Kesimpulan

Pada TP10, aplikasi Character Manager berhasil dikembangkan menjadi versi 0.4 dengan penerapan tema terpusat, warna berdasarkan role, pengaturan font, border, tooltip, dan properti alignment. Pengembangan ini mempertahankan pemisahan antara Model dan View sehingga struktur aplikasi menjadi lebih terorganisasi dan mudah dikembangkan.