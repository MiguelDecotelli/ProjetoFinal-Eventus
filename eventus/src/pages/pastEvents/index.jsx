import ImgCard from "../../img/banner1.jpg";
import { Navbar } from "../../components/Navbar";
import { useParams } from "react-router-dom";
import { useContext } from 'react';
import { DataContext } from '../../context/DataContext';
import { Footer } from "../../components/Footer";

export const PastEventDetails = () => {
  const { id } = useParams();
  const { eventos } = useContext(DataContext);

  const evento = eventos.find(evento => evento.id === String(id));
  if (!evento) {
    return <div>Evento não encontrado.</div>;
  }

  return (
    <div>
      <Navbar />
      <div className="eventDetails">
        <section className="eventSection">
          <div>
            <h3 className="eventTitle">{evento.title}</h3>
            <p className="eventDescription">{evento.description}</p>
            <p className="eventDescription">{evento.date}</p>
          </div>
          <div>
            <img
              src={evento.image || ImgCard}
              className="eventImage"
              alt="Imagem do evento"
            />
          </div>
        </section>
      </div>
      <Footer />
    </div>
  );
};
