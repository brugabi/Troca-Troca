"use client";
import { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Cookies from 'js-cookie';


const CreateExchange = () => {
  const [titulo, setTitulo] = useState('');
  const [descricao, setDescricao] = useState('');
  const [categoria, setCategoria] = useState('');
  const [categorias, setCategorias] = useState([]);
  // const router = useRouter();

  useEffect(() => {
    const fetchCategorias = async () => {
      try {
        const response = await fetch('http://127.0.0.1:8080/api/departamentos/listar');
        if (response.ok) {
          const data = await response.json();
          setCategorias(data);
        } else {
          console.error('Erro ao buscar categorias:', response.statusText);
        }
      } catch (error) {
        console.error('Erro ao buscar categorias:', error);
      }
    };

    fetchCategorias();
  }, []);

  const handleSubmit = async () => {
    try {
      const userId = Cookies.get('UserId')
      const dataToSend = {
        titulo: titulo,
        descricao: descricao,
        idCriador: Number(userId),
        idDepartamento: Number(categoria)
      };

      console.log(dataToSend)

      fetch('http://127.0.0.1:8080/api/anuncios/criarAnuncio', {
        method:'POST',
        headers:{
          'Content-Type': 'application/json',
        },

        body: JSON.stringify(dataToSend) 
      }).then(data =>{
        return data.json()
      }).then(data =>{
        console.log(data)
      })

      alert('Criado com sucesso!')
      console.log(dataToSend)

    } catch (error) {
      console.error('Erro ao enviar cadastro:', error);
    }
  };

  return (
    <div className='pt-10 flex justify-center'>
      <div className="w-full max-w-4xl">
        <div className="flex justify-center space-x-8">
          <div className="flex flex-col items-center w-1/2">
            <div className="w-96">
              <label htmlFor="titulo" className="text-[#F26329] mt-4 mb-2 block">Título*</label>
              <input
                type="text"
                id="titulo"
                name="titulo"
                value={titulo}
                onChange={(e) => setTitulo(e.target.value)}
                className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]"
              />

              <label htmlFor="categoria" className="text-[#F26329] mt-4 mb-2 block">Categoria*</label>
              <select
                id="categoria"
                name="categoria"
                value={categoria}
                onChange={(e) => setCategoria(e.target.value)}
                className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]"
              >
                <option value="">Selecione uma categoria</option>
                {categorias.map(categoria => (
                  <option key={categoria.id} value={categoria.id}>{categoria.nome}</option>
                ))}
              </select>

              <label htmlFor="descricao" className="text-[#F26329] mt-4 mb-2 block">Descrição*</label>
              <textarea
                id="descricao"
                name="descricao"
                rows="5"
                value={descricao}
                onChange={(e) => setDescricao(e.target.value)}
                className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]"
              />

              <button onClick={handleSubmit} className="bg-[#F26329] text-white px-4 py-2 rounded-md w-full mt-2">Enviar Cadastro</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateExchange;