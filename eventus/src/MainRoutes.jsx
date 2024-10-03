import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

import { Home } from "./pages/home";
import { SignUp } from "./pages/signup";
import { Login } from "./pages/login";
import { Contact } from "./pages/contact";
import { Support } from "./pages/support";

function MainRoutes() {
	return (
		<Router>
			<Routes>
				<Route path="/" element={<Home />} />

				<Route path="/signup" element={<SignUp />} />
				<Route path="/login" element={<Login />} />

				<Route path="/contact" element={<Contact />} />
				<Route path="/support" element={<Support />} />

				{/* BASE ROTA NÃO ENCONTRADA */}
				<Route path="*" element={<h1>Página não encontrada (404)</h1>} />
			</Routes>
		</Router>
	);
}

export default MainRoutes;
