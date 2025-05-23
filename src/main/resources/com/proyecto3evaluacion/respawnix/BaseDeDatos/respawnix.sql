-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2025 a las 17:52:47
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `respawnix`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administrador`
--

CREATE TABLE `administrador` (
  `emailUsuario` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administrador`
--

INSERT INTO `administrador` (`emailUsuario`) VALUES
('dylan@gmail.com'),
('pedro@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cestacompra`
--

CREATE TABLE `cestacompra` (
  `idCesta` int(11) NOT NULL,
  `emailUsuario` varchar(40) NOT NULL,
  `nombreVideojuego` varchar(60) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjeta`
--

CREATE TABLE `tarjeta` (
  `id` int(11) NOT NULL,
  `nombre` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjetapremium`
--

CREATE TABLE `tarjetapremium` (
  `email` varchar(30) NOT NULL,
  `porcentaje` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarjetavip`
--

CREATE TABLE `tarjetavip` (
  `email` varchar(30) NOT NULL,
  `porcentaje` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `Nombre` varchar(30) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `fechaNacimiento` date NOT NULL,
  `email` varchar(40) NOT NULL,
  `password` varchar(80) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Nombre`, `apellidos`, `fechaNacimiento`, `email`, `password`) VALUES
('antonio', 'luna marquez', '2000-05-11', 'antonio@gmail.com', '$2a$10$YsqyGwk6MD2hSo48NhnsMOSoiPIgYxLFWgYhlAyN4wT9s70NbQya6'),
('Dylan', 'Abaz rivas', '2005-05-20', 'dylan@gmail.com', '$2a$10$4lPK4D9nZYF9QzlLcvEMjOI9Q3N4y8KXKfHCmlujzaerh22ayI/0G'),
('Javier', 'adamuz montoro', '2006-03-13', 'javi@gmail.com', '$2a$10$rk7HOUewjuUlSrUjlXq/8.Hr3GGTstgzNAQ8u9gE0U5bgmeoCKb7C'),
('luis', 'abaz', '2008-05-13', 'luis@gmail.com', '$2a$10$Oe69pz6o0g6AV.5OgfDXOuwml3AnlBKIuPQc77U900LOGzAqgjnR2'),
('dylan', 'Abad Rivas', '2007-05-11', 'nuevoemail@gmail.com', '$2a$10$TvaNo.0pDfz1WxaU1moOF.cUYFzTDL5ef1Pf3q6pEgNs5lSaZszG.'),
('pedro', 'castaño marin', '2005-02-02', 'pedro@gmail.com', '$2a$10$QCkrQDn1vylaWgy.u/ytNeB0VkZwq/njBURahpZjVtDM9qy5K3acO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuego`
--

CREATE TABLE `videojuego` (
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(400) NOT NULL,
  `genero` varchar(90) NOT NULL,
  `plataforma` varchar(60) NOT NULL,
  `precio` double(5,2) NOT NULL,
  `Imagen` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `videojuego`
--

INSERT INTO `videojuego` (`nombre`, `descripcion`, `genero`, `plataforma`, `precio`, `Imagen`) VALUES
('Blasphemous', 'Un metroidvania de acción brutal ambientado en un mundo oscuro e inspirado en el folclore y el arte religioso español.', 'Acción, Metroidvania', 'PC', 26.99, 'images\\Blasphemous.jpg'),
('Cyberpunk 2077', 'Un juego de rol en un mundo futurista donde los jugadores asumen el papel de V, un mercenario en la ciudad distópica de Night City. El juego se caracteriza por su narrativa compleja y un mundo abierto lleno de decisiones y misiones.', 'RPG, Acción', 'PC, PlayStation 4, Xbox One, PlayStation 5, Xbox Series X/S', 59.99, 'images\\Cyberpunk 2077.jpg'),
('Dark Souls', '\"Dark Souls\" es un videojuego de rol y acción desarrollado por FromSoftware. Es conocido por su dificultad, diseño de niveles interconectados y su atmósfera oscura y sombría', 'RPG, Acción, Aventura', 'PS3, Xbox 360, PC', 29.99, 'images\\Dark souls 1.jpg'),
('Dark Souls II', 'La secuela del aclamado \"Dark Souls\", presenta un nuevo protagonista en un mundo igualmente brutal y lleno de desafíos. Con nuevas mecánicas y un sistema de combate refinado, \"Dark Souls II\" continúa la tradición de dificultad extrema y narrativa sutil', 'RPG, Acción, Aventura', 'PS3, Xbox 360, PC', 29.99, 'images\\dark souls 2.jpg'),
('Dark Souls III', 'Un juego de rol de acción conocido por su dificultad extrema, combate desafiante y mundo interconectado. Los jugadores exploran un mundo oscuro y peligroso, luchando contra enemigos y jefes.', 'RPG, Acción, Aventura', 'PC, PlayStation 4, Xbox One', 39.99, 'images\\dark souls 3.jpg'),
('Dead Cells', 'Dead Cells mezcla elementos de roguelike y metroidvania en una experiencia frenética con combates fluidos. Cada muerte reinicia el juego, pero te permite mejorar lentamente, lo que fomenta la experimentación y la perseverancia.', 'Roguelike, Acción, Plataformas', 'PC, PS4, Xbox One, Nintendo Switch, iOS, Android', 24.99, 'images\\deadCells.jpg'),
('Elden Ring', '\"Elden Ring\" es un videojuego de rol y acción de mundo abierto, desarrollado por FromSoftware y dirigido por Hidetaka Miyazaki, en colaboración con el escritor George R. R. Martin', 'RPG, Acción, Mundo abierto', 'PS4, PS5, Xbox One, Xbox Series X/S, PC', 59.99, 'images\\elden ring.jpg'),
('Grand Theft Auto V', 'Un juego de acción en mundo abierto que permite a los jugadores asumir el control de tres personajes en una ciudad ficticia, llevando a cabo misiones en un mundo lleno de crimen y caos.', 'Acción, Aventura, Mundo abierto', 'PC, PlayStation 4, Xbox One, PlayStation 5, Xbox Series X/S', 39.99, 'images\\gta.jpg'),
('Hades', 'Hades es un roguelike aclamado por su narrativa dinámica, combates rápidos y personajes carismáticos basados en la mitología griega. Juegas como Zagreus, el hijo de Hades, intentando escapar del Inframundo con la ayuda de los dioses del Olimpo.', 'Roguelike, Acción', 'PC, PS4, PS5, Xbox One, Xbox Series X|S, Nintendo Switch', 24.99, 'images\\Hades.png'),
('Hollow Knight', 'Hollow Knight es un metroidvania de acción en 2D que te pone en la piel de un misterioso caballero que explora el vasto y arruinado reino de Hallownest. Con un arte dibujado a mano, una ambientación melancólica y combates exigentes, es una experiencia profundamente inmersiva.', 'Metroidvania, Acción, Aventura', 'PC, Nintendo Switch, PS4, Xbox One', 20.00, 'images\\hollowKnight.png'),
('Minecraft', 'Minecraft es un videojuego de construcción de tipo \"sandbox\", en el que los jugadores pueden explorar mundos generados aleatoriamente y construir estructuras a partir de bloques.', 'Aventura / Sandbox', 'PC', 27.95, 'images\\Minecraft.jpg'),
('Red Dead Redemption 2', 'Un juego de acción y aventura en mundo abierto que narra la historia de Arthur Morgan, un miembro de una banda de forajidos en el siglo XIX. El juego es conocido por su historia épica y su recreación detallada de un mundo salvaje.', 'Acción, Aventura', 'PC, PlayStation 4, Xbox One', 59.99, 'images\\Red Dead Redemption 2.jpg'),
('The Witcher 3: Wild Hunt', 'The Witcher 3 te pone en la piel de Geralt de Rivia, un cazador de monstruos en un mundo abierto vibrante y adulto. Ofrece una narrativa profunda, elecciones morales y un sistema de combate táctico con espadas, magia y alquimia.', 'RPG, Aventura, Mundo Abierto', 'PS4, PS5, Xbox One, Xbox Series X|S, PC, Nintendo Switch', 39.99, 'images\\The Witcher 3.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `videojuegoscomprados`
--

CREATE TABLE `videojuegoscomprados` (
  `idJuegoComprado` int(11) NOT NULL,
  `emailUsuario` varchar(40) NOT NULL,
  `NombreJuego` varchar(60) NOT NULL,
  `precioJuego` double(10,2) NOT NULL,
  `precioTotal` double(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `videojuegoscomprados`
--

INSERT INTO `videojuegoscomprados` (`idJuegoComprado`, `emailUsuario`, `NombreJuego`, `precioJuego`, `precioTotal`, `cantidad`) VALUES
(1, 'antonio@gmail.com', 'Dead Cells', 22.49, 22.49, 1),
(3, 'antonio@gmail.com', 'Grand Theft Auto V', 35.99, 35.99, 1),
(4, 'antonio@gmail.com', 'Blasphemous', 22.49, 22.49, 1),
(5, 'antonio@gmail.com', 'Grand Theft Auto V', 35.99, 35.99, 1),
(6, 'antonio@gmail.com', 'Elden Ring', 53.99, 53.99, 1),
(7, 'antonio@gmail.com', 'Dark Souls', 28.49, 28.49, 1),
(8, 'antonio@gmail.com', 'Cyberpunk 2077', 59.99, 119.98, 2),
(9, 'antonio@gmail.com', 'Dark Souls II', 29.99, 29.99, 1),
(10, 'antonio@gmail.com', 'Grand Theft Auto V', 35.99, 35.99, 1),
(11, 'antonio@gmail.com', 'Minecraft', 25.16, 25.16, 1),
(12, 'luis@gmail.com', 'Dark Souls II', 28.49, 28.49, 1),
(13, 'luis@gmail.com', 'Cyberpunk 2077', 59.99, 59.99, 1),
(14, 'luis@gmail.com', 'Hades', 24.99, 24.99, 1),
(15, 'antonio@gmail.com', 'Hades', 24.99, 24.99, 1),
(16, 'antonio@gmail.com', 'Hades', 22.49, 22.49, 1),
(17, 'antonio@gmail.com', 'Grand Theft Auto V', 37.99, 37.99, 1),
(18, 'antonio@gmail.com', 'Dark Souls III', 39.99, 39.99, 1),
(19, 'antonio@gmail.com', 'Grand Theft Auto V', 39.99, 39.99, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD PRIMARY KEY (`emailUsuario`);

--
-- Indices de la tabla `cestacompra`
--
ALTER TABLE `cestacompra`
  ADD PRIMARY KEY (`idCesta`),
  ADD KEY `fk_email_usuario` (`emailUsuario`),
  ADD KEY `fk_videojuego_cesta` (`nombreVideojuego`);

--
-- Indices de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `tarjetapremium`
--
ALTER TABLE `tarjetapremium`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `tarjetavip`
--
ALTER TABLE `tarjetavip`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`email`);

--
-- Indices de la tabla `videojuego`
--
ALTER TABLE `videojuego`
  ADD PRIMARY KEY (`nombre`);

--
-- Indices de la tabla `videojuegoscomprados`
--
ALTER TABLE `videojuegoscomprados`
  ADD PRIMARY KEY (`idJuegoComprado`),
  ADD KEY `NombreJuego` (`NombreJuego`),
  ADD KEY `emailUsuario` (`emailUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cestacompra`
--
ALTER TABLE `cestacompra`
  MODIFY `idCesta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- AUTO_INCREMENT de la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `videojuegoscomprados`
--
ALTER TABLE `videojuegoscomprados`
  MODIFY `idJuegoComprado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `administrador`
--
ALTER TABLE `administrador`
  ADD CONSTRAINT `administrador_ibfk_1` FOREIGN KEY (`emailUsuario`) REFERENCES `usuario` (`email`);

--
-- Filtros para la tabla `cestacompra`
--
ALTER TABLE `cestacompra`
  ADD CONSTRAINT `fk_email_usuario` FOREIGN KEY (`emailUsuario`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_videojuego_cesta` FOREIGN KEY (`nombreVideojuego`) REFERENCES `videojuego` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tarjeta`
--
ALTER TABLE `tarjeta`
  ADD CONSTRAINT `tarjeta_ibfk_1` FOREIGN KEY (`email`) REFERENCES `usuario` (`email`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `tarjetapremium`
--
ALTER TABLE `tarjetapremium`
  ADD CONSTRAINT `tarjetapremium_ibfk_1` FOREIGN KEY (`email`) REFERENCES `tarjeta` (`email`);

--
-- Filtros para la tabla `tarjetavip`
--
ALTER TABLE `tarjetavip`
  ADD CONSTRAINT `tarjetavip_ibfk_1` FOREIGN KEY (`email`) REFERENCES `tarjeta` (`email`);

--
-- Filtros para la tabla `videojuegoscomprados`
--
ALTER TABLE `videojuegoscomprados`
  ADD CONSTRAINT `videojuegoscomprados_ibfk_1` FOREIGN KEY (`NombreJuego`) REFERENCES `videojuego` (`nombre`),
  ADD CONSTRAINT `videojuegoscomprados_ibfk_2` FOREIGN KEY (`emailUsuario`) REFERENCES `usuario` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
