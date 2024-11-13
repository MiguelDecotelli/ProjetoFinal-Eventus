import ImgCard from "../img/banner1.jpg";

export const CardEvento = ({ evento }) => {

  return (
    <div className='card d-flex flex-column overflow-auto gap-3 p-3 rounded-2'>
      <img src={ImgCard} className="card-img-top" alt="Card image" />
      <h2>Card: {evento.id}</h2>
      <h4>Título: {evento.title}</h4>
      <p>Notícia: {evento.body}</p>

      <div className="d-flex justify-content-center">
        <a href="#" className="btn btn-orange mx-auto">
          Go somewhere
        </a>
      </div>
    </div>
  )
}