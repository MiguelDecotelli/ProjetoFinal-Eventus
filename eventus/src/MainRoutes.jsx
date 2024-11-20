import { DataProvider } from './context/DataContext';
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import { Home } from "./pages/home";
import { SignUp } from "./pages/signup";
import { Login } from "./pages/login";
import { Contact } from "./pages/contact";
import { Support } from "./pages/support";
import { UserProvider } from "./context/UserContext";
import { NextEvents } from "./pages/nextEvents";
import { EventDetail } from "./pages/eventDetail";

function MainRoutes() {
	return (
		<DataProvider>
			<Router>
				<UserProvider>
					<Routes>
						<Route path="/" element={<Home />} />
						<Route path="/signup" element={<SignUp />} />
						<Route path="/login" element={<Login />} />
						<Route path="/contact" element={<Contact />} />
						<Route path="/support" element={<Support />} />
						<Route path="/nextEvents" element={<NextEvents />} />
						<Route path="/eventDetail/:id" element={<EventDetail />} />

						{/* BASE ROTA NÃO ENCONTRADA */}
						<Route path="*" element={<h1>Página não encontrada (404)</h1>} />
					</Routes>
				</UserProvider>
			</Router>
		</DataProvider>
	);
}

export default MainRoutes;
