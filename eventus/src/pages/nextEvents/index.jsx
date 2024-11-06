import { useState, useEffect } from 'react';
import { CardEvento } from '../../components/CardEvento';
import { Navbar } from '../../components/Navbar';
import { DragDropContext, Droppable } from '@hello-pangea/dnd';

import './App.css';

const url = "https://jsonplaceholder.typicode.com/posts/";

export const NextEvents = () => {
  //States do Projeto
  const [eventos, setEventos] = useState([]); // Estado para armazenar os eventos

  // Search Event
  const [search, setSearch] = useState(''); // Estado para a busca
  const [displayedEvents, setDisplayedEvents] = useState([]); // Lista de eventos filtrados
  const [loading, setLoading] = useState(true); // Estado de carregamento

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

  // Efeito para filtrar os eventos sempre que o valor de search mudar
  useEffect(() => {
    const filteredEvents = eventos.filter((evento) =>
      evento.title.toLowerCase().includes(search.toLowerCase()) ||
      evento.id.toString().includes(search)
    );
    setDisplayedEvents(filteredEvents); // Atualiza os eventos filtrados
  }, [search, eventos]); // busca e lista de eventos


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
                    className="lista-cards"
                    {...provided.droppableProps}
                    ref={provided.innerRef}
                  >
                    {displayedEvents.map((evento, index) => (
                    <li key={evento.id}>
                      <CardEvento evento={evento} index={index} />
                    </li>
                    ))}
                    {provided.placeholder}
                  </ul>
                )}
              </Droppable>
            </DragDropContext>
        </section>
      )}
    </div>
  );
};

// export default NextEvents;