import ImgCard from "../img/banner1.jpg";
import { Link } from "react-router-dom";

export const CardEvento = ({ evento }) => {

  return (
    <div className='card d-flex flex-column overflow-auto gap-3 p-3 rounded-2'>
      <img src={ImgCard} className="card-img-top" alt="Card image" />
      <h2>ID: {evento.id}</h2>
      <h4>Título: {evento.title}</h4>
      <p>Detalhes: {evento.body}</p>

      <div className="d-flex justify-content-center">
        <Link className="btn btn-orange d-flex justify-content-center" to={`/eventDetail/${evento.id}`}>
					Ver Detalhes
				</Link>
      </div>
    </div>
  )
}