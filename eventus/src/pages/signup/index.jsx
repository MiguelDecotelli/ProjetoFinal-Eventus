/* eslint-disable no-unused-vars */
import { useState } from "react";
import { Link, NavLink, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";

import { Input } from "../../components/Input";
import { makeRequest } from "../../utils/makeRequest";
// import backgroundImg from "/src/img/bkg.jpg";




const schema = yup.object().shape({
	username: yup.string().required("O usuário é obrigatório."),
	email: yup
		.string()
		.email("Email inválido")
		.required("O email é obrigatório."),
	password: yup
		.string()
		.min(8, "A senha deve ter no mínimo 8 caracteres")
		.required("A senha é obrigatória."),
	confirmPassword: yup
		.string()
		.oneOf([yup.ref("password"), null], "As senhas devem coincidir")
		.required("A confirmação da senha é obrigatória."),
});

export const SignUp = () => {
	const [loading, setLoading] = useState(false);
	const navigate = useNavigate();

	const {
		register,
		handleSubmit,
		formState: { errors },
	} = useForm({
		resolver: yupResolver(schema),
	});

	async function handleSignup(data) {
		setLoading(true);

		const { confirmPassword, ...signupData } = data;

		try {
			await makeRequest("http://localhost:8080/api/auth/register", "POST", signupData);
			alert("Usuário cadastrado com sucesso!");
			navigate("/login");
		} catch (error) {
			console.error("Erro ao cadastrar usuário:", error);
			alert("Erro ao cadastrar usuário. Tente novamente.");
		} finally {
			setLoading(false);
		}
	}

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

						<form
							className="p-4 d-flex flex-column gap-2"
							onSubmit={handleSubmit(handleSignup)}
						>
							<h3>Cadastre-se</h3>

							<Input
								label="USUÁRIO"
								id="username"
								placeholder="exemplo_usuario"
								{...register("username")}
								error={errors.username?.message}
							/>
							<Input
								label="EMAIL"
								id="inputEmail"
								type="email"
								placeholder="exemplo@email.com"
								{...register("email")}
								error={errors.email?.message}
							/>
							<Input
								label="SENHA"
								id="inputPassword"
								type="password"
								placeholder="********"
								{...register("password")}
								error={errors.password?.message}
							/>
							<Input
								label="CONFIRMAR SENHA"
								id="inputConfirmPassword"
								type="password"
								placeholder="********"
								{...register("confirmPassword")}
								error={errors.confirmPassword?.message}
							/>

							<button
								className="btn mx-4 btn-outline-light mt-2"
								type="submit"
								disabled={loading}
							>
								{loading ? "Carregando..." : "Enviar"}
							</button>
						</form>
					</div>
				</div>

				<div className="col position-relative w-100 h-100 custom-background-signup">
					<div className="position-absolute top-0 start-0 end-0 bottom-0 overlay d-flex flex-row-reverse">
						<NavLink to="/" className="p-2 z-2">
							<i className="fa-solid fa-xmark"></i>
						</NavLink>
					</div>
				</div>
			</section>
		</main>
	);
};
