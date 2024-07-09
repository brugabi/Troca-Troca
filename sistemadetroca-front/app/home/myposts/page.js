"use client";
import { useEffect, useState } from 'react';
import Navbar from '@components/navbar';
import Cookies from 'js-cookie';

export default function Myposts() {
    const [proposals, setProposals] = useState([]);
    const userId = Cookies.get('UserId');

    useEffect(() => {
        if (userId) {
            fetch(`http://127.0.0.1:8080/api/anuncios/listarAnuncios/${userId}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Dados recebidos da API:', data);

                    const mappedProposals = data.anuncios.map(item => ({
                        id: item.id,
                        title: item.titulo,
                        description: item.descricao,
                        username: item.criador.primeiroNome + ' ' + item.criador.sobrenome,
                        status: item.status,
                        date: item.dataDeProposta
                    }));

                    setProposals(mappedProposals);
                })
                .catch(error => console.error('Erro ao buscar propostas:', error));
        }
    }, [userId]);

    const handleChangeStatus = (id) => {
        fetch(`http://127.0.0.1:8080/api/anuncios/alterarStatus/${id}`, {
            method: 'PATCH',
        })
        .then(response => response.json())
        .then(data => {
            console.log('Status do anúncio alterado:', data);
            setProposals(proposals.map(proposal => 
                proposal.id === id ? { ...proposal, status: false } : proposal
            ));
        })
        .catch(error => console.error('Erro ao alterar status do anúncio:', error));
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
                                onClick={() => handleChangeStatus(proposal.id)}
                                className="px-4 py-2 bg-red-500 text-white rounded-md shadow-md hover:bg-red-600">
                                Alterar Status
                            </button>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}
