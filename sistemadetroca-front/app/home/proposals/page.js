"use client";
import { useEffect, useState } from 'react';
import Image from 'next/image';
import Navbar from '@components/navbar';
import Cookies from 'js-cookie';

export default function Proposals() {
    const [proposals, setProposals] = useState([]);
    const userId = Cookies.get('UserId');

    useEffect(() => {
        if (userId) {
            fetch(`http://127.0.0.1:8080/api/proposta/lista/createdBy/${userId}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Dados recebidos da API:', data); // Console.log para debug

                    // Mapeando os dados recebidos para os campos desejados
                    const mappedProposals = data.map(item => ({
                        id: item.id,
                        title: item.anuncio.titulo,
                        description: item.anuncio.descricao,
                        username: item.requisitante.primeiroNome + ' ' + item.requisitante.sobrenome,
                        status: item.status.nome,
                        date: item.dataDeProposta
                    }));

                    setProposals(mappedProposals);
                })
                .catch(error => console.error('Erro ao buscar propostas:', error));
        }
    }, [userId]);

    const handleAcceptProposal = (id) => {
        fetch(`http://127.0.0.1:8080/api/proposta/aceitar/${id}`, {
            method: 'POST',
        })
        .then(response => response.json())
        .then(data => {
            console.log('Proposta aceita:', data);
            // Atualizar localmente, se necessário
            window.location.reload(); // Recarrega a página após ação concluída
        })
        .catch(error => console.error('Erro ao aceitar proposta:', error));
    };

    const handleRejectProposal = (id) => {
        fetch(`http://127.0.0.1:8080/api/proposta/recusar/${id}`, {
            method: 'POST',
        })
        .then(response => response.json())
        .then(data => {
            console.log('Proposta recusada:', data);
            // Atualizar localmente, se necessário
            window.location.reload(); // Recarrega a página após ação concluída
        })
        .catch(error => console.error('Erro ao recusar proposta:', error));
    };

    return (
        <div className="flex flex-col min-h-screen bg-slate-100">
            <Navbar />
            <div className="flex flex-col items-center flex-1 overflow-hidden p-4">
                {proposals.map(proposal => (
                    <div key={proposal.id} className="bg-white shadow-lg rounded-md p-6 w-full max-w-4xl mb-6">
                        <div className="flex items-start mb-4 justify-between">
                            <div className="flex flex-col">
                                <h1 className="text-xl font-bold">{proposal.title}</h1>
                                <span className="text-gray-600">@{proposal.username}</span>
                            </div>
                            <div className='flex'>
                                <label>Situação: </label>
                                <span>{proposal.status}</span>
                            </div>
                        </div>
                        <div className="mb-4">
                            <label className="block text-sm font-medium text-gray-700">
                                Descrição da Proposta
                            </label>
                            <p className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm sm:text-sm">
                                {proposal.description}
                            </p>
                        </div>
                        <div className="flex justify-end space-x-4">
                            <button
                                onClick={() => handleRejectProposal(proposal.id)}
                                className="px-4 py-2 bg-red-500 text-white rounded-md shadow-md hover:bg-red-600">
                                Recusar
                            </button>
                            <button
                                onClick={() => handleAcceptProposal(proposal.id)}
                                className="px-4 py-2 bg-green-500 text-white rounded-md shadow-md hover:bg-green-600">
                                Aceitar
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}

