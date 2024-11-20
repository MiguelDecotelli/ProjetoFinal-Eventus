import login from "./admin.js";
import axios from "axios";

const jwt = await login();
const url = "http://localhost:8080/api/cities";
const config = {
	headers: {
		"Authorization": "Bearer " + jwt
	}
}
// Create city
async function createCity(url, config){
	const data ={
		"name": "Caçapava do Sul",
		"state": "RS"
	}
	return await axios.post(url, data, config)
		.then(response=>{console.log("Cidade criada!"); return response.data;})
		.catch(e=>{return e;}
	);
}
// Read All Cities
async function readAllCities(url, config){
	return await axios.get(url, config)
		.then(response=>{return response.data;})
		.catch(_=>{console.log("Erro ao ler todos!");}
	);
}
// ReadCityById
async function readCityById(url, config, id){
	return await axios.get(url+"/"+id, config)
		.then(response=>{return response.data;})
		.catch(_=>{console.log("Erro ao ler por id!");}
	);
}
// UpdateCity
async function updateCity(url, config, id){
	const data = {
		"name": "Porto Alegre",
		"state": "RS"
	}
	return await axios.put(url+"/"+id, data, config)
		.then(response=>{ return response.data;})
		.catch(_=>{console.log("Erro ao atualizar!");}
	);
}
// DeleteCity
async function deleteCity(url, config, id){
	return await axios.delete(url+"/"+id, config)
		.then(response=>{return response.data;})
		.catch(_=>{console.log("Erro ao deletar!");}
	);
}
const cidade = await createCity(url, config);
console.log(cidade);
const id = cidade.id;
console.log(await readAllCities(url, config));
console.log(await readCityById(url, config, id));
console.log(await updateCity(url, config, id));
console.log(await deleteCity(url, config, id));
