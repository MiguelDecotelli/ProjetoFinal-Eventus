import login from "./admin.js";
import axios from "axios";

const jwt = await login();
const ticketUrl = "http://localhost:8080/api/tickets";
const config = {
	headers: {
		"Authorization": "Bearer " + jwt
	}
}

// Setup
async function createAddress(config) {
	const data = {
		cep: "97017580",
		street: "rua do miguel",
		streetNumber: "140",
		complement: "salão b",
		description: "casa do miguel",
		city: { id: 1 }
	}
	return await axios.post("http://localhost:8080/api/address", data, config)
		.then(response => { console.log("Endereço criado"); return response.data })
		.catch(_ => console.log("Erro ao criar o endereço")
		);
}

async function createEvent(config, addressId) {
	const data = {
		name: "Show do LFerreira",
		initialDate: "2024-11-24",
		finalDate: "2024-11-26",
		description: "Show do mestre LFerreira",
		eventImage: "",
		eventStatus: "PENDING",
		eventAddress: addressId
	}
	return await axios.post("http://localhost:8080/api/events", data, config)
		.then(response => { console.log("Evento criado!"); return response.data; })
		.catch(e => { console.log("Não foi possivel criar o evento"); }
		);
}

async function createTicket(config, eventId) {
	const data = {
		name: "Ingresso VIP",
		description: "Melhor ingresso para balado do LFerreira",
		amount: 30,
		event: eventId
	}
	return await axios.post("http://localhost:8080/api/tickets", data, config)
		.then(response => { console.log("Ticket criado!"); return response.data; })
		.catch(e => { console.log("não foi possivel criar o ticket") }
		);
}
async function createUser() {
	const userDetails = {
		"username": "vladimir1234",
		"password": "1234",
		"email": "vlad@transilvania.com",
		"name": "vladimir",
		"lastname": "putin",
		"birthday": "1000-01-01"
	}
	return await axios.post("http://localhost:8080/api/auth/register", userDetails)
		.then((response) => { console.log("usuario criado"); return response.data; })
		.catch(e => { return e });
}
async function deleteTicket(id, config) {
	return await axios.delete("http://localhost:8080/api/tickets/" + id, config)
		.then((response) => { return response; })
		.catch(_ => { console.log("Não foi possivel deletar o ticket") }
		);
}
async function deleteEvents(id, config) {
	return await axios.delete("http://localhost:8080/api/events/" + id, config)
		.then((response) => { return response; })
		.catch(_ => { console.log("Não foi possivel deletar o evento") }
		);
}
async function deleteAddress(id, config) {
	return await axios.delete("http://localhost:8080/api/address/" + id, config)
		.then((response) => { return response; })
		.catch(_ => { console.log("Não foi possivel deletar o endereço") }
		);
}
async function deleteUser(id, config) {
	return await axios.delete("http://localhost:8080/api/users/" + id, config)
		.then((response) => { return response; })
		.catch(_ => { console.log("Não foi possivel deletar o usuario") }
		);
}
// Create Relation
async function createRelation(config, userId, ticketId) {
	return await axios.post("http://localhost:8080/api/users/" + userId + "/tickets/" + ticketId, config)
		.then(response => { return response; })
		.catch(e => {console.log(e); console.log("Não foi possivel criar a relação")});
}
async function getAllUserRelation(config, userId) {
	return await axios.get("http://localhost:8080/api/users/" + userId + "/tickets", config)
		.then(response => { return response; })
		.catch(e =>{ console.log(e); console.log("Não foi possivel recuperar as relações do usuario")});
}
async function getAllTicketRelation(config, ticketId) {
	return await axios.get("http://localhost:8080/api/tickets" + ticketId + "/users", config)
		.then(response => { return response; })
		.catch(_ => console.log("Não foi possivel recuperar as relações do ticket"));
}
async function deleteRelation(config, userId, ticketId) {
	return await axios.delete("http://localhost:8080/api/users/" + userId + "/tickets/" + ticketId, config)
		.then(response => { return response; })
		.catch(_ => console.log("Não foi possivel criar a relação"));
}

async function main(config) {
	const address = await createAddress(config);
	const evento = await createEvent(config, address.id);
	const ticket = await createTicket(config, evento.id);
	const usuario = await createUser();

	console.log(await createRelation(config, usuario.id, ticket.id));
	console.log(await getAllUserRelation(config, usuario.id));
	console.log(await getAllTicketRelation(config, ticket.id));
	console.log(await deleteRelation(config, usuario.id, ticket.id));


	deleteUser(usuario.id, config);
	deleteTicket(ticket.id, config);
	deleteEvents(evento.id, config);
	deleteAddress(address.id, config);
}

main(config);
