import axios from "axios";

async function login(user){
	const response = await axios.post("http://localhost:8080/api/auth/login", user).catch(_=>{console.log("Erro ao logar com: " + user)});
	return response.data;
}

// Registration
async function newUser(){
	const userDetails ={
		"username":"vladimir1234",
		"password":"1234",
		"email":"vlad@transilvania.com",
		"name":"vladimir",
		"lastname":"putin",
		"birthday":"1000-01-01"
	}
	return await axios.post("http://localhost:8080/api/auth/register", userDetails)
		.then((response)=> {console.log(response.status == 200 ? "vladimir cadastrado" : "vladimir não cadastrou"); return response.data;})
		.catch(e=>{console.log(e)});
}
const vlad = await newUser();
console.log(vlad);
// Login
const vladJwt = await login({"username": "vladimir1234", "password":"1234"})
	.then(response=>{console.log("Vlad logado, JWT: " + response)})
	.catch(_=>{console.log("Não foi possivel logar com o vlad")});
// Login with Admin
const admin = {"username": "denis", "password":"1234"}
const adminJwt = await login(admin);
console.log(adminJwt);
const adminConfig = {
	headers: {
		"Authorization": "Bearer " + adminJwt
	}
}
// GetAllUsers
console.log(
	"All Users \n",
);
await axios.get("http://localhost:8080/api/users",adminConfig)
.then((response) =>{ console.log(response.data) })
.catch((e)=>{console.log(e)})
// GetUserById
console.log(
	"Getting Vlad By Id \n",
	await axios.get("http://localhost:8080/api/users/"+ vlad.id, adminConfig)
	.then((response) =>{ console.log(response.data); return response.data })
	.catch((e)=>{console.log("erro ao buscar o vlad por id");})
);

// CreateNewUser

async function adminCreatesNewUser(){
	const data = {
		"username":"luara1234",
		"password":"1234",
		"name":"luara",
		"lastname":"braba dos readme",
		"email":"luara@email.com",
		"birthday":"2003-01-01",
		"role": "ADMIN"
	}
	return await axios.post("http://localhost:8080/api/users", data ,adminConfig)
		.then(response=>{ console.log("Luara criada!"); return response.data; })
		.catch(_=>{console.log("Não foi possível criar a Luara ")})
}
const luara = await adminCreatesNewUser();
console.log(luara);

// Update User
async function adminUpdateUser(id){
	const data = {
		"username":"luara1234",
		"password":"1234",
		"name":"luara",
		"lastname":"readme",
		"email":"luara@email.com",
		"birthday":"2003-01-01",
		"role": "BASIC"
	}
	return await axios.put("http://localhost:8080/api/users/"+id, data ,adminConfig)
		.then(response=>{ console.log(response.data); })
		.catch(_=>{console.log("Não foi possível atualizar a Luara ")})
}
await adminUpdateUser(luara.id);

// Delete User
async function deleteUser(id){
	const response = await axios.delete("http://localhost:8080/api/users/" + id, adminConfig)
		.catch(_ =>{console.log("Não foi possivel deletar o id: " + id)}
	);
	return response;
}
await deleteUser(vlad.id);
await deleteUser(luara.id);
