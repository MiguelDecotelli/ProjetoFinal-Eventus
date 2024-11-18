import { Navbar } from "../../components/Navbar";
import { Footer } from "../../components/Footer";
import ImgContact from "../../img/contact.jpg";

export const Contact = () => {
	return (
		<div className="container ">
			<Navbar />
			<main className="row max-w-2xl mx-auto gap-5 p-5">
				<div className="col">
					<img
						src={ImgContact}
						alt="image-contact"
						style={{ width: "100%", height: "450px", objectFit: "cover" }}
					/>
				</div>

				<div className="row col mb-4 d-flex flex-column justify-content-center ">
					<h5 className="text-center display-4 font-weight-bold mb-5">
						Contato
					</h5>
					<div className="col-md-6 d-flex gap-2 align-items-center mb-3">
						<i className="fa-regular fa-envelope mr-2"></i>
						<span>contato@exemplo.com</span>
					</div>
					<div className="col-md-6 d-flex gap-2 align-items-center mb-3">
						<i className="fa-solid fa-phone mr-2"></i>
						<span>(11) 1234-5678</span>
					</div>
					<div className="col-12 d-flex gap-2 align-items-center mb-3">
						<i className="fa-solid fa-location-dot mr-2"></i>
						<span>Rua Exemplo, 123 - Cidade, Estado</span>
					</div>
				</div>

				<form className="col d-flex flex-column justify-content-center">
					<div className="form-group mb-3">
						<input
							type="text"
							className="form-control"
							placeholder="Seu nome"
							required
						/>
					</div>
					<div className="form-group mb-3">
						<input
							type="email"
							className="form-control"
							placeholder="Seu email"
							required
						/>
					</div>
					<div className="form-group mb-3">
						<textarea
							className="form-control"
							placeholder="Sua mensagem"
							required
							rows="3"
						/>
					</div>
					<div className="d-flex justify-content-center">
						<button type="submit" className="btn btn-outline-light">
							Enviar mensagem
						</button>
					</div>
				</form>
			</main>
			<Footer />
		</div>
	);
};
