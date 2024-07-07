"use client";
import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Cookies from 'js-cookie'; // Importe Cookies de js-cookie

const CreateExchange = () => {
  const [formData, setFormData] = useState({
    titulo: '',
    categoria: '',
    departamento: '',
    descricao: '',
  });
  const [foto, setFoto] = useState(null); // Estado para armazenar o arquivo de foto
  const [categorias, setCategorias] = useState([]);

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

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value
    });
  };

  const handleFotoChange = (e) => {
    const file = e.target.files[0];
    setFoto(file);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Obter userId do cookie usando js-cookie
      const userId = Cookies.get('userId'); // Substitua 'userId' pelo nome real do cookie

      // Construir um FormData para enviar dados e arquivo de foto
      const formDataWithUser = new FormData();
      formDataWithUser.append('titulo', formData.titulo);
      formDataWithUser.append('categoria', formData.categoria);
      formDataWithUser.append('departamento', formData.departamento);
      formDataWithUser.append('descricao', formData.descricao);
      formDataWithUser.append('criador', userId); // Incluir userId no formulário

      // Adicionar a foto se estiver selecionada
      if (foto) {
        formDataWithUser.append('foto', foto);
      }

      const response = await fetch('http://127.0.0.1:8080/api/anuncios/criarAnuncio', {
        method: 'POST',
        body: formDataWithUser,
      });

      if (response.ok) {
        alert('Cadastro enviado com sucesso!');
        setFormData({ titulo: '', departamento: '', descricao: '' });
        //router.push('/home'); // ou outro caminho que você deseja redirecionar após o sucesso
      } else {
        console.error('Erro ao enviar cadastro:', response.statusText);
      }
    } catch (error) {
      console.error('Erro ao enviar cadastro:', error);
    }
  };

  return (
    <div className='pt-10 flex justify-center'>
      <div className="w-full max-w-4xl">
        <div className="flex justify-center space-x-8">
          {/* Coluna da esquerda */}
          <div className="flex flex-col items-center w-1/2">
            {/* Quadrado de inserir imagem */}
            <div className="flex flex-col items-center border-2 border-dashed border-[#F26329] rounded-md bg-white w-96 p-2 mb-4">
              <input 
                type="file" 
                id="foto" 
                name="foto" 
                accept="image/*" 
                onChange={handleFotoChange}
                className="hidden" // Esconder o input de arquivo padrão
              />
              <label htmlFor="foto" className="cursor-pointer">
                <img src="/icons/adicionar-imagem.svg" alt="Imagem de exemplo" className="object-cover w-full h-full" />
                <span className="text-[#F26329] mt-4 block">Inserir imagem</span>
              </label>
            </div>

            {/* Formulário para contato */}
            <form className="w-96" onSubmit={handleSubmit}>

              {/* Formulário para título */}
              <label htmlFor="titulo" className="text-[#F26329] mt-4 mb-2 block">Título*</label>
              <input 
                type="text" 
                id="titulo" 
                name="titulo" 
                value={formData.titulo} 
                onChange={handleChange}
                className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]" 
              />

              {/* Formulário para categorias */}
              <label htmlFor="categoria" className="text-[#F26329] mt-4 mb-2 block">Categoria*</label>
              <select 
                id="categoria" 
                name="categoria" 
                value={formData.categoria} 
                onChange={handleChange}
                className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]"
              >
                <option value="">Selecione uma categoria</option>
                {categorias.map(categoria => (
                  <option key={categoria.id} value={categoria.id}>{categoria.nome}</option>
                ))}
              </select>

              {/* Formulário para descrição */}
              <label htmlFor="descricao" className="text-[#F26329] mt-4 mb-2 block">Descrição*</label>
              <textarea 
                id="descricao" 
                name="descricao" 
                rows="5" 
                value={formData.descricao} 
                onChange={handleChange}
                className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]" 
              />

              {/* Botão de envio */}
              <button className="bg-[#F26329] text-white px-4 py-2 rounded-md w-full mt-2" type="submit">Enviar Cadastro</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateExchange;
