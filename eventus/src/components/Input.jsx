/* eslint-disable react/display-name */
/* eslint-disable react/prop-types */
import { forwardRef } from "react";

const inputCSS =
	"form-control text-start border-0 bg-transparent custom-input px-0 pt-4 pb-0 shadow-none";

export const Input = forwardRef(
	({ label, type = "text", id, placeholder, error, ...rest }, ref) => {
		return (
			<>
				<div className="input-group d-flex flex-nowrap border-bottom">
					<div className="form-floating">
						<input
							type={type}
							className={inputCSS}
							id={id}
							placeholder={placeholder}
							ref={ref}
							{...rest}
						/>
						<label
							className="no-after bg-transparent"
							style={{color: "var(--text)"}}
							htmlFor={id}
						>
							{label}
						</label>
					</div>
				</div>

				<p
					className={`alert text-danger p-0 m-0 error-message ${error ? "visible" : "invisible"
						}`}
				>
					{error}
				</p>
			</>
		);
	}
);
