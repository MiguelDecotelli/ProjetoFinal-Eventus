import { Navbar } from "../../components/Navbar";
import { Footer } from "../../components/Footer";
import { useState, useEffect } from "react";
import { Link } from "react-router-dom";

export const News = () => {
    const [news, setNews] = useState([]);

    useEffect(() => {
        // Lista de eventos com ID, imagem, título, descrição e data
        const sampleNews = [
            {
                id: 1,
                title: "Estreia: 'Avatar 3'",
                description: "Confira a tão aguardada continuação da saga Avatar nos cinemas.",
                date: "2024-12-18",
                image: "https://via.placeholder.com/800x400?text=Avatar+3",
            },
            {
                id: 2,
                title: "Rock in Rio 2025",
                description: "Garanta seus ingressos para o maior festival de música do mundo!",
                date: "2025-09-13",
                image: "https://via.placeholder.com/800x400?text=Rock+in+Rio",
            },
            {
                id: 3,
                title: "Lollapalooza Brasil",
                description: "O festival que une música e cultura retorna com tudo em 2025.",
                date: "2025-03-22",
                image: "https://via.placeholder.com/800x400?text=Lollapalooza",
            },
            {
                id: 4,
                title: "Natal Luz de Gramado",
                description: "Experimente a magia do Natal com shows e decorações incríveis.",
                date: "2024-11-25",
                image: "https://via.placeholder.com/800x400?text=Natal+Luz",
            },
            {
                id: 5,
                title: "ExpoGramado 2025",
                description: "Venha conferir o maior evento de exposições de Gramado.",
                date: "2025-06-15",
                image: "https://via.placeholder.com/800x400?text=ExpoGramado",
            },
            {
                id: 6,
                title: "Comédia Stand-Up: Thiago Ventura",
                description: "Ria com o melhor do stand-up comedy nacional.",
                date: "2024-12-02",
                image: "https://via.placeholder.com/800x400?text=Thiago+Ventura",
            },
            {
                id: 7,
                title: "Feira do Livro de Porto Alegre",
                description: "Descubra novos autores e mergulhe no mundo da literatura.",
                date: "2025-04-10",
                image: "https://via.placeholder.com/800x400?text=Feira+do+Livro",
            },
            {
                id: 8,
                title: "Exposição Van Gogh Alive",
                description: "Uma imersão única na arte do pintor Van Gogh.",
                date: "2024-12-10",
                image: "https://via.placeholder.com/800x400?text=Van+Gogh+Alive",
            },
            {
                id: 9,
                title: "Festival de Cinema de Gramado",
                description: "Premiação dos melhores filmes nacionais e internacionais.",
                date: "2025-08-17",
                image: "https://via.placeholder.com/800x400?text=Festival+de+Cinema",
            },
            {
                id: 10,
                title: "Show da Banda Coldplay",
                description: "Assista ao show inesquecível da banda Coldplay ao vivo.",
                date: "2024-12-05",
                image: "https://via.placeholder.com/800x400?text=Coldplay",
            },
        ];
        setNews(sampleNews);
    }, []);

    return (
        <div className="container-carousel">
            <Navbar />
            <main className="d-flex flex-column gap-4">
                <section className="custom-gradient p-4">
                    <h1 className="text-center" style={{ color: "var(--text)" }}>
                        News
                    </h1>
                    <p className="text-center" style={{ color: "var(--text-placeholder)" }}>
                        Stay up-to-date with the latest news and events!
                    </p>
                </section>
                <section className="d-flex flex-column align-items-center gap-3">
                    {news.length > 0 ? (
                        news.map((item) => (
                            <Link
                                to={`/eventDetails/${item.id}`}
                                key={item.id}
                                className="custom-card p-3"
                                style={{
                                    maxWidth: "800px",
                                    borderRadius: "8px",
                                    boxShadow: "0 4px 8px rgba(0, 0, 0, 0.2)",
                                    textDecoration: "none",
                                }}
                            >
                                <img
                                    src={item.image}
                                    alt={item.title}
                                    style={{
                                        width: "100%",
                                        height: "200px",
                                        objectFit: "cover",
                                        borderRadius: "8px",
                                    }}
                                />
                                <h2 style={{ color: "var(--accent)" }}>{item.title}</h2>
                                <p style={{ color: "var(--text-placeholder)" }}>{item.description}</p>
                                <small style={{ color: "var(--primary)" }}>Date: {item.date}</small>
                            </Link>
                        ))
                    ) : (
                        <p style={{ color: "var(--text)" }}>Loading news...</p>
                    )}
                </section>
            </main>
            <Footer />
        </div>
    );
};
