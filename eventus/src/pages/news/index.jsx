import { useState, useContext } from "react";
import { DataContext } from "../../context/DataContext";
import { Navbar } from "../../components/Navbar";
import { Footer } from "../../components/Footer";
import { Link } from "react-router-dom";
import { FaChevronLeft, FaChevronRight } from "react-icons/fa6";

export const News = () => {
    const { eventos } = useContext(DataContext);

    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 10; // Quantidade de eventos por página

    // Função de paginação
    const paginate = (events, pageNumber) => {
        const startIndex = (pageNumber - 1) * itemsPerPage;
        const endIndex = startIndex + itemsPerPage;
        return events.slice(startIndex, endIndex);
    };

    const totalPages = Math.ceil(eventos.length / itemsPerPage);
    const currentEvents = paginate(eventos, currentPage);

    return (
        <div className="container-carousel">
            <Navbar />
            <main className="news-main">
                <section className="custom-gradient p-4">
                    <h1 className="news-title">News</h1>
                    <p className="news-subtitle">Fique por dentro das Novidades</p>
                </section>
                <section className="news-list">
                    {currentEvents.length > 0 ? (
                        currentEvents.map((evento) => (
                            <Link
                                to={`/eventDetails/${evento.id}`}
                                key={evento.id}
                                className="custom-news-card"
                            >
                                <img
                                    src={evento.image}
                                    alt={evento.title}
                                    className="news-image"
                                />
                                <h2 className="news-item-title">{evento.title}</h2>
                                <p className="news-description">{evento.description}</p>
                                <small className="news-date">Date: {evento.date}</small>
                            </Link>
                        ))
                    ) : (
                        <p className="news-loading">Loading news...</p>
                    )}
                </section>
                <div className="pagination d-flex justify-content-center align-items-center mt-4">
                    <button
                        onClick={() => setCurrentPage((prev) => Math.max(prev - 1, 1))}
                        disabled={currentPage === 1}
                    >
                        <FaChevronLeft />
                    </button>
                    <span>{`Página ${currentPage} de ${totalPages}`}</span>
                    <button
                        onClick={() => setCurrentPage((prev) => Math.min(prev + 1, totalPages))}
                        disabled={currentPage === totalPages}
                    >
                        <FaChevronRight />
                    </button>
                </div>
            </main>
            <Footer />
        </div>
    );
};
