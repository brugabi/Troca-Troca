import Image from "next/image";
import Navbar from '@components/navbar';

export default function Proposals() {
    return (
        <div className="flex flex-col min-h-screen bg-slate-100">
            <Navbar />
            <div className="flex flex-col items-center flex-1 overflow-hidden p-4">
                {/* Primeiro exemplo de proposta */}
                <div className="bg-white shadow-lg rounded-md p-6 w-full max-w-4xl mb-6">
                    <div className="flex items-center mb-4">
                        <div className="w-12 h-12 rounded-full overflow-hidden mr-4">
                            <Image src="https://via.placeholder.com/48" width={48} height={48} alt="Foto do usuário" />
                        </div>
                        <div className="flex flex-col">
                            <h1 className="text-xl font-bold">Título do Anúncio</h1>
                            <span className="text-gray-600">@usuario1</span>
                        </div>
                    </div>
                    <div className="mb-4">
                        <label className="block text-sm font-medium text-gray-700">
                            Descrição da Proposta
                        </label>
                        <p className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm sm:text-sm">
                            Esta é a descrição da proposta feita pelo usuário.
                        </p>
                    </div>
                    <div className="flex justify-end space-x-4">
                        <button className="px-4 py-2 bg-red-500 text-white rounded-md shadow-md hover:bg-red-600">
                            Recusar
                        </button>
                        <button className="px-4 py-2 bg-green-500 text-white rounded-md shadow-md hover:bg-green-600">
                            Aceitar
                        </button>
                    </div>
                </div>

                {/* Segundo exemplo de proposta */}
                <div className="bg-white shadow-lg rounded-md p-6 w-full max-w-4xl mb-6">
                    <div className="flex items-center mb-4">
                        <div className="w-12 h-12 rounded-full overflow-hidden mr-4">
                            <Image src="https://via.placeholder.com/48" width={48} height={48} alt="Foto do usuário" />
                        </div>
                        <div className="flex flex-col">
                            <h1 className="text-xl font-bold">Título do Anúncio</h1>
                            <span className="text-gray-600">@usuario2</span>
                        </div>
                    </div>
                    <div className="mb-4">
                        <label className="block text-sm font-medium text-gray-700">
                            Descrição da Proposta
                        </label>
                        <p className="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm sm:text-sm">
                            Esta é a descrição da proposta feita pelo usuário.
                        </p>
                    </div>
                    <div className="flex justify-end space-x-4">
                        <button className="px-4 py-2 bg-red-500 text-white rounded-md shadow-md hover:bg-red-600">
                            Recusar
                        </button>
                        <button className="px-4 py-2 bg-green-500 text-white rounded-md shadow-md hover:bg-green-600">
                            Aceitar
                        </button>
                    </div>
                </div>

                
            </div>
        </div>
    );
}
