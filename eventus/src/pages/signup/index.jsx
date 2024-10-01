import { Link } from "react-router-dom";

export const SignUp = () => {
	return (
		<main className="d-flex align-items-center justify-content-center vh-100 custom-gradient">
			<section className="bg text m-auto custom-section row g-0 position-relative">
				<div className="col">
					<div className="d-flex flex-column justify-content-center h-100 p-3 position-relative">
						<Link
							to="/login"
							className="position-absolute top-0 d-flex justify-content-evenly p-3 z-2 custom-style-right"
						>
							Entrar <i className="fa-solid fa-angles-right"></i>
						</Link>

						<form className="p-4 d-flex flex-column gap-4">
							<h3>Cadastre-se</h3>
							<div className="input-group d-flex flex-nowrap border-bottom">
								<label
									className="input-group-text border-0 bg-transparent text p-0"
									htmlFor="username"
								>
									NOME DE USUÁRIO
								</label>
								<input
									type="text"
									className="form-control border-0 bg-transparent custom-input px-0 py-2"
									id="username"
									placeholder="exemplo_usuario"
								/>
							</div>

							<div className="input-group d-flex flex-nowrap border-bottom">
								<label
									className="input-group-text border-0 bg-transparent text p-0"
									htmlFor="inputEmail"
								>
									EMAIL
								</label>
								<input
									type="email"
									className="form-control border-0 bg-transparent custom-input px-0 py-2"
									id="inputEmail"
									placeholder="exemplo@email.com"
								/>
							</div>

							<div className="input-group d-flex flex-nowrap border-bottom">
								<label
									className="input-group-text border-0 bg-transparent text p-0"
									htmlFor="inputPassword"
								>
									SENHA
								</label>
								<input
									type="password"
									className="form-control border-0 bg-transparent custom-input px-0 py-2"
									id="inputPassword"
									placeholder="********"
								/>
							</div>

							<div className="input-group d-flex flex-nowrap border-bottom">
								<label
									className="input-group-text border-0 bg-transparent text p-0"
									htmlFor="inputConfirmPassword"
								>
									CONFIRMAR SENHA
								</label>
								<input
									type="confirmPassword"
									className="form-control border-0 bg-transparent custom-input px-0 py-2"
									id="inputConfirmPassword"
									placeholder="********"
								/>
							</div>

							<button
								className="btn mx-auto btn-outline-light mt-2"
								type="submit"
							>
								Enviar
							</button>
						</form>
					</div>
				</div>

				<div className="col position-relative w-100 h-100 custom-background-signup">
					<div className="position-absolute top-0 start-0 end-0 bottom-0 overlay"></div>
				</div>
			</section>
		</main>
	);
};
