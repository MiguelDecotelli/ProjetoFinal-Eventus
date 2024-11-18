import ImgCard from "../../img/banner1.jpg";
import { Navbar } from "../../components/Navbar";
import { useParams } from "react-router-dom";

import { useContext } from 'react';
import { DataContext } from '../../context/DataContext';
import { Footer } from "../../components/Footer";

export const EventDetail = () => {
  const { id } = useParams();  // Pega o parâmetro "id" da URL
  const { eventos } = useContext(DataContext);

  
  console.log(id);

  // Encontrar o evento com o id correspondente
  const evento = eventos.find(evento => evento.id === Number(id));
  console.log(evento);

  // Verificar se o evento foi encontrado
  if (!evento) {
    return <div>Evento não encontrado.</div>;
  }

  return (
    <div className="container-events">
      <Navbar />  
      <h2 className="mt-4 fs-1">Detalhes do Evento</h2>
      <section className="d-flex gap-5 mt-5">
        <div>
          <h3 className="fs-1 fw-bold">{evento.title}</h3>
          {/* <p>ID:{evento.id}</p> */}
          <p className="pt-4 fs-4">{evento.body}</p>
        </div>
        <div>
          <img src={evento.imagem || ImgCard} className="card-img-top w-75" alt="Card image" />
          <div className="d-flex justify-content-center mt-5">
            <a href="#" className="btn btn-orange mx-auto w-50">
              <p className="fs-3 fw-bold pt-3">Comprar Ticket</p>
            </a>
          </div>
        </div>
      </section>
      <Footer />
    </div>
  )
}

// export default EventDetail