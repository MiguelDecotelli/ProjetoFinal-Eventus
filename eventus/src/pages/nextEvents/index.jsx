import { useState, useEffect } from 'react';
import { CardEvento } from '../../components/CardEvento';
import { Navbar } from '../../components/Navbar';
import { DragDropContext, Droppable, Draggable } from '@hello-pangea/dnd';
import { FaChevronLeft, FaChevronRight  } from "react-icons/fa6";

import { useContext } from 'react';
import { DataContext } from '../../context/DataContext';

export const NextEvents = () => {
  //States do Projeto
  const { eventos } = useContext(DataContext);

  // Search Event
  const [search, setSearch] = useState(''); // Estado para a busca
  const [displayedEvents, setDisplayedEvents] = useState(eventos); // Lista de eventos filtrados

  // Estados de paginação
  const [currentPage, setCurrentPage] = useState(1); // Página atual
  const [itemsPerPage] = useState(6); // Quantidade de itens por página

  // Efeito para filtrar os eventos sempre que o valor de search mudar
  useEffect(() => {
    const filteredEvents = eventos.filter((evento) =>
      evento.title.toLowerCase().includes(search.toLowerCase()) ||
      evento.id.toString().includes(search)
    );
    setDisplayedEvents(filteredEvents); // Atualiza os eventos filtrados
  }, [search, eventos]); // busca e lista de eventos

  // Função para paginar os eventos
  const paginate = (events, pageNumber) => {
    const startIndexPage = (pageNumber - 1) * itemsPerPage; // Índice inicial
    const endIndexPage = startIndexPage + itemsPerPage; // Índice final
    return events.slice(startIndexPage, endIndexPage); // Retorna os eventos da página atual
  };

  // Função de reordenação da lista de eventos
  const reorder = (list, startIndex, endIndex) => {
    const result = Array.from(list);
    const [removed] = result.splice(startIndex, 1); // Remove o item da posição inicial
    result.splice(endIndex, 0, removed); // Adiciona na nova posição
    return result;
  };

  // On Drag End:
  // Função chamada quando o arrastar e soltar termina
  const onDragEnd = (result) => {
    if (!result.destination) return; // Se o item não for solto em um local válido, retorna

    // Reordena a lista de eventos
    const reordered = reorder(displayedEvents, result.source.index, result.destination.index);
    setDisplayedEvents(reordered); // Atualiza o estado com a nova ordem
  };

  // Calcula o número total de páginas
  const totalPages = Math.ceil(displayedEvents.length / itemsPerPage);

  // Página atual dos eventos
  const currentEvents = paginate(displayedEvents, currentPage);

  return (
    <div className='container-events'>
			<Navbar />
      <h1 className='mt-4 mb-4'>Todos os Eventos da Cidade</h1>
      <div className='search'>
        <label className='d-flex flex-column align-items-center gap-3 m-4'>
          <span className='fs-4 fw-bold'>Buscar por Eventos:</span>
          <input 
            type="search" 
            placeholder='Buscar Evento'
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </label>
      </div>
      {eventos.length > 0 && ( // Só renderiza o DragDropContext se os eventos não estiverem vazios
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
                          <CardEvento evento={evento} />
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

      {/* Controles de paginação */}
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
  );
};