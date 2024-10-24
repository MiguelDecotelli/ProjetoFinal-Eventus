import "./CardEvento.css";
import ImgCard from "../img/banner1.jpg";

export const CardEvento = ({ id, title, description }) => {

  return (
    <div className='card'>
      <img src={ImgCard} className="card-img-top" alt="Card image" />
      <h2>Card: {id}</h2>
      <h4>Título: {title}</h4>
      <p>Notícia: {description}</p>

      <div className="d-flex justify-content-center">
					<a href="#" className="btn btn-orange mx-auto">
						Go somewhere
					</a>
			</div>
    </div>
  )
}

// export default CardEvento;