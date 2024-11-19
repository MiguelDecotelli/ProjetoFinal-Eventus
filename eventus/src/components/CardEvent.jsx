import ImgCard from "../img/banner1.jpg";
import { Link } from "react-router-dom";

export const CardEvent = ({ evento }) => {

  return (
    <div className="card">
      <img src={ImgCard} className="card-img-top card-img-custom" alt="Card image" />
      <div className="card-body d-flex flex-column">
        <h4 className="card-title">{evento.title}</h4>
        <p className="card-text">{evento.body}</p>
        <div className="mt-auto d-flex justify-content-center">
          <Link className="btn btn-orange mx-auto" to={`/eventDetails/${evento.id}`}>
            Detalhes
          </Link>
        </div>
      </div>
    </div>
  )
}