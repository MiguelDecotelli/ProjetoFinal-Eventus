import { Carousel } from "../../components/Carousel";
import { Navbar } from "../../components/Navbar";

import { CardEvent } from "../../components/CardEvent";

export const Home = () => {
	return (
		<div className="container">
			<Navbar />
			<main className="py-2 d-flex flex-column gap-5">
				<Carousel />
				<h2>Titulo</h2>
				<section className="d-flex gap-3 align-items-center justify-content-center">
					<CardEvent />
					<CardEvent />
					<CardEvent />
					<CardEvent />
				</section>
			</main>
		</div>
	);
};
