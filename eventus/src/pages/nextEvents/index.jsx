import { useState, useEffect } from 'react';
import { CardEvento } from '../../components/CardEvento';
import { Navbar } from '../../components/Navbar';
import { DragDropContext, Droppable, Draggable } from '@hello-pangea/dnd';
import { FaChevronLeft, FaChevronRight  } from "react-icons/fa6";

import './App.css';

const url = "https://jsonplaceholder.typicode.com/posts/";

export const NextEvents = () => {
  //States do Projeto
  const [eventos, setEventos] = useState([]); // Estado para armazenar os eventos

  // Search Event
  const [search, setSearch] = useState(''); // Estado para a busca
  const [displayedEvents, setDisplayedEvents] = useState([]); // Lista de eventos filtrados
  const [loading, setLoading] = useState(true); // Estado de carregamento

  // Estados de paginação
  const [currentPage, setCurrentPage] = useState(1); // Página atual
  const [itemsPerPage] = useState(6); // Quantidade de itens por página
  
// #######################################################################################
  
  // Fazer a requisição para a API quando o componente for montado
  useEffect(() => {
    const fetchEventos = async () => {
      try {
        const response = await fetch(url);
        const data = await response.json(); // Converte a resposta em JSON
        setEventos(data); // Atualiza o estado com os dados da API
        setDisplayedEvents(data); // Inicializa a lista filtrada com todos os eventos
      } catch (error) {
        console.error("Erro ao buscar eventos: ", error);
      } finally {
        setLoading(false); // Marca como carregado
      }
    };  
    fetchEventos(); // Chama a função de requisição
  }, []);
  
  // #######################################################################################

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

  if (loading) {
    return <p>Carregando os Eventos...</p>; // Mostra a mensagem de carregamento enquanto os dados não chegam
  }

  return (
    <div className='container-eventos'>
			<Navbar />
      <h1>Todos os Eventos da Cidade</h1>
      <div className='search'>
        <label>
          <span>Buscar por Eventos:</span>
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
                  className="list-cards"
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
      <div className="pagination">
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