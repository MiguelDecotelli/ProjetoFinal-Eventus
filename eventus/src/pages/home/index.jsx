import { Carousel } from "../../components/Carousel";
import { Navbar } from "../../components/Navbar";
import { CardEvent } from "../../components/CardEvent";
import { TextTitle } from "../../components/TextTitle";
import { ImagemHome01 } from "../../components/ImagemHome01";
import { TextHome } from "../../components/TextHome";
import { ImagemHome02 } from "../../components/ImagemHome02";
import { Footer } from "../../components/Footer";

export const Home = () => {
	return (
		<div className="container-carousel">
			<Navbar />
			<main className="d-flex flex-column gap-3">
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
				<section className="d-flex gap-3 align-items-center justify-content-center">
					<CardEvent />
					<CardEvent />
					<CardEvent />
					<CardEvent />
				</section>
				<Footer />
			</main>
		</div>
	);
};
