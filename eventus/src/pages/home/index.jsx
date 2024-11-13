import { Carousel } from "../../components/Carousel";
import { Navbar } from "../../components/Navbar";

import { CardEvent } from "../../components/CardEvent";
import { CardEvento } from "../../components/CardEvento";

export const Home = () => {
	return (
		<div className="container">
			<Navbar />
			<main className="py-2 d-flex flex-column gap-5">
				<Carousel />
				<h2>Titulo</h2>
				<section className="d-flex gap-3 align-items-center justify-content-center">
					<CardEvento evento={[]}/>
					<CardEvento evento={[]}/>
					<CardEvento evento={[]}/>
					<CardEvento evento={[]}/>
				</section>
			</main>
		</div>
	);
};
