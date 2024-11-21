import ImgCard from "../img/banner1.jpg";
import { Link } from "react-router-dom";

export const CardEvento = ({ evento }) => {

  return (
    <div className='card d-flex flex-column overflow-auto gap-3 p-3 rounded-2'>
      <img src={evento.image} className="card-img-top" alt="Not Found" />
      <h2>{evento.title}</h2>
      <p>D{evento.body}</p>

      <div className="d-flex justify-content-center">
        <Link className="btn btn-orange d-flex justify-content-center" to={`/eventDetail/${evento.id}`}>
					Ver Detalhes
				</Link>
      </div>
    </div>
  )
}
