import login from "./admin.js";
import axios from "axios";

const jwt = await login();
const url = "http://localhost:8080/api/tickets";
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
		.catch(e => { return e; }
	);
}

// Create Ticket
async function createTicket(url, config, eventId){
	const data ={
		name: "Ingresso VIP",
		description: "Melhor ingresso para balado do LFerreira",
		amount: 30,
		event: eventId
	}
	return await axios.post(url, data, config)
		.then(response=>{console.log("Ticket criado!"); return response.data;})
		.catch(e=>{return e;}
	);
}
// Read All Tickets
async function readAllTickets(url, config){
	return await axios.get(url, config)
		.then(response=>{return response.data;})
		.catch(_=>{console.log("Erro ao ler todos!");}
	);
}
// Read Ticket By Id
async function readTicketById(url, config, id){
	return await axios.get(url+"/"+id, config)
		.then(response=>{return response.data;})
		.catch(_=>{console.log("Erro ao ler por id!");}
	);
}
// Update Ticket
async function updateTicket(url, config, id, eventId){
	const data = {
		name: "Ingresso VIP",
		description: "Melhor ingresso para balado do LFerreira",
		amount: 40,
		event: eventId
	}
	return await axios.put(url+"/"+id, data, config)
		.then(response=>{ return response.data;})
		.catch(_=>{console.log("Erro ao atualizar!");}
	);
}
// Delete Ticket
async function deleteTicket(url, config, id){
	return await axios.delete(url+"/"+id, config)
		.then(response=>{return response.data;})
		.catch(_=>{console.log("Erro ao deletar!");}
	);
}

const address = await createAddress(config);
const evento = await createEvent(config, address.id);
const ticket = await createTicket(url, config, evento.id);
const id = ticket.id;
console.log(ticket);
console.log(await readAllTickets(url, config));
console.log(await readTicketById(url, config, id));
console.log(await updateTicket(url, config, id, evento.id));
console.log(await deleteTicket(url, config, id));
console.log(await deleteTicket("http://localhost:/api/address", config, address.id));
console.log(await deleteTicket("http://localhost:/api/events", config, evento.id));
