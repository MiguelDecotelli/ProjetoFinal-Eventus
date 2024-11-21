import { Navbar } from "../../components/Navbar";
import { Footer } from "../../components/Footer";
import { useContext } from "react";
import { DataContext } from "../../context/DataContext";
import { Link } from "react-router-dom";


export const History = () => {
  const { eventos } = useContext(DataContext);

  const eventosPassados = eventos.filter((evento) => {
    const eventDate = new Date(evento.date);
    const today = new Date();
    return eventDate < today;
  });

  return (
    <div>
      <Navbar />
      <div className="eventHistory">
        <div className="custom-gradient p-4">
          <h2 className="historyTitle">Histórico de Eventos</h2>
        </div>
        {eventosPassados.length === 0 ? (
          <p className="noEventsMessage">Nenhum evento passado disponível.</p>
        ) : (
          <div className="historySections">
            {eventosPassados.map((evento, index) => (
              <section className="historySection">
                <Link
                  to={`/pastEventDetails/${evento.id}`}
                  key={index}
                  style={{ textDecoration: 'none', color: 'inherit' }}
                >
                  <div key={index}>
                    <h3 className="eventTitle">{evento.title}</h3>
                    <p className="eventDescription">{evento.description}</p>
                    <p className="eventDate">{evento.date}</p>
                  </div>
                </Link>

              </section>
            ))}
          </div>
        )}
      </div>
      <Footer />
    </div >
  );
};


