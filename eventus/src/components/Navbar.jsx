import { Link, NavLink } from "react-router-dom";
import ImgLogo from "../img/logo-w.png";
import { useUser } from "../context/UserContext";

export function Navbar() {
	const { user, logout } = useUser();

	return (
		<nav className="navbar navbar-expand-lg bg container">
			<div className="container-fluid py-2">
				<NavLink className="navbar-brand" to="/">
					<img src={ImgLogo} width={40} />
				</NavLink>
				<button
					className="navbar-toggler"
					type="button"
					data-bs-toggle="collapse"
					data-bs-target="#navbarScroll"
					aria-controls="navbarScroll"
					aria-expanded="false"
					aria-label="Toggle navigation"
				>
					<span className="navbar-toggler-icon"></span>
				</button>
				<div className="collapse navbar-collapse" id="navbarScroll">
					<ul
						className="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll text-center"
						style={{ "--bs-scroll-height": "100px" }}
					>
						<li className="nav-item">
							<Link className="nav-link text" to="/contact">
								Contato
							</Link>
						</li>
						<li className="nav-item">
							<Link className="nav-link text" to="/nextEvents">
								Proximos eventos
							</Link>
						</li>
						<li className="nav-item">
							<Link className="nav-link text" to="#">
								Novidades
							</Link>
						</li>
						<li className="nav-item">
							<Link className="nav-link text" to="#">
								Histórico
							</Link>
						</li>
					</ul>
					<form className="p-2 d-flex justify-content-center" role="search">
						<input
							className="form-control me-2"
							style={{ maxWidth: "250px" }}
							type="search"
							placeholder="O que você procura?"
							aria-label="Search"
						/>
						<button className="btn btn-outline-light" type="submit">
							<i className="fa-solid fa-magnifying-glass"></i>
						</button>
					</form>
					{user ? (
						<div className="p-2 d-flex justify-content-center align-items-center gap-2">
							<span>Olá, {user}!</span>

							<button type="button" onClick={logout} className="btn btn-light">
								Sair
							</button>
						</div>
					) : (
						<div className="p-2 d-flex justify-content-center gap-2">
							<button type="button" className="btn btn-outline-light">
								<NavLink to="/signup">Cadastrar</NavLink>
							</button>

							<button type="button" className="btn btn-light">
								<NavLink to="/login">Entrar</NavLink>
							</button>
						</div>
					)}
				</div>
			</div>
		</nav>
	);
}
