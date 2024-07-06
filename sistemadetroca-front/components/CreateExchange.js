// components/CreateExchange.js
import React from 'react';

const CreateExchange = () => {
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

            {/* Formulário para CEP */}
            <form className="w-96 mb-4">
              <label htmlFor="cep" className="text-[#F26329] mt-4 mb-2 block">CEP*</label>
              <input type="text" id="cep" name="cep" placeholder='00000-000' className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]" />
            </form>

            {/* Formulário para contato */}
            <form className="w-96">
              <label htmlFor="contato" className="text-[#F26329] mt-4 mb-2 block">Contato*</label>
              <input type="text" id="contato" name="contato" className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]" />
            </form>
          </div>

          {/* Coluna da direita */}
          <div className="flex flex-col items-center w-1/2">
            {/* Formulário para título */}
            <form className="w-96 mb-4">
              <label htmlFor="titulo" className="text-[#F26329] mt-4 mb-2 block">Título*</label>
              <input type="text" id="titulo" name="titulo" className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]" />
            </form>

            {/* Formulário para categorias */}
            <form className="w-96 mb-4">
              <label htmlFor="categoria" className="text-[#F26329] mt-4 mb-2 block">Categoria*</label>
              <select id="categoria" name="categoria" className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]">
                <option value="">Selecione uma categoria</option>
                <option value="livros">Livros</option>
                <option value="jogos">Jogos</option>
                <option value="acessorios">Acessórios</option>
                {/* Adicione mais categorias conforme necessário */}
              </select>
            </form>

            {/* Formulário para descrição */}
            <form className="w-96 mb-4">
              <label htmlFor="descricao" className="text-[#F26329] mt-4 mb-2 block">Descrição*</label>
              <textarea id="descricao" name="descricao" rows="5" className="border-2 border-gray-300 rounded-md px-4 py-3 w-full focus:outline-none focus:border-[#F26329]" />
            </form>

            {/* Botão de envio */}
            <button className="bg-[#F26329] text-white px-4 py-2 rounded-md w-full mt-2">Enviar Cadastro</button>
          </div>
        </div>
      </div>
    </div>
  );
}

export default CreateExchange;
