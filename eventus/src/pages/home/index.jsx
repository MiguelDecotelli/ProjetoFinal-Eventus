import { Carousel } from "../../components/Carousel";
import { Navbar } from "../../components/Navbar";

import { CardEvent } from "../../components/CardEvent";
import { CardEvento } from "../../components/CardEvento";

import { useState, useEffect, useContext } from 'react';
import { DataContext } from '../../context/DataContext';

export const Home = () => {
	//States do Projeto
  const { eventos } = useContext(DataContext);

	const [ eventosRenderizados, setEventosRenderizados ] = useState([]);

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

    // Intervalos para trocar os eventos renderizados
    const temporizadorEventos = setInterval(() => {
      pegarEventosAleatorios();
    }, 5000); // 5000ms = 5 segundos

    // Limpa o intervalo quando o componente for desmontado
    return () => clearInterval(temporizadorEventos);
  }, [eventos]);

	return (
		<div className="container">
			<Navbar />
			<main className="py-2 d-flex flex-column gap-5">
				<Carousel />
				<h2 className="mt-5 mb-0">Eventos em Destaque</h2>
				<section className="d-flex gap-3 align-items-center justify-content-center mb-5">
					{eventosRenderizados.length > 0 ? (
            eventosRenderizados.map((evento, index) => (
              <CardEvento key={index} evento={evento} />
            ))
          ) : (
            <p>Carregando eventos...</p>
          )}
				</section>
			</main>
		</div>
	);
};
