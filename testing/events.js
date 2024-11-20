import login from "./admin.js";
import axios from "axios";

const jwt = await login();
const url = "http://localhost:8080/api/events";
const config = {
	headers: {
		"Authorization": "Bearer " + jwt
	}
}

// Create Event
async function createAddress(config) {
	const data = {
		cep: "97017580",
		street: "rua do miguel",
		streetNumber: "140",
		complement: "salão b",
		description: "casa do miguel",
		city: {id: 1}
	}
	return await axios.post("http://localhost:8080/api/address", data, config)
		.then(response => { return response.data })
		.catch(_ => console.log("Erro ao criar o endereço")
		);
}
async function createEvent(url, config, addressId) {
	const data = {
		name: "Show do LFerreira",
		initialDate: "2024-11-24",
		finalDate: "2024-11-26",
		description: "Show do mestre LFerreira",
		eventImage: "",
		eventStatus: "PENDING",
		eventAddress: addressId
	}
	return await axios.post(url, data, config)
		.then(response => { console.log("Evento criado!"); return response.data; })
		.catch(e => { return e; }
		);
}
// Read All Events
async function readAllEvents(url, config) {
	return await axios.get(url, config)
		.then(response => { return response.data; })
		.catch(_ => { console.log("Erro ao ler todos!"); }
		);
}
// Read Event By Id
async function readEventById(url, config, id) {
	return await axios.get(url + "/" + id, config)
		.then(response => { return response.data; })
		.catch(_ => { console.log("Erro ao ler por id!"); }
		);
}
// Update Event
async function updateEvent(url, config, id, addressId) {
	const data = {
		name: "Show do LFerreira",
		initialDate: "2024-11-24",
		finalDate: "2024-11-26",
		description: "Show do mestre LFerreira",
		eventImage: "",
		eventStatus: "ACTIVE",
		eventAddress: addressId
	}
	return await axios.put(url + "/" + id, data, config)
		.then(response => { return response.data; })
		.catch(_ => { console.log("Erro ao atualizar!"); }
		);
}
// Delete Event
async function deleteEvent(url, config, id) {
	return await axios.delete(url + "/" + id, config)
		.then(response => { return response.data; })
		.catch(_ => { console.log("Erro ao deletar!"); }
		);
}

const address = await createAddress(config);
const evento = await createEvent(url, config, address.id);
const id = evento.id;
console.log(evento);
console.log(await readAllEvents(url, config));
console.log(await readEventById(url, config, id));
console.log(await updateEvent(url, config, id, address.id));
console.log(await deleteEvent(url, config, id));
