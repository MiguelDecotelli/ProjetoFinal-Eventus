import { useState, useEffect } from 'react';
import { CardEvento } from '../../components/CardEvento';
import { Navbar } from '../../components/Navbar';

import './App.css';

const url = "https://jsonplaceholder.typicode.com/posts/";

export const NextEvents = () => {
  // ### Custom Fetch ##########
  const [ data, setData ] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      const response = await fetch(url);
      const json = await response.json();

      setData(json);
    }

    fetchData();

  }, [url]);

  const eventos = data;
  // #####################################

  // Search Event
  const [ search, setSearch ] = useState('');
  const searchLowerCase = search.toLowerCase();

  const filteredEvents = eventos ? eventos.filter((e) => 
    e.title.toLowerCase().includes(searchLowerCase) ||
    e.id.toString().includes(search)
  ) : [];

  return (
    <div className='container-eventos'>
			<Navbar />
      <h1>Todos os Eventos da Cidade</h1>
      <div className='search'>
        <label>
          <span>Buscar por Eventos:</span>
          <input 
            type="text"
            placeholder='Buscar Evento'
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
        </label>
      </div>
      {eventos ? (
        <div>
          <ul className='lista-cards'>
            {filteredEvents.map((evento) => (
              <li key={evento.id}>
                <CardEvento id={evento.id} title={evento.title} description={evento.body} />
              </li>
            ))}
          </ul>
        </div>
      ) : (
        <p>Carregando os Eventos...</p>
      )}
    </div>
  )
}

// export default NextEvents;