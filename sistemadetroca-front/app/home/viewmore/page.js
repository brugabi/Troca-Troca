"use client";
import { useState } from 'react';
import Image from 'next/image';
import SimpleNavbar from '@components/SimpleNavbar';

const ViewMore = ({ post }) => {
  const [suggestion, setSuggestion] = useState("");

  const handleSuggestionChange = (e) => {
    setSuggestion(e.target.value);
  };

  const handleSendProposal = () => {
    
    console.log("Proposta enviada:", suggestion);
  };

  return (
    <div className="flex flex-col w-full min-h-screen">
      <SimpleNavbar />
      <div className="flex flex-col w-full p-4">
        <div className="flex flex-col md:flex-row">
          <div className="md:w-1/2 p-4">
            <Image src={post.image} width={400} height={200} className="w-full h-auto" alt={post.title} />
            <div className="bg-white shadow-md rounded-md p-4 mt-4">
              <h2 className="text-xl font-bold">{post.title}</h2>
              <div className="flex items-center mt-4">
                <Image src={post.userAvatar} width={30} height={30} className="rounded-full" alt={post.username} />
                <span className="ml-2 text-gray-600">{post.username}</span>
              </div>
            </div>
          </div>
          <div className="md:w-1/2 p-4">
            <div className="bg-white shadow-md rounded-md p-4 mb-4">
              <h3 className="text-md font-bold">Descrição:</h3>
              <div className="border p-2 rounded-md h-40 overflow-auto">
                <p className="text-gray-600">{post.description}</p>
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
                onClick={handleSendProposal}
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


const post = {
  image: "https://via.placeholder.com/400x200",
  title: "Livro Frontend",
  description: "livro de frontend seminovo ...",
  userAvatar: "https://via.placeholder.com/30",
  username: "@usuario1",
};

export default function Page() {
  return <ViewMore post={post} />;
}
