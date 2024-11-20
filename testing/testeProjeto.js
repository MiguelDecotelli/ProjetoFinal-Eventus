import axios from "axios";

axios.post("http://localhost:8080/api/auth/register", { username: "Logan", password: "1234", email: "wolverine@xmen.com", name: "James", lastname: "Howlett", birthday: "1958-07-20" }).then(response => console.log(response))