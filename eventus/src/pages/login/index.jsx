import { useState } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";

import { Input } from "../../components/Input";
import { makeRequest } from "../../utils/makeRequest";
import { useUser } from "../../context/UserContext";

const schema = yup.object().shape({
	username: yup.string().required("O usuario é obrigatório."),
	password: yup.string().required("A senha é obrigatória."),
});

const validateUser = async (data, setError) => {
	console.log(data);
	const reqData = {username: data.username, password: data.password};
	const response = await makeRequest("http://localhost:8080/api/auth/login", "POST", reqData);
	if(response === null){ 
		setError("password", {type: "manual", message: "Login invalido"})
		return false;
	}
	const token = response.token;
	const user = response.user;
	localStorage.setItem("token", token)

	return user;
};

export const Login = () => {
	const { setUser } = useUser();
	const [loading, setLoading] = useState(false);
	const navigate = useNavigate();

	const {
		register,
		handleSubmit,
		setError,
		formState: { errors },
	} = useForm({
		resolver: yupResolver(schema),
	});

	async function handleLogin(data) {
		setLoading(true);

		const user = await validateUser(data, setError);

		if (!user) {
			setLoading(false);
			return;
		}
		setUser(user);
		navigate("/");
	}

	return (
		<main className="d-flex align-items-center justify-content-center vh-100 custom-gradient">
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

						<form
							className="p-4 d-flex flex-column gap-2"
							onSubmit={handleSubmit(handleLogin)}
						>
							<h3>Conecte-se</h3>

							<Input
								label="USERNAME"
								id="inputUsername"
								type="text"
								placeholder="usuario"
								{...register("username")}
								error={errors.username?.message}
							/>
							<Input
								label="SENHA"
								id="inputPassword"
								type="password"
								placeholder="********"
								{...register("password")}
								error={errors.password?.message}
							/>

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

							<button
								className="btn mx-4 btn-outline-light mt-4"
								type="submit"
								disabled={loading}
							>
								{loading ? "Carregando..." : "Entrar"}
							</button>
						</form>

						<div className="d-grid text-center gap-1 col-6 mx-auto">
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
