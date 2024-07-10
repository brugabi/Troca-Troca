"use client";
import { useEffect, useState } from 'react';
import Navbar from '@components/navbar';
import Cookies from 'js-cookie';

export default function MyProposals() {
    const [proposals, setProposals] = useState([]);
    const userId = Cookies.get('UserId');

    useEffect(() => {
        if (userId) {
            fetch(`http://127.0.0.1:8080/api/proposta/lista/requisitante/${userId}`)
                .then(response => response.json())
                .then(data => {
                    console.log('Dados recebidos da API:', data);

                    const filteredProposals = data.filter(item => item.anuncio.criador.id !== parseInt(userId) && item.status.id !== 3 );

                    const mappedProposals = filteredProposals.map(item => ({
                        id: item.id,
                        title: item.anuncio.titulo,
                        description: item.anuncio.descricao,
                        username: item.requisitante.primeiroNome + '_' + item.requisitante.sobrenome,
                        status: item.status.nome,
                        statusId: item.status.id,
                        date: item.dataDeProposta,
                        mensagem: item.mensagem,
                        criador: item.anuncio.criador.primeiroNome + '_' + item.anuncio.criador.sobrenome,
                    }));

                    setProposals(mappedProposals);
                })
                .catch(error => console.error('Erro ao buscar propostas:', error));
        }
    }, [userId]);

    const handleConfirmProposalByRequisitante = (id) => {
        fetch(`http://127.0.0.1:8080/api/proposta/confirmarPeloRequisitante/${id}`, {
            method: 'POST',
        })
        .then(response => response.json())
        .then(data => {
            console.log('Proposta confirmada pelo requisitante:', data);
            window.location.reload();
        })
        .catch(error => console.error('Erro ao confirmar proposta pelo requisitante:', error));
    };

    return (
        <div className="flex flex-col min-h-screen bg-slate-100">
            <Navbar />
            <div className="flex flex-col items-center flex-1 overflow-hidden p-4">
                {proposals.length > 0 ? (
                    proposals.map(proposal => (
                        <div key={proposal.id} className="bg-white shadow-lg rounded-md p-6 w-full max-w-4xl mb-6">
                            <div className="flex items-start mb-4 justify-between">
                                <div className="flex flex-col">
                                    <h1 className="text-xl font-bold">{proposal.title}</h1>
                                    <span className="text-gray-600">Criador: @{proposal.criador}</span>
                                    <span className="text-gray-600">Requisitante: @{proposal.username}</span>
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
                                    {proposal.mensagem}
                                </p>
                            </div>
                            <div className="flex justify-end space-x-4">
                                {proposal.statusId === 2 && (
                                    <button
                                        onClick={() => handleConfirmProposalByRequisitante(proposal.id)}
                                        className="px-4 py-2 bg-blue-500 text-white rounded-md shadow-md hover:bg-blue-600"
                                    >
                                        Confirmar
                                    </button>
                                )}
                            </div>
                        </div>
                    ))
                ) : (
                    <p className="text-gray-600">Não há propostas disponíveis para visualização.</p>
                )}
            </div>
        </div>
    );
}
