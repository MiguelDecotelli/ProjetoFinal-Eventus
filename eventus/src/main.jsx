import { StrictMode } from "react";
import { createRoot } from "react-dom/client";

import MainRoutes from "./MainRoutes.jsx";
import "./global.css";

createRoot(document.getElementById("root")).render(
	<StrictMode>
		<MainRoutes />
	</StrictMode>
);
