import { createContext, useState, useEffect } from "react";
import { makeRequest } from "../utils/makeRequest.js";

export const DataContext = createContext();

export const DataProvider = ({ children }) => {
	const [eventos, setEventos] = useState([]);

	const url = "http://localhost:8080/api/events";

	// Função para buscar os eventos
	const fetchEventos = async () => {
		try {
			const response = await fetch(url);
			const data = await response.json();
			console.log(data);
			setEventos(data);
		} catch (error) {
			console.error("Erro ao buscar eventos:", error);
		}
	};

	useEffect(() => {
		fetchEventos();
	}, []);

	return (
		<DataContext.Provider value={{ eventos }}>
			{children}
		</DataContext.Provider>
	)
};
