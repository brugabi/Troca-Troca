"use client";
import { useState } from 'react';
import Image from "next/image";
import HendleSignup from "components/handleSignup"

export default function Login() {

  const [login, setUsername] = useState('');
  const [senha, setPassword] = useState('');

  const handleLogin = async () => {
    try {
      const response = await fetch('http://127.0.0.1:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ login, senha }),
      });
  
      // Verifica se a resposta foi bem-sucedida (status code 200-299)
      if (response.ok) {
        // Extrai os dados JSON da resposta
        const data = await response.json();
        // Captura o ID do usuário da resposta
        const userId = data.userId;
        alert(userId)
        console.log('Login concluído! ID do usuário:', userId);
        // Aqui você pode redirecionar o usuário ou realizar outras ações após o login
      } else {
        // Lógica para tratamento de erro
        console.error('Falha no usuário ou senha:', response.statusText);
        // Ou você pode processar o JSON do erro, se houver, com await response.json() aqui
      }
    } catch (error) {
      console.error('Erro ao processar login:', error);
    }
  };

  return (
    <main className="flex h-dvh justify-center items-center">
      <div className="flex items-center w-[70rem] h-[40rem] rounded-2xl shadow-lg">

        <div className="flex bg-[#F26329] w-[25rem] h-full  justify-center rounded-s-2xl hover:w-[80rem] transition-all duration-500">
          <div className="w-full">
            <div className="flex flex-col justify-center items-center h-[30rem] gap-4">
              <div className="flex justify-center items-center font-bold h-[10rem]">
                <span className="text-white text-[35px]">Seja bem vindo!</span>
              </div>
              <div className="flex w-[50%] p-3 bg-slate-100 rounded-sm gap-1">
                <Image src="/icons/mail_black.svg" alt="User Icon" width={25} height={25} />
                <input value={login} onChange={(e) => setUsername(e.target.value)} className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Email"></input>
              </div>
              <div className="flex w-[50%] p-3 bg-slate-100 rounded-sm gap-1">
                <Image src="/icons/lock_black.svg" alt="User Icon" width={25} height={25} />
                <input type="password" value={senha} onChange={(e) => setPassword(e.target.value)} className="w-[90%] bg-slate-100 focus:outline-none" placeholder="Confirmar Senha"></input>
              </div>
              <div className="flex w-[50%] justify-center">
                <button onClick={handleLogin} className="p-2 w-[8rem] rounded-lg text-white border border-white">Login</button>
              </div>
            </div>
          </div>
        </div>

        <HendleSignup />

      </div>
    </main>
  );
}

