import { Link, NavLink } from "react-router-dom";

const inputCSS =
	"form-control border-0 bg-transparent custom-input px-0 py-2 shadow-none";

export const Login = () => {
	return (
		<main className="d-flex  align-items-center justify-content-center vh-100 custom-gradient">
			<section className="bg text m-auto custom-section row g-0 position-relative">
				<div className="col position-relative w-100 h-100 custom-background-login">
					<div className="position-absolute top-0 start-0 end-0 bottom-0 overlay d-flex flex-row">
						<NavLink to="/" className="p-2 z-2">
							<i className="fa-solid fa-xmark"></i>
						</NavLink>
					</div>
				</div>

				<div className="col">
					<div className="d-flex gap-4 flex-column justify-content-center h-100 p-3 position-relative">
						<Link
							to="/signup"
							className="position-absolute top-0 d-flex justify-content-evenly p-3 z-2 custom-style-left"
						>
							<i className="fa-solid fa-angles-left"></i>Cadastrar
						</Link>

						<form className="p-4 d-flex flex-column gap-2">
							<h3>Conecte-se</h3>

							<div className="input-group d-flex flex-nowrap border-bottom">
								<label
									className="input-group-text border-0 bg-transparent text p-0"
									htmlFor="inputEmail"
								>
									EMAIL
								</label>
								<input
									type="email"
									className={inputCSS}
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
									className={inputCSS}
									id="inputPassword"
									placeholder="********"
								/>
							</div>

							<div className="d-flex justify-content-between">
								<div className="form-check">
									<input
										className="form-check-input"
										type="checkbox"
										id="remember"
									/>
									<label className="form-check-label small" htmlFor="remember">
										Lembrar de mim.
									</label>
								</div>

								<Link to="#" className="link-secondary small">
									Esqueceu a senha?
								</Link>
							</div>

							<button className="btn mx-4 btn-outline-light mt-2" type="submit">
								<NavLink to="/">Entrar</NavLink>
							</button>
						</form>

						<div className="d-grid text-center gap-2 col-6 mx-auto">
							<p>OU</p>
							<button
								className="btn btn-light rounded-pill border d-flex gap-2 align-items-center justify-content-center"
								type="button"
							>
								<i className="fa-brands fa-google"></i>
								Entrar com o Google
							</button>
						</div>
					</div>
				</div>
			</section>
		</main>
	);
};
