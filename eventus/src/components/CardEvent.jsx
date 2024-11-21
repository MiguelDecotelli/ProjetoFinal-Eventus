import ImgCard from "../img/banner1.jpg";

export function CardEvent() {
	return (
		<div className="card" style={{ width: "18rem" }}>
			<img src={ImgCard} className="card-img-top" alt="Image not Found" />
			<div className="card-body">
				<h5 className="card-title">Card title</h5>
				<p className="card-text">
					Some quick example text to build on the card title and make up the
					bulk of the cards content.
				</p>

				<div className="d-flex justify-content-center">
					<a href="#" className="btn btn-orange mx-auto">
						Go somewhere
					</a>
				</div>
			</div>
		</div>
	);
}
