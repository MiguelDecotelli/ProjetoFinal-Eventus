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
		const storedUser = localStorage.getItem("user");

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
			const usersData = await makeRequest("/users", "GET");
			const username = localStorage.getItem("user");

			const filteredUser = usersData.find((user) => user.username === username);

			if (filteredUser) {
				setUserLogged(filteredUser);
			}
		} catch (error) {
			console.error("Erro ao buscar os dados do usuário:", error);
		}
	};

	function setUserAndStore(user) {
		localStorage.setItem("user", user);
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
