import api from "../services/api";

export async function makeRequest(
	url,
	method,
	data = undefined,
	token = undefined
) {
	try {
		const headers = {
			"Content-Type": "application/json",
			...(token ? { Authorization: `Bearer ${token}` } : {}),
		};

		const response = await api({
			url,
			method,
			headers,
			data,
		});

		return response.data;
	} catch (error) {
		alert("Ocorreu um erro, tente mais tarde!");
		console.error(error);
	}
}
