/* eslint-disable react/display-name */
/* eslint-disable react/prop-types */
import { forwardRef } from "react";

const inputCSS =
	"form-control border-0 bg-transparent custom-input px-0 pt-2 pb-0 shadow-none";

export const Input = forwardRef(
	({ label, type = "text", id, placeholder, error, ...rest }, ref) => {
		return (
			<>
				<div className="input-group d-flex flex-nowrap border-bottom">
					<label
						className="input-group-text border-0 bg-transparent text p-0"
						htmlFor={id}
					>
						{label}
					</label>
					<input
						type={type}
						className={inputCSS}
						id={id}
						placeholder={placeholder}
						ref={ref}
						{...rest}
					/>
				</div>

				<p
					className={`alert text-danger p-0 m-0 error-message ${
						error ? "visible" : "invisible"
					}`}
				>
					{error}
				</p>
			</>
		);
	}
);
