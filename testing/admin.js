import axios from "axios";

async function login(){
	const admin = {"username": "Logan", "password":"1234"}
	const response = await axios.post("http://localhost:8080/api/auth/login", admin)
		.catch(_=>{console.log("Erro ao logar com o Admin")}
	);
	return response.data;
}

export default login;
