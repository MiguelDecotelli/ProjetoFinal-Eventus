import { Carousel } from "../../components/Carousel";
import { Navbar } from "../../components/Navbar";
import { CardEvent } from "../../components/CardEvent";
import { CardEvento } from "../../components/CardEvento";
import { TextTitle } from "../../components/TextTitle";
import { ImagemHome01 } from "../../components/ImagemHome01";
import { TextHome } from "../../components/TextHome";
import { ImagemHome02 } from "../../components/ImagemHome02";
import { Footer } from "../../components/Footer";
import { useState, useEffect, useContext } from 'react';
import { DataContext } from '../../context/DataContext';

export const Home = () => {

	const { eventos } = useContext(DataContext);
	const [eventosRenderizados, setEventosRenderizados] = useState([]);
	// Função para pegar eventos aleatórios
	const pegarEventosAleatorios = () => {
		if (eventos.length > 0) {
			// Embaralha o array de eventos e seleciona os primeiros 4
			const eventosAleatorios = eventos
				.sort(() => Math.random() - 0.5) // Embaralha o array
				.slice(0, 4); // Pega os 4 primeiros eventos
			setEventosRenderizados(eventosAleatorios);
		}
	};
	useEffect(() => {
		pegarEventosAleatorios();
		const temporizadorEventos = setInterval(() => {
			pegarEventosAleatorios();
		}, 15000);
		return () => clearInterval(temporizadorEventos);
	}, [eventos]);
	return (
		<div className="container-carousel">
			<Navbar />
			<main className="d-flex flex-column gap-0">
				<Carousel />
				<TextTitle />
				<ImagemHome01 />
				<TextHome />
				<ImagemHome02 />
				<section className="d-flex gap-3 align-items-center justify-content-center">
					<CardEvent />
					<CardEvent />
					<CardEvent />
					<CardEvent />
				</section>
				<section className="d-flex gap-3 align-items-center justify-content-center mb-5">
					{eventosRenderizados.length > 0 ? (
						eventosRenderizados.map((evento, index) => (
							<CardEvento key={index} evento={evento} />
						))
					) : (
						<p>Carregando eventos...</p>
					)}
				</section>
				<Footer />
			</main>
		</div>
	);
};
