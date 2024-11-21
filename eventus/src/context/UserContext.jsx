/* eslint-disable react-refresh/only-export-components */
/* eslint-disable react/prop-types */
import { createContext, useContext, useEffect, useState } from "react";
import { makeRequest } from "../utils/makeRequest";

export const UserContext = createContext();

export const UserProvider = ({ children }) => {
	const [userLogged, setUserLogged] = useState(null);
	const [user, setUser] = useState(undefined);
	const [token, setToken] = useState("");

	useEffect(() => {
		const storedToken = localStorage.getItem("token");
		const storedUser = JSON.parse(localStorage.getItem("user"));

		if (storedToken) {
			setToken(storedToken);
		}

		if (storedUser) {
			setUser(storedUser);
		}
		getUser();
	}, []);

	const getUser = async () => {
		try {
			const user = JSON.parse(localStorage.getItem("user"));
			if(user){setUserLogged(user)};
		} catch (error) {
			console.error("Erro ao buscar os dados do usuário:", error);
		}
	};

	function setUserAndStore(user) {
		localStorage.setItem("user", JSON.stringify(user));
		setUser(user);
	}

	function logout() {
		localStorage.removeItem("token");
		localStorage.removeItem("user");
		setToken("");
		setUser(undefined);
		setUserLogged(null);
	}

	const values = {
		userLogged,
		user,
		token,
		logout,
		setUser: setUserAndStore,
	};

	return <UserContext.Provider value={values}>{children}</UserContext.Provider>;
};

export function useUser() {
	return useContext(UserContext);
}
