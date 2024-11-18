import ImgCard from "../../img/banner1.jpg";
import { Navbar } from "../../components/Navbar";
import { useParams } from "react-router-dom";


export const EventDetail = () => {
  const { id } = useParams();  // Pega o parâmetro "id" da URL

  return (
    <div className="container-events">
      <Navbar />  
      <section className="d-flex gap-5 mt-5">
        <div>
          <h2>Título: SAUHSUAHSUAHSHASSHSAUSHA</h2>
          <p>ID:{id}</p>
          <p>descrição: aushuahusasuahuashasuhasuhasuhuhuhauashs
            auhsuashuashusahusahashashuashusahuashsahuashahuahss
            aushuahusasuahuashasuhasuhasuhuhuhauashs
            auhsuashuashusahusahashashuashusahuashsahuashahuahss
            aushuahusasuahuashasuhasuhasuhuhuhauashs
            auhsuashuashusahusahashashuashusahuashsahuashahuahss
            aushuahusasuahuashasuhasuhasuhuhuhauashs
            auhsuashuashusahusahashashuashusahuashsahuashahuahss</p>    {/* detalhes do evento*/}
        </div>
        <div>
          <img src={ImgCard} className="card-img-top w-75" alt="Card image" />
          <div className="d-flex justify-content-center mt-5">
            <a href="#" className="btn btn-orange mx-auto w-50">
              <p className="fs-3 fw-bold pt-3">Comprar Ticket</p>
            </a>
          </div>
        </div>
      </section>
    </div>
  )
}

// export default EventDetail