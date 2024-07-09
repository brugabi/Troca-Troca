"use client";
import { useState, useEffect } from 'react';
import Image from 'next/image';
import SimpleNavbar from '@components/SimpleNavbar';
import { parseCookies } from 'nookies'; // Importa a função parseCookies de 'nookies'

const ViewMore = () => {
  const [post, setPost] = useState(null);
  const [suggestion, setSuggestion] = useState("");

  useEffect(() => {
    const fetchPostDetails = async () => {
      const postId = localStorage.getItem('selectedPostId');
      if (!postId) return;

      try {
        const response = await fetch(`http://127.0.0.1:8080/api/anuncios/${postId}`);
        const data = await response.json();
        setPost(data.anuncio);
      } catch (error) {
        console.error('Erro ao buscar os detalhes do post:', error);
      }
    };

    fetchPostDetails();
  }, []);

  const handleSuggestionChange = (e) => {
    setSuggestion(e.target.value);
  };

  const sendProposal = async () => {
    // Obter o cookie 'UserId' para pegar o idRequisitante
    const cookies = parseCookies();
    const idRequisitante = cookies.UserId;

    // Montar os dados da proposta
    const proposalData = {
      idRequisitante: Number(idRequisitante),
      idAnuncio: Number(post.id),
      textoProposta: suggestion
    };

    try {
      fetch('http://127.0.0.1:8080/api/proposta/criar', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(proposalData)
      }).then(data => {
        return data.json()
      }).then(data => {
        console.log(data)
      })

      alert('enviado com sucesso!')
      console.log(proposalData)


    } catch (error) {
      console.error('Erro ao enviar proposta:', error);
    }
  };

  if (!post) {
    return <div>Loading...</div>;
  }

  return (
    <div className="flex flex-col w-full min-h-screen">
      <SimpleNavbar />
      <div className="flex flex-col w-full p-4">
        <div className="flex flex-col md:flex-row">
          <div className="md:w-1/2 p-4">
            <div key={post.id} className="bg-white shadow-md rounded-md overflow-hidden">
              {post.imagemUrl && (
                <Image
                  src={`http://127.0.0.1:8080/${post.imagemUrl}`}
                  alt={post.titulo}
                  className="w-full h-48 object-contain"
                  width={500} // Largura da imagem
                  height={300} // Altura da imagem
                />
              )}
            </div>
            <div className="bg-white shadow-md rounded-md p-4 mt-4">
              <h2 className="text-xl font-bold">{post.titulo}</h2>
              <div className="flex items-center mt-4">
                <Image src={post.criador.avatar || 'https://via.placeholder.com/30'} width={30} height={30} className="rounded-full" alt={post.criador.login} />
                <span className="ml-2 text-gray-600">{post.criador.login}</span>
              </div>
            </div>
          </div>
          <div className="md:w-1/2 p-4">
            <div className="bg-white shadow-md rounded-md p-4 mb-4">
              <h3 className="text-md font-bold">Descrição:</h3>
              <div className="border p-2 rounded-md h-40 overflow-auto">
                <p className="text-gray-600">{post.descricao}</p>
              </div>
            </div>
            <div className="bg-white shadow-md rounded-md p-4">
              <h3 className="text-md font-bold">Sugestão de troca:</h3>
              <textarea
                className="w-full mt-2 p-2 border rounded-md"
                rows="4"
                value={suggestion}
                onChange={handleSuggestionChange}
              ></textarea>
              <button
                className="w-full bg-[#F26329] text-white mt-4 p-2 rounded-md"
                onClick={sendProposal} // Chama a função sendProposal ao clicar no botão
              >
                Enviar proposta de troca
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewMore;
