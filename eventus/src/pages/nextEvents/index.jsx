import { useState, useEffect } from 'react';
import { CardEvent } from '../../components/CardEvent';
import { Navbar } from '../../components/Navbar';
import { DragDropContext, Droppable, Draggable } from '@hello-pangea/dnd';
import { FaChevronLeft, FaChevronRight } from "react-icons/fa6";

import { useContext } from 'react';
import { DataContext } from '../../context/DataContext';
import { Footer } from '../../components/Footer';
export const NextEvents = () => {
  const { eventos } = useContext(DataContext);

  // Função para filtrar eventos futuros
  const eventosFuturos = eventos.filter((evento) => {
    const eventDate = new Date(evento.date);
    const today = new Date();
    return eventDate > today;
  });

  const [search, setSearch] = useState('');
  const [displayedEvents, setDisplayedEvents] = useState(eventosFuturos); // Usa eventosFuturos aqui

  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage] = useState(6);

  useEffect(() => {
    const filteredEvents = eventosFuturos.filter((evento) =>
      evento.title.toLowerCase().includes(search.toLowerCase()) ||
      evento.id.toString().includes(search)
    );
    setDisplayedEvents(filteredEvents);
  }, [search, eventosFuturos]);

  const paginate = (events, pageNumber) => {
    const startIndexPage = (pageNumber - 1) * itemsPerPage;
    const endIndexPage = startIndexPage + itemsPerPage;
    return events.slice(startIndexPage, endIndexPage);
  };

  const reorder = (list, startIndex, endIndex) => {
    const result = Array.from(list);
    const [removed] = result.splice(startIndex, 1);
    result.splice(endIndex, 0, removed);
    return result;
  };

  const onDragEnd = (result) => {
    if (!result.destination) return;

    const reordered = reorder(displayedEvents, result.source.index, result.destination.index);
    setDisplayedEvents(reordered);
  };

  const totalPages = Math.ceil(displayedEvents.length / itemsPerPage);

  const currentEvents = paginate(displayedEvents, currentPage);

  return (
    <div>
      <div className="nextEvents">
        <Navbar />
        <h1 className="nextEvents-Title">Próximos Eventos</h1>
        <div className="search">
          <label className="search-label">
            <input
              type="search"
              placeholder="Filtrar Evento"
              value={search}
              className="search-input"
              onChange={(e) => setSearch(e.target.value)}
            />
          </label>
        </div>

        {eventosFuturos.length > 0 && ( // Usa eventosFuturos aqui também
          <section>
            <DragDropContext onDragEnd={onDragEnd}>
              <Droppable droppableId="eventos" type="list" direction="horizontal">
                {(provided) => (
                  <ul
                    className="list-cards d-flex justify-content-around flex-wrap gap-5 p-5 rounded-4"
                    {...provided.droppableProps}
                    ref={provided.innerRef}
                  >
                    {currentEvents.map((evento, index) => (
                      <Draggable key={evento.id} draggableId={evento.id.toString()} index={index}>
                        {(provided) => (
                          <li
                            ref={provided.innerRef}
                            {...provided.draggableProps}
                            {...provided.dragHandleProps}
                          >
                            <CardEvent evento={evento} />
                          </li>
                        )}
                      </Draggable>
                    ))}
                    {provided.placeholder}
                  </ul>
                )}
              </Droppable>
            </DragDropContext>
          </section>
        )}

        <div className="pagination d-flex justify-content-center align-items-center mt-4">
          <button
            onClick={() => setCurrentPage(prev => Math.max(prev - 1, 1))}
            disabled={currentPage === 1}
          >
            <FaChevronLeft />
          </button>
          <span>{`Página ${currentPage} de ${totalPages}`}</span>
          <button
            onClick={() => setCurrentPage(prev => Math.min(prev + 1, totalPages))}
            disabled={currentPage === totalPages}
          >
            <FaChevronRight />
          </button>
        </div>
      </div>
      <Footer />
    </div>
  );
};
