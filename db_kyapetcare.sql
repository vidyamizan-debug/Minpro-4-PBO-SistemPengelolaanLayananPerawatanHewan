-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 01:12 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_kyapetcare`
--

-- --------------------------------------------------------

--
-- Table structure for table `layanan`
--

CREATE TABLE `layanan` (
  `id_layanan` int NOT NULL,
  `kategori` varchar(20) NOT NULL,
  `nama_layanan` varchar(100) NOT NULL,
  `catatan` varchar(255) NOT NULL,
  `harga` int NOT NULL,
  `nama_hewan` varchar(50) NOT NULL,
  `nama_pemilik` varchar(50) NOT NULL,
  `jenis_hewan` varchar(50) NOT NULL,
  `umur_hewan` int NOT NULL,
  `lama_penitipan` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `layanan`
--

INSERT INTO `layanan` (`id_layanan`, `kategori`, `nama_layanan`, `catatan`, `harga`, `nama_hewan`, `nama_pemilik`, `jenis_hewan`, `umur_hewan`, `lama_penitipan`) VALUES
(1, 'Perawatan', 'Perawatan Mandi', 'Hati-hati yach dia agak ngereog kalo kena air..', 120000, 'Kiya', 'Kansa', 'Kucing Himalayan', 3, NULL),
(2, 'Penitipan', 'Penitipan 3 Hari', 'Anas harus dielus dulu baru mau tidur, disayang yaaaa', 150000, 'Anas', 'Vidi', 'Anjing Golden Retriever', 2, 3),
(5, 'Perawatan', 'Perawatan Bulu', 'dirawat DGN benAr y,,,', 75000, 'Cyuti', 'Kyuti', 'Ulat Bulu', 1, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `layanan`
--
ALTER TABLE `layanan`
  ADD PRIMARY KEY (`id_layanan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `layanan`
--
ALTER TABLE `layanan`
  MODIFY `id_layanan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
