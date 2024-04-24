# LP7DPBO2024C2

# Janji
Saya Ratu Syahirah Khairunnisa [2200978] 
mengerjakan Latihan Praktikum 7
dalam mata kuliah DPBO
untuk keberkahanNya maka saya tidak melakukan kecurangan 
seperti yang telah dispesifikasikan. 
Aamiin

# Flappy Bird Game
# Desain Program

## 1. Penghentian Permainan saat Player Menabrak/Menyentuh Pipa atau Terjatuh ke Batas Bawah JFrame (Game Over)
- Fungsionalitas ini telah diimplementasikan dengan mengintegrasikan logika deteksi tabrakan ke dalam metode `move()`. Saat pemain menabrak pipa atau jatuh ke batas bawah frame, metode `gameOver()` dipanggil. Ini menghentikan loop permainan dan menampilkan pesan "Game Over".

## 2. Setelah Game Over, Fungsionalitas untuk Merestart Permainan dengan Menekan Tombol “R” pada Keyboard Ditambahkan
- Saat permainan berakhir, pengguna dapat memilih untuk merestart permainan dengan menekan tombol "R". Fungsionalitas ini telah ditambahkan dalam metode `gameOver()`. Jika pemain memilih untuk merestart permainan, maka game akan diinisialisasi kembali.

## 3. Pembuatan Sebuah JLabel untuk Menampilkan Skor
- Sebuah JLabel bernama `scoreLabel` telah dibuat dalam konstruktor. Ini berfungsi sebagai wadah untuk menampilkan skor pemain. Skor diperbarui setiap kali pemain melewati sepasang pipa, dan nilai skor tersebut ditampilkan pada `scoreLabel`.

## 4. Peningkatan Skor (+1) Setiap Kali Player Melewati Pipa
- Logika peningkatan skor telah diimplementasikan dalam metode `move()`. Setiap kali pemain berhasil melewati sepasang pipa, skornya akan bertambah 1. Peningkatan skor tersebut tercermin pada `scoreLabel`.
  
---

Pada program ini terdapat 1 main class dan 3 class lainnya, diantaranya:

1. **Main Class (`App.java`)**: Kelas utama yang digunakan untuk menjalankan aplikasi. Ini membuat sebuah frame untuk permainan dan menambahkan panel `FlappyBird` ke dalamnya.

2. **Game Panel Class (`FlappyBird.java`)**: Kelas yang menangani semua logika permainan dan tampilan. Beberapa poin pentingnya adalah:
   - Inisialisasi gambar latar belakang, burung, dan pipa.
   - Menjalankan loop permainan menggunakan `Timer`.
   - Menangani logika pergerakan burung dan pipa.
   - Mendeteksi tabrakan antara burung dan pipa.
   - Menampilkan skor dan mengatur ulang permainan saat game over.
   - Mendeteksi input dari pemain untuk menggerakkan burung dan mereset permainan.

3. **Player Class (`Player.java`)**: Kelas yang merepresentasikan pemain (burung) dalam permainan. Ini menyimpan posisi, dimensi, gambar, dan kecepatan vertikal burung. Juga memiliki metode untuk mendeteksi tabrakan dengan pipa.

4. **Pipe Class (`Pipe.java`)**: Kelas yang merepresentasikan pipa dalam permainan. Ini menyimpan posisi, dimensi, gambar, dan kecepatan horizontal pipa. Juga memiliki atribut untuk melacak apakah pipa sudah dilewati oleh pemain.

# Penjelasan Alur Program
1. Saat program dijalankan, frame utama game akan ditampilkan dan permainan akan langsung mulai dengan tampilan score di atas (di set 0)
2. Untuk memainkannyadengan cara tekan tombol spasi untuk menggerakkan burung naik.
3. Pemain perlu menghindari tabrakan dengan pipa ataupun jatuh ke tanah, karena jika tabrakan atau jatuh maka permainan akan berakhir.
4. Setiap kali burung melewati sepasang pipa, skor akan bertambah 1.
5. Pemain dapat merestart permainan dengan menekan tombol "R" saat bermain dan klik tombol yes pada saat game over.

# Dokumentasi
![Game over dan restart](https://github.com/queenxhr/LP7DPBO2024C2/assets/135084798/46a466c0-7106-425a-8d94-5884adee92a2)
![Tampilan score](https://github.com/queenxhr/LP7DPBO2024C2/assets/135084798/8e5db97d-1360-4b20-b721-420bb70a3fb8)

