import ImgBanner1 from "../img/banner1.jpg";
import ImgBanner2 from "../img/banner2.jpg";
import ImgBanner3 from "../img/banner3.jpg";

export function Carousel() {
	return (
		<div id="carousel" className="carousel slide custom-carousel">
			<div className="carousel-indicators">
				<button
					type="button"
					data-bs-target="#carousel"
					data-bs-slide-to="0"
					className="active"
					aria-current="true"
					aria-label="Slide 1"
				></button>
				<button
					type="button"
					data-bs-target="#carousel"
					data-bs-slide-to="1"
					aria-label="Slide 2"
				></button>
				<button
					type="button"
					data-bs-target="#carousel"
					data-bs-slide-to="2"
					aria-label="Slide 3"
				></button>
			</div>
			<div className="carousel-inner">
				<div className="carousel-item active">
					<img src={ImgBanner1} className="d-block w-100" alt="..." />
				</div>
				<div className="carousel-item">
					<img src={ImgBanner2} className="d-block w-100" alt="..." />
				</div>
				<div className="carousel-item">
					<img src={ImgBanner3} className="d-block w-100" alt="..." />
				</div>
			</div>
			<button
				className="carousel-control-prev"
				type="button"
				data-bs-target="#carousel"
				data-bs-slide="prev"
			>
				<span className="carousel-control-prev-icon" aria-hidden="true"></span>
				<span className="visually-hidden">Previous</span>
			</button>
			<button
				className="carousel-control-next"
				type="button"
				data-bs-target="#carousel"
				data-bs-slide="next"
			>
				<span className="carousel-control-next-icon" aria-hidden="true"></span>
				<span className="visually-hidden">Next</span>
			</button>
		</div>
	);
}
