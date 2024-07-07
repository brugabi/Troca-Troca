"use client"
import React, { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import Cookies from 'js-cookie'; // Importe Cookies de js-cookie

const CreateExchange = () => {
  const [formData, setFormData] = useState({
    titulo: '',
    categoria: '',
    departamento: '',
    criador: '',
    descricao: '', // Corrigi a chave 'descricao'
  });
  const [categorias, setCategorias] = useState([]);
  //const router = useRouter();

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

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Obter userId do cookie usando js-cookie
      const userId = Cookies.get('userId'); // Substitua 'userId' pelo nome real do cookie

      // Incluir userId e departamento em formData
      const formDataWithUser = {
        ...formData,
        criador: userId,
        departamento: formData.departamento // Certifique-se de que 'departamento' está sendo definido corretamente
      };

      const response = await fetch('http://127.0.0.1:8080/api/exchanges', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formDataWithUser),
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
              <img src="/icons/adicionar-imagem.svg" alt="Imagem de exemplo" className="object-cover w-full h-full" />
              <span className="text-[#F26329] mt-4 block">Inserir imagem</span>
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
                value={formData.categoria} // Corrigi para 'categoria'
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
