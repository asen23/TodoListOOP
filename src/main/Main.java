package main;

import menu.MenuList;

public class Main {
  public static void main(String[] args) {
    new MenuList();
    // Program ini adalah program sederhana todo list yang menggunakan Database
    // MySql.
    // Di program ini terdapat dua macam jenis barang yaitu Todo dan Tags.
    // Todo berfungsi menyimpan task atau tugas yang ingin kita kerjakan, contohnya:
    // buang sampah, beresin OOP, dll.
    // Tags berfungsi untuk menandakan todo, contohnya: school, chores,
    // money-related, dll.
    // Package menu berisi Class yang berfungsi menghandle perihal tampilan, print,
    // listing todo dan tags.
    // Package helper berisi method-method untuk membantu pemograman, seperti
    // membantu print garis dan formatting lainnya.
    // Package db berisi semua yang berhubungan dengan input/output database.

    // Fitur-fitur utama program ini adalah:
    // 1. Fitur create, add, edit, delete, view todo dan tags.
    // 2. Mencantumkan tags ke suatu todo.
    // 3. Mencari todo sesuai dengan tags.

    // Pembagian tugas anggota:
    // - Andersen: Hal-hal yang berhubungan dengan DB dan method-methodnya
    // (kebanyakan di package db).
    // - Winston Adinata Kusumo: Hal-hal yang berhubungan dengan todo dan pembuatan
    // menu.
    // - Denzel Wendy: Hal-hal yang berhubungan dengan tags dan pembuatan meny.
  }
}
