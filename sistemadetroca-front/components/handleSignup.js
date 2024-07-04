"use client";
import { useState } from 'react';
import Image from 'next/image';

const signUp = () => {

    const [primeiroNome, setFirstUsername] = useState('');
    const [ultimoNome, setLastUsername] = useState('');
    const [login, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [senha, setPassword] = useState('');
    const [dataDeNascimento, setBirthdate] = useState('');

    const handleSignup = async () => {
        try {
            const response = await fetch('http://127.0.0.1:8080/api/user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ primeiroNome, ultimoNome, login, email, senha, dataDeNascimento }),
            });

            if (response.ok) {
                // Lógica para sucesso na criação de conta
                console.log('Conta criada com sucesso!');
            } else {
                // Lógica para tratamento de erro
                console.error('Falha ao criar conta:', response.statusText);
            }
        } catch (error) {
            console.error('Erro ao processar criação de conta:', error);
        }
    };

    return (
        <div className="w-[50rem] rounded-e-2xl">
            <div className="flex flex-col justify-center items-center h-[30rem] gap-4">
                <div className="flex flex-col justify-center items-center h-[10rem]">
                    <span className="text-[#F26329] font-bold text-[35px]">Crie uma conta</span>
                    <span className="text-slate-400">Insira as informações solicitadas abaixo</span>
                </div>
                <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
                <Image src={"/icons/user_info.svg"} width={25} height={25} alt="userIcon" />
                    <input
                        value={primeiroNome}
                        onChange={(e) => setFirstUsername(e.target.value)}
                        className="w-[90%] bg-slate-100 focus:outline-none"
                        placeholder="Nome"
                    />
                </div>
                <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
                <Image src={"/icons/user_info.svg"} width={25} height={25} alt="userIcon" />
                    <input
                        value={ultimoNome}
                        onChange={(e) => setLastUsername(e.target.value)}
                        className="w-[90%] bg-slate-100 focus:outline-none"
                        placeholder="Sobrenome"
                    />
                </div>
                <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
                <Image src={"/icons/user.svg"} width={25} height={25} alt="userIcon" />
                    <input
                        value={login}
                        onChange={(e) => setUsername(e.target.value)}
                        className="w-[90%] bg-slate-100 focus:outline-none"
                        placeholder="Nome de Usuário"
                    />
                </div>
                <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
                <Image src={"/icons/mail.svg"} width={25} height={25} alt="mailIcon" />
                    <input
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        type="email"
                        className="w-[90%] bg-slate-100 focus:outline-none"
                        placeholder="Email"
                    />
                </div>
                <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
                <Image src={"/icons/lock.svg"} width={25} height={25} alt="lockIcon" />
                    <input
                        value={senha}
                        onChange={(e) => setPassword(e.target.value)}
                        type="password"
                        className="w-[90%] bg-slate-100 focus:outline-none"
                        placeholder="Senha"
                    />
                </div>
                <div className="flex w-[50%] p-3 bg-slate-100 gap-1">
                <Image src={"/icons/calendar_month.svg"} width={25} height={25} alt="calendarmonthIcon" />
                    <input
                        value={dataDeNascimento}
                        onChange={(e) => setBirthdate(e.target.value)}
                        type="date"
                        className="w-[90%] bg-slate-100 focus:outline-none"
                    />
                </div>
                <div className="flex w-[50%] justify-center">
                    {/* Botão de criar conta */}
                    <button onClick={handleSignup} className="bg-[#F26329] p-2 rounded-lg text-white">
                        Criar Conta
                    </button>
                </div>
            </div>
        </div>
    );
};

export default signUp;